package com.testservlet.dao;

import java.util.List;

import com.testservlet.model.CategoryModel;

public interface ICategoryDAO{
	List<CategoryModel> findAll();
}
