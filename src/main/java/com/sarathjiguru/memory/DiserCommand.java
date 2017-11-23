package com.sarathjiguru.memory;

/**
 * Created by sarath on 23/11/17.
 */
public class DiserCommand {
    public static final String SEPARATOR = "\\$";
    private final String commandObject;
    Commands command;
    String key;
    Object value;

    @Override
    public String toString() {
        return commandObject;
    }

    private void parseCommandObject(String commandObject) {
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
    }

    DiserCommand(String commandObject) {
        this.commandObject = commandObject;
        parseCommandObject(commandObject);
    }
}
