package com.sarathjiguru.memory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sarath on 23/11/17.
 */
public class DiserCommandTest {
    @Test
    public void testToString() throws Exception {
        DiserCommand diserCommand = new DiserCommand("@replicatedSET$a$100");
        System.out.println(diserCommand.toString());
    }

}