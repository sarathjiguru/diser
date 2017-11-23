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
        DiMemory diMemory = new DiMemory(diskWriter);
        assertEquals(diMemory.result(commandObject), "Unsupported Operation. Given Command is ");

        commandObject = " $";
        assertEquals(diMemory.result(commandObject), "Unsupported Operation. Given Command is " + commandObject.trim());

        commandObject = "set$a$b";
        assertEquals(diMemory.result(commandObject), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "get$a";
        assertEquals(diMemory.result(commandObject), "Unsupported Operation. Given Command is "+commandObject);

        commandObject = "GET$a$4";
        diMemory = new DiMemory(diskWriter);
        assertEquals(diMemory.result(commandObject), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "SET$a$";
        assertEquals(diMemory.result(commandObject), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "SET$a$4$5";
        assertEquals(diMemory.result(commandObject), "Unsupported Operation. Given Command is " + commandObject);

        commandObject = "SET$a$4";
        assertEquals(diMemory.result(commandObject), "1");

        commandObject = "GET$a";
        assertEquals(diMemory.result(commandObject), "4");


        commandObject = "@replicatedSET$a$4";
        assertEquals(diMemory.result(commandObject), "1");


    }

}