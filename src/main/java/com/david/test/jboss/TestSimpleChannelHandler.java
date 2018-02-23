package com.david.test.jboss;

import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * SimpleChannelHandler 实现了SimpleChannelUpstream,SimpleChannelDownstream接口
 * 故该适配器具有收发消息事件处理的能力
 * 若需要单独监听收或者发，可单独实现了SimpleChannelUpstream或者SimpleChannelDownstream
 * @author jia ji
 *
 */
public class TestSimpleChannelHandler extends SimpleChannelHandler{
	
	
}
