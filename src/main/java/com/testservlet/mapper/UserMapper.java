package com.testservlet.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.testservlet.model.RoleModel;
import com.testservlet.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		UserModel user = new UserModel();
		try {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFullName(rs.getString("fullname"));
			user.setStatus(rs.getInt("status"));
			user.setRoleId(rs.getLong("roleid"));
			user.setCreatedBy(rs.getString("createdby"));
			if (rs.getTimestamp("modifieddate") != null) {
				user.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
			if (rs.getString("modifiedby") != null) {
				user.setModifiedBy(rs.getString("modifiedby"));
			}
			try {
				RoleModel role = new RoleModel();
				role.setCode(rs.getString("code"));
				role.setName(rs.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			return null;
		}
		return user;
	}

}
