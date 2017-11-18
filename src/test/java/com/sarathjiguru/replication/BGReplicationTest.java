package com.sarathjiguru.replication;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by sarath on 18/11/17.
 */
public class BGReplicationTest {
    @Test
    public void replicate() throws Exception {
        ArrayList<String> replicators = new ArrayList<>();
        replicators.add("diser://127.0.0.1:7433/");
        BGReplication bgReplication = new BGReplication(replicators);
    }

}