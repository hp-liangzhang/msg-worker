package com.marykay.community;

/**
 * Created by wanwei on 16/8/16.
 */
public class Config {

    private int port;
    private String amqpURI;
    private String exchange;
    private String exchangeType;
    private String queueName;
    private String consumerTag;
    private String routingKey;

    public static Config Load() {
        Config config = new Config();
        config.port = 80;

        final String portEnv = System.getenv("PORT");
        if (portEnv != null) {
            config.port = Integer.parseInt(portEnv);
        }

        config.amqpURI = loadEnv("AMQP_URI", "amqp://localhost:5672/");
        config.exchange = loadEnv("EXCHANGE", ""); // default exchange
        config.exchangeType = loadEnv("EXCHANGE_TYPE", "direct");
        config.queueName = loadEnv("QUEUE_NAME", "test");
        config.consumerTag = loadEnv("CONSUMER_TAG", "msg-worker");
        config.routingKey = loadEnv("ROUTING_KEY", "*");

        return config;
    }

    private static String loadEnv(String key, String _default) {
        String value = System.getenv(key);
        return value == null ? _default : value;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAmqpURI() {
        return amqpURI;
    }

    public void setAmqpURI(String amqpURI) {
        this.amqpURI = amqpURI;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public void setConsumerTag(String consumerTag) {
        this.consumerTag = consumerTag;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

}
