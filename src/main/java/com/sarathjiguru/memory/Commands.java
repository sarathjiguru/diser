package com.sarathjiguru.memory;

/**
 * Created by sarath on 23/11/17.
 */
public enum Commands {
    SET("SET"),
    GET("GET"),
    UNSUPPORTED("UNSUPPORTED OPERATION");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String command() {
        return this.command;
    }

}