package com.david.test.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.david.test.SpringDemoApplication;
import com.david.test.exception.UserException;
import com.david.test.mapper.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class UserDaoTest {

	@Autowired
	public UserDao userDao;
	
	@Test
	@Transactional(readOnly=true)
	public void listUsers(){
		try {
			System.out.println(userDao.findUsers());
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
