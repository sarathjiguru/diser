package com.sarathjiguru.memory;

/**
 * Created by sarath on 23/11/17.
 *
 * List of Commands available in Diser
 */
public enum Commands {
    /**
     * command to put key value pair in diser
     * Ex: SET$a$1043
     */
    SET("SET"),
    /**
     * Command to retrieve value for a key from diser.
     * EX:GET$a
     */
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