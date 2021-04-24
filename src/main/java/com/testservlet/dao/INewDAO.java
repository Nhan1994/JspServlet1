package com.testservlet.dao;

import java.util.List;

import com.testservlet.model.News;
import com.testservlet.paging.Pageable;

public interface INewDAO extends GenericDAO<News>{
	List<News> findByCategoryId(Long categoryId);
	
	Long save(News newModel);
	
	News findOne(Long id);
	
	void update(News newModel);
	
	void delete(long ids);
	
	List<News> findNewsById(long[] ids);
	
	List<News> findAll(Pageable pageable);
	
	int getTotalItems();
}
