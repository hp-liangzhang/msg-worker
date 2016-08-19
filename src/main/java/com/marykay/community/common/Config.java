package com.marykay.community.common;

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
    private String dbConnectString;
    private String application;
    private String env;
    private String gitHash;


    public String getApplication(){
        return this.application;
    }

    public void setApplication(String application){
        this.application = application;
    }

    public String getEnv(){
        return this.env;
    }

    public void setEnv(String env){
        this.env = env;
    }

    public String getGitHash(){
        return this.gitHash;
    }

    public void setGitHash(String gitHash){
        this.gitHash = gitHash;
    }



    public static Config Load() {
        Config config = new Config();
        config.port = 80;

        final String portEnv = System.getenv("PORT");
        if (portEnv != null) {
            config.port = Integer.parseInt(portEnv);
        }

        config.amqpURI = loadEnv("AMQP_URI", "amqp://mkuser:admin@localhost:5672");
        config.exchange = loadEnv("EXCHANGE", "mkm.ibc.private"); // default exchange
        config.exchangeType = loadEnv("EXCHANGE_TYPE", "topic");
        config.queueName = loadEnv("QUEUE_NAME", "mkm.ibc.message.ack");
        config.consumerTag = loadEnv("CONSUMER_TAG", "msg-worker");
        config.routingKey = loadEnv("ROUTING_KEY", "mkm.ibc.private.com.marykay.china.community.11e7f03d-751c-4958-9b3d-ae9b747c8709");
        config.dbConnectString = loadEnv("CommunityDB","jdbc:sqlserver://wddcedsql20.mkdev.com:501;" +
                "databaseName=Community;user=siteuser;password=B161C90H");

        return config;
    }

    private static String loadEnv(String key, String _default) {
        String value = System.getenv(key);
        return value == null ? _default : value;
    }

    public String getDbConnectString(){
        return this.dbConnectString;
    }

    public void setDbConnectString(String dbConnectString){
        this.dbConnectString = dbConnectString;
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
