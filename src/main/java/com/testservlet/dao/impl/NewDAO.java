package com.testservlet.dao.impl;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.testservlet.dao.INewDAO;
import com.testservlet.mapper.NewMapper;
import com.testservlet.model.News;
import com.testservlet.paging.Pageable;
import org.apache.commons.lang.StringUtils;

public class NewDAO extends AbstractDAO<News> implements INewDAO {

    public Connection connection;

    @Override
    public List<News> findByCategoryId(Long categoryId) {
        String sql = "Select * from news where categoryid = ?";
        return query(sql, new NewMapper(), categoryId);
    }

    @Override
    public Long save(News newModel) {
        String sql = "INSERT INTO news (title, content , thumbnail, shortdescription, categoryid, createddate, createdby ) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, newModel.getTitle(), newModel.getContent(), newModel.getThumbnail(),
                newModel.getShortDescription(), newModel.getCategoryId(), newModel.getCreatedDate(), "ADMIN");
    }

    @Override
    public News findOne(Long id) {
        String sql = "Select * from news where id = ?";
        List<News> news = query(sql, new NewMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public void update(News newModel) {
        StringBuilder sb = new StringBuilder("UPDATE news SET ");
        sb.append("title=?, thumbnail=?, shortdescription=?, content=?, categoryid=?, modifieddate=?, modifiedby=? ");
        sb.append("WHERE id=?");
        update(sb.toString(), newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
                newModel.getContent(), newModel.getCategoryId(), LocalDate.now().toString(), "ADMIN", newModel.getId());
    }

    @Override
    public void delete(long id) {
        News news = findOne(id);
        StringBuilder sbComment = new StringBuilder("DELETE FROM comment ");
        sbComment.append("WHERE new_id = ?");
        delete(sbComment.toString(), news.getId());

        StringBuilder sbNews = new StringBuilder("DELETE FROM news ");
        sbNews.append("WHERE id = ?");
        delete(sbNews.toString(), news.getId());
    }

    @Override
    public List<News> findNewsById(long[] ids) {
        List<News> news = new ArrayList<News>();
        for (long id : ids) {
            String sql = "Select * from news where id = ?";
            news.addAll(query(sql, new NewMapper(), id));
        }
        return news;
    }

    @Override
    public List<News> findAll(Pageable pageable) {
        List<News> news = new ArrayList<News>();
        StringBuilder str = new StringBuilder("Select * from news");
        if (pageable.getSorter() != null &&
                StringUtils.isNotBlank(pageable.getSorter().getSortBy()) &&
                StringUtils.isNotBlank(pageable.getSorter().getSortName())) {
            str.append(" ORDER BY " + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy());
        }
        if (pageable.getOffSet() != null && pageable.getLimit() != null) {
            str.append(" LIMIT ?, ?");
            news = query(str.toString(), new NewMapper(), pageable.getOffSet(), pageable.getLimit());
        } else {
            news = query(str.toString(), new NewMapper());
        }
        return news;
    }

    @Override
    public int getTotalItems() {
        String sql = "Select count(*) from news";
        return count(sql);
    }

}
