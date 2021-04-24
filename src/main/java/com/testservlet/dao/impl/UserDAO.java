package com.testservlet.dao.impl;

import java.util.List;

import com.testservlet.dao.IUserDAO;
import com.testservlet.mapper.UserMapper;
import com.testservlet.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder query = new StringBuilder("Select * from user u ");
		query.append("JOIN role r where r.id = u.roleid ");
		query.append("AND u.username = ? AND u.password = ? AND u.status = ?");
		List<UserModel> users = query(query.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
	}

}
