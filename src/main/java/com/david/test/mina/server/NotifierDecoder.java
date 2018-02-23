package com.david.test.mina.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 解析类
 */
public class NotifierDecoder extends CumulativeProtocolDecoder {
	private static Logger logger = Logger.getLogger(NotifierDecoder.class);
	private static Integer i = 0;

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		int size = in.capacity();
		System.out.println("waiting..."+size);
		if(in.prefixedDataAvailable(3)){
			in.flip();
			out.write(in.array());
			return false;
		}
		return true;
	}
	
	
}