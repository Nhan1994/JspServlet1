package com.testservlet.filter;

import com.testservlet.model.UserModel;
import com.testservlet.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getRequestURI();
        if (url.contains("/admin")){
            UserModel user = (UserModel) SessionUtil.getInstance().getValue(httpRequest, "USERMODEL");
            if (user != null) {
                if (user.getRole().getCode().equals("ADMIN")) {
                    chain.doFilter(request, response);
                } else if (user.getRole().getCode().equals("USER")){
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?action=login&message=not_permission&alert=danger");
                }
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?action=login&message=not_login&alert=danger");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
