package org.okboom.bard.gateway.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;

/**
 * @author tookbra
 */
public class ProxyHandler extends SimpleChannelInboundHandler<HttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        System.out.printf("1");
    }
}
