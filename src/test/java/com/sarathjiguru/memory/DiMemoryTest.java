package com.sarathjiguru.memory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sarath on 12/11/17.
 */
public class DiMemoryTest {
    @Test
    public void result() throws Exception {
        DiskWriter diskWriter = new DiskWriter();
        String commandObject = "";
        DiMemory diMemory = new DiMemory(commandObject, diskWriter);
        assertEquals(diMemory.result(), "Unsupported Operation. Given Command is ");

        commandObject = " $";
        diMemory = new DiMemory(commandObject, diskWriter);
        assertEquals(diMemory.result(), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "set$a$b";
        diMemory = new DiMemory(commandObject, diskWriter);
        assertEquals(diMemory.result(), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "get$a";
        diMemory = new DiMemory(commandObject, diskWriter);
        assertEquals(diMemory.result(), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "GET$a$4";
        diMemory = new DiMemory(commandObject, diskWriter);
        assertEquals(diMemory.result(), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "SET$a$";
        diMemory = new DiMemory(commandObject, diskWriter);
        assertEquals(diMemory.result(), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "SET$a$4$5";
        diMemory = new DiMemory(commandObject, diskWriter);
        assertEquals(diMemory.result(), "Unsupported Operation. Given Command is " + commandObject);


        commandObject = "GET$a$";
        diMemory = new DiMemory(commandObject, diskWriter);
        assertEquals(diMemory.result(), "key a does not exist");

    }

}