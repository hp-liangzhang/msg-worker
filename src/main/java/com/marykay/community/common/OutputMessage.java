package com.marykay.community.common;

import java.util.Date;
import java.util.UUID;

/**
 * Created by zhujohnny11 on 2016/8/18.
 */
public class OutputMessage {

    private Date time;
    private UUID transationId;
    private  String logLevel;
    private String application;
    private String ENV;
    private String gitHash;
    private String messageBody;

    public Date getTime(){
        return this.time;
    }

    public void steTime(Date time){
        this.time = time;
    }

    public String getLogLevel(){
        return this.logLevel;
    }

    public void setLogLevel(String logLevel){
        this.logLevel = logLevel;
    }

    public String getApplication(){
        return this.application;
    }

    public void setApplication(String application){
        this.application = application;
    }

    public UUID getTransationId(){
        return this.transationId;
    }

    public void setTransationId(UUID transationId){
        this.transationId=transationId;
    }

    public String getENV(){
        return this.ENV;
    }

    public void setENV(String env){
        this.ENV = env;
    }

    public String getGitHash(){
        return this.gitHash;
    }

    public void setGitHash(String gitHash){
        this.gitHash = gitHash;
    }

    public String getMessageBody(){
        return this.messageBody;
    }

    public void setMessageBody(String messageBody){
        this.messageBody = messageBody;
    }


}
