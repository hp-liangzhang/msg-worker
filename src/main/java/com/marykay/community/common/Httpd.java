package com.marykay.community.common;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by wanwei on 16/8/16.
 */
public class Httpd extends NanoHTTPD {
    public Httpd(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello, this is a web server</h1></body></html>";
        return newFixedLengthResponse(msg);
    }
}
