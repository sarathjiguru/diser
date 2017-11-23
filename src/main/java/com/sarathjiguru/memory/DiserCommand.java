package com.sarathjiguru.memory;

/**
 * Created by sarath on 23/11/17.
 * Parses the String Command sent from client.
 */
public class DiserCommand {
    public static final String SEPARATOR = "\\$";
    public static final String REPLICATED = "@replicated";
    private String commandObject;
    public Commands command;
    String key;
    Object value;
    public boolean isReplicated;

    @Override
    public String toString() {
        return commandObject;
    }

    private void parseCommandObject(String commandObject) {
        if (commandObject.contains(REPLICATED)) {
            commandObject = commandObject.replace(REPLICATED, "");
            this.isReplicated = true;
        }

        String[] split = commandObject.split(SEPARATOR);
        this.command = Commands.UNSUPPORTED;
        try {
            Commands command = Commands.valueOf(split[0]);
            if (split.length == 3 && command == Commands.SET) {
                this.command = Commands.SET;
                this.key = split[1];
                this.value = split[2];
            } else if (split.length == 2 && command == Commands.GET) {
                this.command = Commands.GET;
                this.key = split[1];
            }
        } catch (Exception e) {
            this.command = Commands.UNSUPPORTED;
        }
        this.commandObject = commandObject;
    }

    public DiserCommand(String commandObject) {
        parseCommandObject(commandObject);
    }
}
