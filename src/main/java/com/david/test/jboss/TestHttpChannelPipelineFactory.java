package com.david.test.jboss;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;

public class TestHttpChannelPipelineFactory implements ChannelPipelineFactory {

	private final ExecutionHandler executionHandler;

	public TestHttpChannelPipelineFactory(ExecutionHandler executionHandler) {    
        this.executionHandler = executionHandler;    
    }

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		// TODO Auto-generated method stub
		return Channels.pipeline(
				executionHandler,
				new TestSimpleChannelUpstreamHandler(),
				new TestSimpleChannelDownstreamHandler()
				);
	}

}
