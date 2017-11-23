package com.sarathjiguru.server;

import com.sarathjiguru.config.ServerConfig;
import com.sarathjiguru.memory.DiskWriter;
import com.sarathjiguru.replication.Replication;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by sarath on 11/11/17.
 */
public class DiserInitializer extends ChannelInitializer<SocketChannel> {

    private ServerConfig serverConfig;

    public DiserInitializer(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        ServerHandler handler = new ServerHandler(serverConfig);
        pipeline.addLast("handler", handler);
    }

}
