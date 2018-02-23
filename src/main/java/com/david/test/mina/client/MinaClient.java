package com.david.test.mina.client;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * mina客户端
 * 
 * @author David Ji
 *
 */

public class MinaClient {
	private static Integer PORT = 7777;
	private static Integer CONNECT_TIMEOUT = 30000;
	private static String HOSTNAME = "127.0.0.1";

	public static void main(String[] args) {
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);

		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.setHandler(new TestMinaHandler());
		IoSession session;

		for (;;) {
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
				future.awaitUninterruptibly();
				session = future.getSession();
				break;
			} catch (RuntimeIoException e) {
				e.printStackTrace();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		final IoSession uSession = session;
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(uSession);
				byte[] totalTestBytes = new byte[]{(byte) 0xab,(byte) 0x12,(byte) 0x32,(byte) 0x65,(byte) 0xef,(byte) 0xdd};
				Random random = new Random();
				Random byteRandomIndex = new Random();
				while (true) {
					try {
						int messageSize = random.nextInt(50);
						IoBuffer ioBuffer = IoBuffer.allocate(messageSize);
						ioBuffer.put((byte)0x7e);
						for(int i=0;i<messageSize-2;i++){
							ioBuffer.put(totalTestBytes[byteRandomIndex.nextInt(totalTestBytes.length)]);
						}
						ioBuffer.put((byte)0x7e);
						uSession.write(ioBuffer.flip());
						ioBuffer.free();
						ioBuffer.clear();
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		// wait until the summation is done
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}
}
