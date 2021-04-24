package com.testservlet.dao;

import java.util.List;

import com.testservlet.mapper.RowMapper;

public interface GenericDAO<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper,  Object... paramters);
	
	void update(String sql, Object... parameters);
	
	Long insert(String sql, Object... parameters);
	
	void delete(String sql, Object... parameters);
	
	int count(String sql, Object... parameters);
}