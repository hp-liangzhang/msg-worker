package com.marykay.community;


import java.util.Date;

public class Main {

    public static void main(String[] args) {
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
