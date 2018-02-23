package com.david.test.jboss;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

public class TestServerBootstrap {

	private static Integer httpPort = 6666;

	private ServerBootstrap serverBootStrap;
	private ExecutionHandler executionHandler;

	public TestServerBootstrap(){
		System.out.println("ServerBootstrap: 开始启动监听....");
		serverBootStrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		executionHandler = new ExecutionHandler(new OrderedMemoryAwareThreadPoolExecutor(16, 1048576, 1048576));
		serverBootStrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				return Channels.pipeline(
						executionHandler,
						new TestSimpleChannelUpstreamHandler(),
						new TestSimpleChannelDownstreamHandler());
			}
		});
		serverBootStrap.setOption("child.tcpNoDelay", true);    
		serverBootStrap.setOption("child.keepAlive", true);    
		serverBootStrap.bind(new InetSocketAddress(httpPort)); //端口开始监听   
		System.out.println("ServerBootstrap: 启动监听....");
	}

}
