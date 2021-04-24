package com.testservlet.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testservlet.model.News;
import com.testservlet.model.UserModel;
import com.testservlet.service.INewService;
import com.testservlet.util.HttpUtil;
import com.testservlet.util.SessionUtil;

@WebServlet(urlPatterns = { "/news" })
public class NewAPI extends HttpServlet {

	@Inject
	private INewService newService;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5903119943668066254L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		News news = HttpUtil.of(req.getReader()).toModel(News.class);
		news.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		News newsResult = newService.save(news);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), newsResult);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		News newsUpdate = HttpUtil.of(req.getReader()).toModel(News.class);
		newService.delete(newsUpdate.getIds());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		News newsUpdate = HttpUtil.of(req.getReader()).toModel(News.class);
		newsUpdate.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		News newsResult = newService.update(newsUpdate);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getOutputStream(), newsResult);
	}
}
