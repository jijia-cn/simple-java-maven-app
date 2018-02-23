package com.david.test.mina.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class NotifierEncoder extends ProtocolEncoderAdapter
{
	private static Logger logger = Logger.getLogger(NotifierEncoder.class);
	
	@Override
	public void encode(IoSession iosession, Object message,
			ProtocolEncoderOutput out) throws Exception
	{
		
	}
}