package com.marykay.community;


import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        int port = 80;

        final String portEnv = System.getenv("PORT");
        if (portEnv != null) {
            port = Integer.parseInt(portEnv);
        }

        System.out.println("httpd listen on port:" + port);
        Httpd httpd = new Httpd(port);

        try {
            httpd.start();
        } catch (IOException e) {
            System.err.println("Couldn't start server:\n" + e);
            return;
        }

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
}
