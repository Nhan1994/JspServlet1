package com.testservlet.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testservlet.model.UserModel;
import com.testservlet.service.IUserService;
import com.testservlet.util.FormUtils;
import com.testservlet.util.SessionUtil;

@WebServlet(urlPatterns = { "/homepage" ,"/login","/logout"})
public class HomeController extends HttpServlet{
	/**
	 * 
	 */
	@Inject
	private IUserService userService;
	
	private static final long serialVersionUID = 3678889298934202525L;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
			requestDispatcher.forward(req, resp);
		} else if (action != null && action.equals("logout")){
			SessionUtil.getInstance().removeValue(req,"USERMODEL");
			resp.sendRedirect(req.getContextPath() + "/homepage");
		} else {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
			requestDispatcher.forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel user = FormUtils.toModel(UserModel.class, req);
			user = userService.findByUserNameAndPasswordAndStatus(user.getUserName(), user.getPassword(), 1);
			if (user != null) {
				SessionUtil.getInstance().putValue(req,"USERMODEL", user);
				if (user.getRole().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath() + "/homepage");
				} else if (user.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/admin-homepage");
				}
			} else {
				resp.sendRedirect(req.getContextPath()+ "/login?action=login&message=invalid_password&alert=danger");
			}
		}
	}
}
