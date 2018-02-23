package com.david.test.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Array;

public class ReturnMapUtils {
	
	
	public static Map<String, Object> mapOk(String message){
		Map<String,Object> returnMap = new HashMap();
		returnMap.put("data", new ArrayList());
		returnMap.put("message", message);
		returnMap.put("success", true);
		return returnMap;
	}
	
	public static Map<String, Object> mapOk(List datas){
		Map<String,Object> returnMap = new HashMap();
		returnMap.put("data", datas);
		returnMap.put("message", "查询成功");
		returnMap.put("success", true);
		return returnMap;
	}
	
	public static Map<String, Object> mapError(String message){
		Map<String,Object> returnMap = new HashMap();
		returnMap.put("data", new ArrayList());
		returnMap.put("message", message);
		returnMap.put("success", false);
		return returnMap;
		
	}

}
