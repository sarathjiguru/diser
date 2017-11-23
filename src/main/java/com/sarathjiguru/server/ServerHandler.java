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
import com.sarathjiguru.memory.DiMemory;
import com.sarathjiguru.memory.DiserCommand;
import com.sarathjiguru.memory.DiskWriter;
import com.sarathjiguru.replication.Replication;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;

/**
 * Handler implementation for the Diser.
 */
@Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    public static final String CARRIAGE_RETURN = "\r\n";
    private final Replication replication;
    private final DiMemory diM;

    public ServerHandler(ServerConfig serverConfig) throws IOException {
        this.replication = serverConfig.replication();
        DiskWriter dw = new DiskWriter(serverConfig.getDiskPath());
        this.diM = new DiMemory(dw);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws IOException {
        System.out.println("server received:" + msg);
        DiserCommand diserCommand = new DiserCommand(msg);
        replication.replicate(diserCommand);
        ctx.channel().writeAndFlush(diM.result(diserCommand) + CARRIAGE_RETURN);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}