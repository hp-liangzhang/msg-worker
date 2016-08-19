package com.marykay.community.message;

import com.marykay.community.common.Config;
import com.marykay.community.common.Httpd;
import com.marykay.community.common.MyLogManager;
import com.marykay.community.repository.MessageRepository;
import com.marykay.community.repository.MessageSqlHelper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class Main {

    private static Config config;

    public static void main(String[] args) {

       //Start a http Server
        config = Config.Load();
        try {
            MyLogManager.LogInfo("httpd listen on port:" + config.getPort());
            Httpd httpd = new Httpd(config.getPort());
            httpd.start();
        } catch (IOException e) {
            MyLogManager.ErrorInfo("Couldn't start server:\n" + e.getStackTrace().toString());
            return;
        }
        MessageSqlHelper messageSqlHelper = new MessageSqlHelper();
        messageSqlHelper.setDbConnectString(config.getDbConnectString());
        try {
            MyLogManager.LogInfo("consume starting...");
           new MessageRepository(messageSqlHelper).ConsumeMessage();
        } catch (IOException e) {
            MyLogManager.ErrorInfo(e.getMessage()+"||"+e.getStackTrace().toString());
        } catch (TimeoutException e) {
            MyLogManager.ErrorInfo(e.getMessage()+"||"+e.getStackTrace().toString());
        } catch (NoSuchAlgorithmException e) {
            MyLogManager.ErrorInfo(e.getMessage()+"||"+e.getStackTrace().toString());
        } catch (KeyManagementException e) {
            MyLogManager.ErrorInfo(e.getMessage()+"||"+e.getStackTrace().toString());
        } catch (URISyntaxException e) {
            MyLogManager.ErrorInfo(e.getMessage()+"||"+e.getStackTrace().toString());
        }
    }

}
