package com.david.test.mapper;

import java.util.List;

import com.david.test.domain.User;
import com.david.test.exception.UserException;

public interface UserDao {
	
	int addUser(User user) throws UserException;
	
	int removeUser(Integer id) throws UserException;
	
	int updateUser(User user) throws UserException;
	
	List<User> findUsers() throws UserException;
}
