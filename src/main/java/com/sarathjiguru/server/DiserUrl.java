package com.sarathjiguru.server;

/**
 * Created by sarath on 15/11/17.
 */
public class DiserUrl {
    private final String host;
    private final int port;

    public DiserUrl(String r) {
        String hostPort = r.replace("diser://", "").replace("/", "");
        String[] split = hostPort.split(":");
        this.host = split[0];
        this.port = Integer.parseInt(split[1]);
    }

    public int port() {
        return port;
    }

    public String host() {
        return host;
    }
}
