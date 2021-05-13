package com.testservlet.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.enterprise.inject.New;
import javax.inject.Inject;

import com.testservlet.dao.INewDAO;
import com.testservlet.dao.impl.NewDAO;
import com.testservlet.model.News;
import com.testservlet.paging.Pageable;
import com.testservlet.service.INewService;

public class NewService implements INewService{
	
	@Inject
	private INewDAO newDao;

	@Inject
	private CategoryService categoryService;

	@Override
	public List<News> findNewByCategoryId(Long categoryId) {
		return newDao.findByCategoryId(categoryId);
	}

	@Override
	public News save(News newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setCreatedBy("admin");
		newModel.setCategoryId(categoryService.findOneByCode(newModel.getCategoryCode()).getId());
		Long id = newDao.save(newModel);
		return newDao.findOne(id);
	}

	@Override
	public News update(News newsUpdate) {
		News existingNew = newDao.findOne(newsUpdate.getId());
		newsUpdate.setCreatedDate(existingNew.getCreatedDate());
		newsUpdate.setCreatedBy(existingNew.getCreatedBy());
		newsUpdate.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		newsUpdate.setCategoryId(categoryService.findOneByCode(newsUpdate.getCategoryCode()).getId());
		newsUpdate.setModifiedBy("admin");
		newDao.update(newsUpdate);
		return newDao.findOne(newsUpdate.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for (long id: ids) {
			newDao.delete(id);
		}
	}

	@Override
	public List<News> findAll(Pageable pageable) {
		return newDao.findAll(pageable);
	}

	@Override
	public int getTotalItems() {
		return newDao.getTotalItems();
	}

	@Override
	public News findOne(long id) {
		News news = newDao.findOne(id);
		news.setCategoryCode(categoryService.findOne(news.getCategoryId()).getCode());
		return news;
	}

}
