package com.testservlet.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.testservlet.dao.ICategoryDAO;
import com.testservlet.mapper.CategoryMapper;
import com.testservlet.mapper.NewMapper;
import com.testservlet.model.CategoryModel;
import com.testservlet.model.News;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	public Connection connection;

	public List<CategoryModel> findAll() {
		String sql = "Select * from category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "Select * from category where id = ?";
		List<CategoryModel> categoryModels = query(sql, new CategoryMapper(), id);
		return categoryModels.isEmpty() ? null : categoryModels.get(0);
	}

	@Override
	public CategoryModel findOneByCategoryCode(String code) {
		String sql = "Select * from category where code = ?";
		List<CategoryModel> categoryModels = query(sql, new CategoryMapper(), code);
		return categoryModels.isEmpty() ? null : categoryModels.get(0);
	}

}
