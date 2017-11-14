package com.sarathjiguru.replication;

import com.sarathjiguru.server.DiserUrl;

import java.util.List;

/**
 * Created by sarath on 15/11/17.
 */
public class BGThread implements Runnable {
    private final List<DiserUrl> replicators;

    @Override
    public void run() {

    }

    public BGThread(List<DiserUrl> replicators) {
        this.replicators = replicators;
    }

    private void runCommandInReplicatedServers(){
        for(DiserUrl dUrl: replicators){

        }
    }
}
