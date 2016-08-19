package com.marykay.community.common;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhujohnny11 on 2016/8/18.
 */
public class MyLogManager {

private static Config config;

    public static void LogInfo(String message)
    {
        LogInfo(message, UUID.randomUUID());
    }

    public static void LogInfo(String message, UUID tranId)
    {
        if(config==null) config = Config.Load();
        String hash = "EMPTY-HASH";
        if (StringUtils.isNotEmpty(config.getGitHash()))
        {
            hash = config.getGitHash();
        }
        String strOutputMessage = GenerateMessageBody(message, hash, "Info");
        System.out.println(strOutputMessage);
    }

    private static String GenerateMessageBody(String message, String hash, String LogLevel)
    {
        return GenerateMessageBody(message, hash, LogLevel, UUID.randomUUID());

    }
    private static String GenerateMessageBody(String message, String hash, String logLevel, UUID tranId)
    {
        OutputMessage msg = new OutputMessage();
        msg.setApplication(config.getApplication());
        msg.setENV(config.getEnv());
        msg.setGitHash(hash);
        msg.setMessageBody(message);
        msg.setLogLevel(logLevel);
        msg.steTime(new Date());
        msg.setTransationId(tranId);
        Gson gson = new Gson();
        String strOutputMessage = gson.toJson(msg);
        return strOutputMessage;
    }

    public static void ErrorInfo(String message)
    {
        ErrorInfo(message,UUID.randomUUID() );
    }
    public static void ErrorInfo(String message,  UUID tranId)
    {
        String hash = "EMPTY-HASH";
        if (!StringUtils.isNotEmpty(config.getGitHash()))
        {
            hash = config.getGitHash();
        }
        String strOutputMessage = GenerateMessageBody(message, hash, "Error");
        System.out.println(strOutputMessage);
    }
}
