package com.marykay.community.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marykay.community.common.Config;
import com.marykay.community.common.MyLogManager;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhujohnny11 on 2016/8/18.
 */
public class MessageRepository {

    private static Config config;
    private MessageSqlHelper messageSqlHelper;

    public MessageRepository(MessageSqlHelper messageSqlHelper){
        config = Config.Load();
        this.messageSqlHelper = messageSqlHelper;
    }

    public void ConsumeMessage() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(config.getAmqpURI());
        com.rabbitmq.client.Connection conn = factory.newConnection(config.getAmqpURI());

        final Channel channel = conn.createChannel();
        if (config.getExchange().equals("")) {
            channel.queueDeclare(config.getQueueName(), true, false, false, null);
        } else {
            channel.exchangeDeclare(config.getExchange(), config.getExchangeType(),true);
            channel.queueDeclare(config.getQueueName(), true, false, false, null);
            channel.queueBind(config.getQueueName(), config.getExchange(), config.getRoutingKey());
        }
        boolean autoAck = false;
        channel.basicConsume(config.getQueueName(), autoAck, config.getConsumerTag(),
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag,
                                               Envelope envelope,
                                               AMQP.BasicProperties properties,
                                               byte[] body)
                            throws IOException
                    {
                        long deliveryTag = envelope.getDeliveryTag();
                        String message = new String(body, "UTF-8");
                        String mesageId = GetMessageId(message);
                        if(mesageId!=null) messageSqlHelper.UpdateMessageStatus(mesageId);
                        channel.basicAck(deliveryTag, false);
                    }
                });
    }

    private String GetMessageId(String message){
        try {
            MyLogManager.LogInfo("current message: "+message);
            Gson gson = new Gson();
            Map jsonData = gson.fromJson(message, new TypeToken<HashMap>() {
            }.getType());
            return jsonData.get("I").toString();
        }catch (Exception ex){
            MyLogManager.ErrorInfo(ex.getMessage());
            return  null;
        }
    }
}
