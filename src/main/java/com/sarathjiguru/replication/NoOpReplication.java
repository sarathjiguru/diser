package com.sarathjiguru.replication;

/**
 * Created by sarath on 15/11/17.
 */
public class NoOpReplication implements Replication{
    @Override
    public String replicate(String command) {
        return "no-replication";
    }
}
