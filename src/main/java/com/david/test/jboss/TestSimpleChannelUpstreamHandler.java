package com.david.test.jboss;

import java.util.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.WriteCompletionEvent;

public class TestSimpleChannelUpstreamHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handleUpstream：" + new Date());
		super.handleUpstream(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO Auto-generated method stub
		Channel channel = ctx.getChannel();
		//唯一标识
		System.out.println("messageReceived id：" + channel.getId());
		ChannelBuffer channelBuffer = (ChannelBuffer) e.getMessage();
		System.out.println("message: "+channelBuffer.toString());
		for (byte b : channelBuffer.array()) {
			System.out.print((char)b + " ");
		}
		System.out.println();
		//send ok msg
		String message = "\nrecv ok\n";
		ChannelBuffer sendMsg = ChannelBuffers.buffer(message.length());
		sendMsg.writeBytes(message.getBytes());
		ctx.getChannel().write(sendMsg);
		
		super.messageReceived(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("exceptionCaught：" + e.getCause());
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channelOpen：" + new Date());
		super.channelOpen(ctx, e);
	}

	@Override
	public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channelBound：" + new Date());
		super.channelBound(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channelConnected：" + new Date());
		ChannelBuffer buffer = ChannelBuffers.buffer(10);   //表示创建一个10个字节的包.  
		buffer.writeByte(126); // 给数据写入一个byte类型的占一个字节.
		for (byte b : buffer.array()) {
			System.out.print(b + ",");
		}
		for (byte b : buffer.array()) {
			System.out.print(b + " ");
		}
		System.out.println();
		e.getChannel().write(buffer);
		super.channelConnected(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channelDisconnected：" + new Date());
		super.channelDisconnected(ctx, e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("channelClosed：" + new Date());
		super.channelClosed(ctx, e);
	}

	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("writeComplete：" + new Date());
		super.writeComplete(ctx, e);
	}

}
