/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.sarathjiguru.server;

import com.sarathjiguru.config.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.cli.*;

/**
 * Echoes back any received data from a client.
 */
public final class Server {

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        Option option;

        option = new Option("c", "config", true, "Server configuration file");
        option.setRequired(false);
        options.addOption(option);

        option = new Option("p", "port", true, "Port to run the server");
        option.setRequired(false);
        options.addOption(option);


        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
            ServerConfig servConfig;
            if (cmd.hasOption("p")) {
                servConfig = new ServerConfig(Integer.parseInt(cmd.getOptionValue("p")));
                startServer(servConfig);

            } else if (cmd.hasOption("c")) {
                servConfig = new ServerConfig(cmd.getOptionValue("c"));
                startServer(servConfig);
            } else {
                System.exit(-1);
            }

        } catch (ParseException e) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(Server.class.getCanonicalName(), options);
            System.exit(0);
        }


    }

    private static void startServer(ServerConfig servConfig) throws InterruptedException {
        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new DiserInitializer(servConfig.replication()));
            ChannelFuture future = bootstrap.bind(servConfig.masterPort()).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}