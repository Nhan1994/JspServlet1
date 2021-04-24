package com.testservlet.service;

import java.util.List;

import com.testservlet.model.News;
import com.testservlet.paging.Pageable;

public interface INewService {
	List<News> findNewByCategoryId(Long categoryId);
	
	News save(News newModel);
	
	News update(News newModel);
	
	void delete (Long[] ids);
	
	List<News> findAll(Pageable pageable);
	
	int getTotalItems();
}
