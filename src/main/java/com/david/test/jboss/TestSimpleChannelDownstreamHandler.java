package com.david.test.jboss;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;

public class TestSimpleChannelDownstreamHandler extends SimpleChannelDownstreamHandler{

	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handleDownstream....");
		super.handleDownstream(ctx, e);
	}

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("writeRequested....");
		super.writeRequested(ctx, e);
	}

	@Override
	public void bindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("bindRequested....");
		super.bindRequested(ctx, e);
	}

	@Override
	public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("connectRequested....");
		super.connectRequested(ctx, e);
	}

	@Override
	public void setInterestOpsRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("setInterestOpsRequested....");
		super.setInterestOpsRequested(ctx, e);
	}

	@Override
	public void disconnectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("disconnectRequested....");
		super.disconnectRequested(ctx, e);
	}

	@Override
	public void unbindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("unbindRequested....");
		super.unbindRequested(ctx, e);
	}

	@Override
	public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("closeRequested....");
		super.closeRequested(ctx, e);
	}

}
