package com.david.test.logger;

import org.apache.log4j.Logger;

/**
 * 日志控制器
 * @author jia ji
 *
 */
public class LoggerManager {

	private final static Logger quartzLogger = Logger.getLogger("quartzLogger");
	private final static Logger infoLogger = Logger.getLogger("infoLogger");
	private final static Logger serverLogger = Logger.getLogger("serverLogger");
	private final static Logger deviceLogger = Logger.getLogger("deviceLogger");
	private final static Logger recvDataLogger = Logger.getLogger("recvDataLogger");
	private final static Logger sendDataLogger = Logger.getLogger("sendDataLogger");
	private final static Logger debugLogger = Logger.getLogger("debugLogger");
	private final static Logger warnLogger = Logger.getLogger("warnLogger");
	private final static Logger errorLogger = Logger.getLogger("errorLogger");
	
	public static void quartzInfo(Object message){
		quartzLogger.info(message);
	}
	
	public static void info(Object message){
		infoLogger.info(message);
	}
	
	public static void debug(Object message){
		debugLogger.debug(message);
	}
	
	public static void warn(Object message){
		warnLogger.warn(message);
	}
	
	public static void error(Object message){
		errorLogger.error(message);
	}
	
	public static void recvData(Object message){
		recvDataLogger.info(message);
	}
	
	public static void sendData(Object message){
		sendDataLogger.info(message);
	}
	
	public static void serverInfo(Object message){
		serverLogger.info(message);
	}
	
	public static void deviceInfo(Object message){
		deviceLogger.info(message);
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		infoLogger.info("logger manager");
		debug("test debug message");
	}

}
