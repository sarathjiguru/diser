package com.sarathjiguru.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sarath on 15/11/17.
 */
public class ServerConfigurator {

    ServerConfigurator() {

    }

    @JsonProperty("master.port")
    private int masterPort;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("replication")
    private List<String> replicators;

    public int getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(int masterPort) {
        this.masterPort = masterPort;
    }

    public List<String> getReplicators() {
        return replicators;
    }

    public void setReplicators(List<String> replicators) {
        this.replicators = replicators;
    }
}
