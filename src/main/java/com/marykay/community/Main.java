package com.marykay.community;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class Main {

    private static Config config;

    public static void main(String[] args) {
        config = Config.Load();

        try {
            System.out.println("httpd listen on port:" + config.getPort());
            Httpd httpd = new Httpd(config.getPort());
            httpd.start();
        } catch (IOException e) {
            System.err.println("Couldn't start server:\n" + e);
            return;
        }

//        test();
        try {
            startConsumer(config.getAmqpURI(), config.getExchange(), config.getQueue(),"*");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        while (true) {
            Date date = new Date();
            System.out.println(date.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startConsumer(String amqpURI, String exchangeName,String queue, String routingKey) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(amqpURI);
        Connection conn = factory.newConnection();

        final Channel channel = conn.createChannel();
        channel.exchangeDeclare(exchangeName, "direct", true);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchangeName, routingKey);

        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, "myConsumerTag",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag,
                                               Envelope envelope,
                                               AMQP.BasicProperties properties,
                                               byte[] body)
                            throws IOException
                    {
                        String routingKey = envelope.getRoutingKey();
                        String contentType = properties.getContentType();
                        long deliveryTag = envelope.getDeliveryTag();
                        // (process the message components here ...)

                        System.out.print(new String(body));
                        channel.basicAck(deliveryTag, false);
                    }
                });
    }
}
