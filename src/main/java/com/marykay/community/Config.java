package com.marykay.community;

/**
 * Created by wanwei on 16/8/16.
 */
public class Config {

    private int port;
    private String amqpURI;
    private String exchange;
    private String queue;
    private String consumerTag;

    public static Config Load() {
        Config config = new Config();
        config.port = 80;

        final String portEnv = System.getenv("PORT");
        if (portEnv != null) {
            config.port = Integer.parseInt(portEnv);
        }

        config.amqpURI = loadEnv("AMQP_URI", "amqp://localhost:5672/");
        config.exchange = loadEnv("EXCHANGE", "test-exchange");
        config.queue = loadEnv("EXCHANGE", "test-queue");
        config.consumerTag = loadEnv("CONSUMER_TAG", "test-tag");

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

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public void setConsumerTag(String consumerTag) {
        this.consumerTag = consumerTag;
    }
}
