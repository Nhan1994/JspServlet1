package com.testservlet.controller.admin;

import com.testservlet.service.ICategoryService;
import com.testservlet.service.INewService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/admin-homepage" })
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -731239602150291377L;
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private INewService newService;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		UserModel user = new UserModel();
//		user.setUserName("Barry");
//		req.setAttribute("model", user);
//		req.setAttribute("categories", categoryService.findAll());
//		News newModel = new News();
//		newModel.setTitle("New 3");
//		newModel.setContent("content 3");
//		newModel.setCategoryId(1l);
//		newService.save(newModel);
//		req.setAttribute("news", newService.findNewByCategoryId(1l));
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/home.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
