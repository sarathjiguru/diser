package com.sarathjiguru.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by sarath on 18/11/17.
 */
public class ServerConfiguratorTest {

    private ServerConfigurator serverConfigurator;
    YAMLFactory yf = new YAMLFactory();

    @Before
    public void setup() throws IOException {
        ObjectMapper objectMapper = Jackson.newObjectMapper(yf);
        serverConfigurator = objectMapper.readValue(this.getClass().getClassLoader().getResource("server-config.yml"), ServerConfigurator.class);
    }

    @Test
    public void getMasterPort() throws Exception {
        System.out.println(serverConfigurator.getMasterPort());
    }

    @Test
    public void getReplicators() throws Exception {
        System.out.println(serverConfigurator.getReplicators());
    }

}