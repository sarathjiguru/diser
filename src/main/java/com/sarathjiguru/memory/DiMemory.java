package com.sarathjiguru.memory;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by sarath on 11/11/17.
 * Handles writing to static hashmap and retrieving from the hashmap. Writing the key-value pair to disk.
 */
public class DiMemory {

    static HashMap<String, Object> map = new HashMap<>();
    private final DiskWriter dw;

    public DiMemory(DiskWriter dw) throws IOException {
        this.dw = dw;
    }

    /**
     * Parses the command and takes appropriate action.
     * EX: SET$a$342q3
     *
     * @param commandObject
     * @return
     * @throws IOException
     */
    public String result(String commandObject) throws IOException {
        DiserCommand diserCommand = new DiserCommand(commandObject.trim());
        Object returnValue;
        switch (diserCommand.command) {
            case SET:
                map.put(diserCommand.key, diserCommand.value);
                dw.write(diserCommand.toString());
                returnValue = 1;
                break;
            case GET:
                Object o = map.get(diserCommand.key);
                returnValue = o == null ? "key " + diserCommand.key + " does not exist" : o;
                break;
            default:
                returnValue = "Unsupported Operation. Given Command is " + diserCommand.toString();
        }
        return returnValue.toString();
    }

    public String result(DiserCommand diserCommand) throws IOException {
        return result(diserCommand.toString());
    }
}
