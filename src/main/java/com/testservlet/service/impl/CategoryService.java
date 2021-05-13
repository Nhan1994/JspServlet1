package com.testservlet.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.testservlet.dao.ICategoryDAO;
import com.testservlet.model.CategoryModel;
import com.testservlet.service.ICategoryService;

public class CategoryService implements ICategoryService {
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDAO.findOne(id);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		return categoryDAO.findOneByCategoryCode(code);
	}

}
