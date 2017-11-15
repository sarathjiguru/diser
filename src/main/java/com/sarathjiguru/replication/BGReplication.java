package com.sarathjiguru.replication;

import com.sarathjiguru.server.DiserUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarath on 15/11/17.
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
        BGThread bgThread = new BGThread(replicators, command);
        Thread thread = new Thread(bgThread);
        thread.setDaemon(true);
        thread.start();
        return "ok";
    }
}
