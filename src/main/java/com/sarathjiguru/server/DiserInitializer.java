package com.sarathjiguru.server;

import com.sarathjiguru.memory.DiMemory;
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

    private final Replication replication;
    private final DiskWriter dw;

    public DiserInitializer(Replication replication, DiskWriter dw) {
        this.replication = replication;
        this.dw = dw;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        pipeline.addLast("handler", new ServerHandler(replication, new DiMemory(dw)));
    }

}
