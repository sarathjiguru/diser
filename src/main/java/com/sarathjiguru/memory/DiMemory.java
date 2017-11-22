package com.sarathjiguru.memory;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by sarath on 11/11/17.
 */
public class DiMemory {
    private final DiserCommand diserCommand;

    static HashMap<String, Object> map = new HashMap<>();
    private final Object returnValue;
    public DiMemory(String commandObject, DiskWriter dw) throws IOException {
        this.diserCommand = new DiserCommand(commandObject.trim());

        switch (this.diserCommand.command) {
            case SET:
                map.put(this.diserCommand.key, this.diserCommand.value);
                dw.write(commandObject.trim());
                returnValue = 1;
                break;
            case GET:
                Object o = map.get(this.diserCommand.key);
                returnValue = o == null ? "key " + this.diserCommand.key + " does not exist" : o;
                break;
            default:
                returnValue = "Unsupported Operation. Given Command is " + commandObject;
        }
    }

    public String result() {
        return returnValue.toString();
    }

    enum Commands {
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

    class DiserCommand {
        Commands command;
        String key;
        Object value;

        private String[] parseCommandObject(String commandObject) {
            String[] split = commandObject.split("\\$");
            return split;
        }

        DiserCommand(String commandObject) {
            String[] split = parseCommandObject(commandObject);
            try {
                this.command = getCommand(split[0]);
            } catch (Exception e) {
                this.command = Commands.UNSUPPORTED;
            }
            if (this.command == Commands.SET && split.length == 3) {
                this.command = Commands.SET;
                this.key = split[1];
                this.value = split[2];
            } else if (this.command == Commands.GET && split.length == 2) {
                this.command = Commands.GET;
                this.key = split[1];
            } else {
                this.command = Commands.UNSUPPORTED;
            }
        }

        private Commands getCommand(String givenCommand) {
            try {
                return Commands.valueOf(givenCommand);
            } catch (IllegalArgumentException e) {
                return Commands.UNSUPPORTED;
            }

        }
    }
}
