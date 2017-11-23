package com.sarathjiguru.memory;

/**
 * Created by sarath on 23/11/17.
 */
public     class DiserCommand {
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
        try {
            this.command = Commands.valueOf(split[0]);
            if (command == Commands.SET && split.length == 3) {
                this.command = Commands.SET;
                this.key = split[1];
                this.value = split[2];
            } else if (this.command == Commands.GET && split.length == 2) {
                this.command = Commands.GET;
                this.key = split[1];
            }
        } catch (IllegalArgumentException e) {
            this.command = Commands.UNSUPPORTED;
        }
    }

    DiserCommand(String commandObject) {
        this.commandObject = commandObject;
        parseCommandObject(commandObject);
    }
}
