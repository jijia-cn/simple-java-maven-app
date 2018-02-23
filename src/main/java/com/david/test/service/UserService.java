package com.david.test.service;

import java.util.List;

import com.david.test.domain.User;
import com.david.test.exception.UserException;

public interface UserService {

	boolean addUser(String userName,Integer age) throws UserException;
	
	boolean removeUser(Integer id) throws UserException;
	
	boolean updateUser(User user) throws UserException;
	
	List<User> findUsers() throws UserException;
}
