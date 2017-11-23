package com.sarathjiguru.replication;

import java.util.ArrayList;
import java.util.List;

import com.sarathjiguru.memory.Commands;
import com.sarathjiguru.memory.DiserCommand;
import com.sarathjiguru.transport.DiserUrl;

/**
 * Created by sarath on 15/11/17.
 * Background thread based Replication Implementation
 */
public class BGReplication implements Replication {
    private final List<DiserUrl> replicators;

    public BGReplication(List<String> replicators) {
        this.replicators = createReplicators(replicators);
    }

    private List<DiserUrl> createReplicators(List<String> replicators) {
        ArrayList<DiserUrl> dURLS = new ArrayList<>();
        for (String r : replicators) {
            dURLS.add(new DiserUrl(r));
        }
        return dURLS;
    }

    @Override
    public String replicate(String command) {
        BGThread bgThread = new BGThread(replicators, DiserCommand.REPLICATED + command);
        Thread thread = new Thread(bgThread);
        thread.setDaemon(true);
        thread.start();
        return "ok";
    }

    public String replicate(DiserCommand command) {
        if (!command.isReplicated) {
            if (command.command == Commands.SET) {
                replicate(command.toString());
            }
        }
        return "ok";
    }
}
