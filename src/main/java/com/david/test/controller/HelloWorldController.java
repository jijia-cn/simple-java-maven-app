package com.david.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.david.test.domain.User;

@Controller
public class HelloWorldController {
	
	@RequestMapping(value="hello")
	public String helloPage(){
		return "hello";
	}
	
	@RequestMapping(value="user")
	@ResponseBody
	public User user(){
		return new User("zhangsan", 30, "123456");
	}
	
	@RequestMapping(value="users")
	@ResponseBody
	public List<User> users(){
		List<User> users = new ArrayList<>();
		users.add(new User("zhangsan1", 36, "123456"));
		users.add(new User("lisi", 33, "123456"));
		users.add(new User("wangwu", 34, "123456"));
		users.add(new User("赵六", 35, "123456"));
		return users;
	}
	
	@RequestMapping(value="usermap")
	@ResponseBody
	public Map<String,Object> usermap(){
		Map<String, Object> returnDatas = new HashMap<>();
		List<User> users = new ArrayList<>();
		users.add(new User("zhangsan1", 36, "123456"));
		users.add(new User("lisi", 33, "123456"));
		users.add(new User("wangwu", 34, "123456"));
		users.add(new User("赵六", 35, "123456"));
		returnDatas.put("total", users.size());
		returnDatas.put("data", users);
		returnDatas.put("success", true);
		return returnDatas;
	}

}
