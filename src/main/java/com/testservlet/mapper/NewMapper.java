package com.testservlet.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.testservlet.model.News;

public class NewMapper implements RowMapper<News>{

	@Override
	public News mapRow(ResultSet rs) {
		News news = new News();
		try {
			news.setId(rs.getLong("id"));
			news.setTitle(rs.getString("title"));
			news.setContent(rs.getString("content"));
			news.setCategoryId(rs.getLong("categoryId"));
			news.setThumbnail(rs.getString("thumbnail"));
			news.setShortDescription(rs.getString("shortDescription"));
			news.setCreatedDate(rs.getTimestamp("createddate"));
			news.setCreatedBy(rs.getString("createdby"));
			news.setModifiedDate(rs.getTimestamp("modifieddate"));
			if (rs.getTimestamp("modifieddate") != null) {
				news.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
			if (rs.getString("modifiedby") != null) {
				news.setModifiedBy(rs.getString("modifiedby"));
			}
			news.setModifiedBy(rs.getString("modifiedby"));
		} catch (SQLException e) {
			return null;
		}
		return news;
	}

}
