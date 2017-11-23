package com.sarathjiguru.replication;

import com.sarathjiguru.memory.DiserCommand;

/**
 * Created by sarath on 15/11/17.
 */
public class NoOpReplication implements Replication{
    @Override
    public String replicate(String command) {
        return "no-replication";
    }

    @Override
    public String replicate(DiserCommand command) {
        return replicate(command.toString());
    }
}
