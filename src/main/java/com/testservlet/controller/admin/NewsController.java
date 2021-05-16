package com.testservlet.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testservlet.model.News;
import com.testservlet.paging.PageRequest;
import com.testservlet.paging.Pageable;
import com.testservlet.service.ICategoryService;
import com.testservlet.service.INewService;
import com.testservlet.sort.Sorter;
import com.testservlet.util.FormUtils;

@WebServlet(urlPatterns = { "/admin-news-list" })
public class NewsController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735525935292337666L;

	@Inject
	private INewService newService;

	@Inject
	private ICategoryService categoryService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		News model = FormUtils.toModel(News.class, req);
		String view = "";
		if (model.getType().equals("list")) {
			view = "/views/admin/news/list.jsp";
			Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			model.setResults(newService.findAll(pageable));
			model.setTotalItem(newService.getTotalItems());
			model.setTotalPage((int) Math.ceil((double)model.getTotalItem() / model.getMaxPageItem()));
		} else if (model.getType().equals("edit")){
			view = "/views/admin/news/edit.jsp";
			if (model.getId() != null) {
				model = newService.findOne(model.getId());
			}
			req.setAttribute("categories", categoryService.findAll());
		}

		if (req.getAttribute("message") != null){
			String msgResponse = "";
			String alert = "";
			String msg = (String) req.getAttribute("message");
			if (msg.equals("insert_success")){
				msgResponse ="Insert succesfully";
				alert = "success";
			} else if (msg.equals("update_success")){
				msgResponse ="Update succesfully";
				alert = "success";
			} else if (msg.equals("error")){
				msgResponse ="Error";
				alert = "danger";
			}
			req.setAttribute("messageResponse", msgResponse);
			req.setAttribute("alert", alert);
		}
		req.setAttribute("model", model);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
		requestDispatcher.forward(req, resp);
	}
}
