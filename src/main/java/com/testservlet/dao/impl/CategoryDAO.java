package com.testservlet.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.testservlet.dao.ICategoryDAO;
import com.testservlet.mapper.CategoryMapper;
import com.testservlet.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	public Connection connection;

	public List<CategoryModel> findAll() {
		String sql = "Select * from category";
		return query(sql, new CategoryMapper());
	}

}
