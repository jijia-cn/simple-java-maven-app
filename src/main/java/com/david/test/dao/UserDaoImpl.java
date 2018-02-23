package com.david.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.david.test.domain.User;
import com.david.test.exception.UserException;
import com.david.test.mapper.UserDao;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public int addUser(User user) throws UserException {
		// TODO Auto-generated method stub
		SqlSession session = sessionTemplate.getSqlSessionFactory().openSession();
		UserDao userDao = (UserDao) session.getMapper(UserDao.class);
		return userDao.addUser(user); 
	}

	public int removeUser(Integer id) throws UserException {
		// TODO Auto-generated method stub
		SqlSession session = sessionTemplate.getSqlSessionFactory().openSession();
		UserDao userDao = (UserDao) session.getMapper(UserDao.class);
		return userDao.removeUser(id);
	}

	public int updateUser(User user) throws UserException {
		// TODO Auto-generated method stub
		SqlSession session = sessionTemplate.getSqlSessionFactory().openSession();
		UserDao userDao = (UserDao) session.getMapper(UserDao.class);
		return userDao.updateUser(user);
	}

	public List<User> findUsers() throws UserException {
		// TODO Auto-generated method stub
		SqlSession session = sessionTemplate.getSqlSessionFactory().openSession();
		UserDao userDao = (UserDao) session.getMapper(UserDao.class);
		return userDao.findUsers();
	}

}
