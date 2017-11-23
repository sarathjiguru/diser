package com.sarathjiguru.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sarathjiguru.replication.BGReplication;
import com.sarathjiguru.replication.NoOpReplication;
import com.sarathjiguru.replication.Replication;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by sarath on 15/11/17.
 */
public class ServerConfig {

    private final int masterPort;
    private final Replication replication;
    private final String diskPath;
    private YAMLFactory yamlFactory;
    private ObjectMapper objectMapper;

    public ServerConfig(int masterPort) {
        this.masterPort = masterPort;
        this.diskPath = "/tmp/diser-data.txt";
        this.replication = new NoOpReplication();
    }

    public ServerConfig(String filePath) throws IOException {
        yamlFactory = new YAMLFactory();
        objectMapper = new ObjectMapper(yamlFactory);
        ServerConfigurator sc = objectMapper.readValue(new File(filePath), ServerConfigurator.class);

        masterPort = sc.getMasterPort();
        diskPath = sc.getDiskPath();
        List<String> replicators = sc.getReplicators();
        if (replicators == null || replicators.size() == 0) {
            this.replication = new NoOpReplication();
        } else {
            this.replication = new BGReplication(replicators);
        }
    }

    public int masterPort() {
        return masterPort;
    }

    public Replication replication() {
        return replication;
    }

    public String getDiskPath() {
        return diskPath;
    }
}
