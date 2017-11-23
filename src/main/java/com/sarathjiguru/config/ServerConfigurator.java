package com.sarathjiguru.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sarath on 15/11/17.
 * Maps YAML file to Java Object
 */
public class ServerConfigurator {

    /**
     * Default constructor used by ObjectMapper
     */
    ServerConfigurator() {

    }

    @JsonProperty("master.port")
    private int masterPort;

    @JsonProperty("master.diskpath")
    private String diskPath;

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

    public String getDiskPath() {
        return diskPath;
    }
}
