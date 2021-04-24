package com.testservlet.service.impl;

import com.testservlet.dao.IUserDAO;
import com.testservlet.model.UserModel;
import com.testservlet.service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDao;

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDao.findByUserNameAndPasswordAndStatus(userName, password, status);
	}
	
}
