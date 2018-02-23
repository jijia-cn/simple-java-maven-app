package com.david.test.mina.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;

import org.apache.mina.filter.codec.ProtocolDecoder;

import org.apache.mina.filter.codec.ProtocolEncoder;

public class NotifierCoderFactory implements ProtocolCodecFactory
{
	public NotifierEncoder encoder;
	public NotifierDecoder decoder;

	public NotifierCoderFactory() throws Exception
	{
		this.encoder = new NotifierEncoder();
		this.decoder = new NotifierDecoder();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return this.decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		
		return this.encoder;
	}
}
