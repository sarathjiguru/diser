package com.sarathjiguru.replication;

import com.sarathjiguru.server.DiserUrl;
import com.sarathjiguru.transport.DiserTransportCli;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by sarath on 15/11/17.
 */
public class BGThread implements Runnable {
    private final List<DiserUrl> replicators;
    private final String command;

    @Override
    public void run() {
        try {
            sendCommandForReplication();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public BGThread(List<DiserUrl> replicators, String command) {
        this.replicators = replicators;
        this.command = command;
    }

    private void sendCommandForReplication() throws InterruptedException, ExecutionException {
        for (DiserUrl dUrl : replicators) {
            DiserTransportCli connect = DiserTransportCli.connect(dUrl.host(), dUrl.port());
            connect.runCommand(command);
        }
    }
}
