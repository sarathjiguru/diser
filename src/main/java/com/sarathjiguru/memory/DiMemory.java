package com.sarathjiguru.memory;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by sarath on 11/11/17.
 */
public class DiMemory {

    static HashMap<String, Object> map = new HashMap<>();
    private final DiskWriter dw;

    public DiMemory(DiskWriter dw) throws IOException {
        this.dw = dw;
    }

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
}
