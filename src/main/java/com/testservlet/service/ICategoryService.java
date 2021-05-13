package com.testservlet.service;

import java.util.List;

import com.testservlet.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();

	CategoryModel findOne(Long id);

	CategoryModel findOneByCode(String code);
}
