package com.david.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.test.domain.User;
import com.david.test.exception.UserException;
import com.david.test.mapper.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public boolean addUser(String userName, Integer age) throws UserException {
		// TODO Auto-generated method stub
		userDao.addUser(new User(userName, age));
		return true;
	}

	@Transactional
	public boolean removeUser(Integer id) throws UserException {
		// TODO Auto-generated method stub
		userDao.removeUser(id);
		return true;
	}

	@Transactional
	public boolean updateUser(User user) throws UserException {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
		return true;
	}

	@Transactional(readOnly=true)
	public List<User> findUsers() throws UserException {
		// TODO Auto-generated method stub
		return userDao.findUsers();
	}
	
	
}
