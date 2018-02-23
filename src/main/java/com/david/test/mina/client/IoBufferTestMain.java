package com.david.test.mina.client;

import java.util.Random;

import org.apache.mina.core.buffer.IoBuffer;

public class IoBufferTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] totalTestBytes = new byte[]{(byte) 0xab,(byte) 0x12,(byte) 0x32,(byte) 0x65,(byte) 0xef,(byte) 0xdd};
		Random random = new Random();
		Random byteRandomIndex = new Random();
		while(true){
			IoBuffer ioBuffer = IoBuffer.allocate(2,false);
			ioBuffer.setAutoExpand(true);
			for(int i=0;i<random.nextInt(10);i++){
				ioBuffer.put(totalTestBytes[byteRandomIndex.nextInt(totalTestBytes.length)]);
			}
			System.out.println("capacity: "+ioBuffer.capacity());
			ioBuffer.flip();
			for(int i=0;i<ioBuffer.position();i++){
				System.out.print(Integer.toHexString(ioBuffer.get())+" ");
			}
			System.out.println();
			byte[] bytes = ioBuffer.array();
			for(byte b:bytes){
				System.out.print(Integer.toHexString(b & 0xff)+" ");
			}
			System.out.println(bytes);
			
			System.out.println(ioBuffer.capacity());
			System.out.println(ioBuffer.position());
			System.out.println(ioBuffer.limit());
			ioBuffer.clear();
			System.out.println(ioBuffer.capacity());
			System.out.println(ioBuffer.position());
			System.out.println(ioBuffer.limit());
			System.out.println(bytes);
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
