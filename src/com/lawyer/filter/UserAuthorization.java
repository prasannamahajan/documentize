package com.lawyer.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import com.lawyer.entity.User;

@WebFilter("/UserAuthorization")
public class UserAuthorization implements Filter {

	public UserAuthorization() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = request.getServletContext().getContextPath();
		HttpSession session = req.getSession(false);
		if (session == null) {
			//if no active session then redirect to login.html
			res.sendRedirect(path + "/login.html");
		} else {
			if (session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				if (user.getRole().equals("user")
						|| user.getRole().equals("admin")) {

					chain.doFilter(request, response);

					return;
				}

				else {
					//if role does not have previlage to access
					res.sendRedirect(path + "/response.jsp?id=6");
					return;
				}
			} else {
				//user attribute null hence
				session.invalidate();
				res.sendRedirect(path + "/login.html");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
