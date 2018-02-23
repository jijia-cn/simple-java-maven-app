package com.david.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.test.domain.User;
import com.david.test.exception.UserException;
import com.david.test.service.UserService;
import com.david.test.utils.ReturnMapUtils;

@Controller
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="addUser")
	public @ResponseBody Map<String, Object> addUser(@RequestParam String userName,@RequestParam Integer age){
		try {
			userService.addUser(userName, age);
			return ReturnMapUtils.mapOk("添加成功");
		} catch (UserException e) {
			e.printStackTrace();
			return ReturnMapUtils.mapError(e.getMessage());
		}
	}
	
	@RequestMapping(value="updateUser")
	public @ResponseBody Map<String, Object> updateUser(@RequestParam Integer id,@RequestParam String userName,@RequestParam Integer age){
		try {
			User user = new User(userName, age);
			user.setId(id);
			userService.updateUser(user);
			return ReturnMapUtils.mapOk("更新成功");
		} catch (UserException e) {
			e.printStackTrace();
			return ReturnMapUtils.mapError(e.getMessage());
		}
	}
	
	@RequestMapping(value="removeUser")
	public @ResponseBody Map<String, Object> removeUser(@RequestParam Integer id){
		try {
			userService.removeUser(id);
			return ReturnMapUtils.mapOk("更新成功");
		} catch (UserException e) {
			e.printStackTrace();
			return ReturnMapUtils.mapError(e.getMessage());
		}
	}
	

	@RequestMapping(value="listUser")
	public @ResponseBody Map<String, Object> listUser(){
		try {
			return ReturnMapUtils.mapOk(userService.findUsers());
		} catch (UserException e) {
			e.printStackTrace();
			return ReturnMapUtils.mapError(e.getMessage());
		}
	}

}
