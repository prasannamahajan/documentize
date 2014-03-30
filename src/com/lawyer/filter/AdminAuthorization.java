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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lawyer.entity.User;


@WebFilter("/AdminAuthorization")
public class AdminAuthorization implements Filter {
	private Logger logger = LoggerFactory.getLogger(AdminAuthorization.class);
	public AdminAuthorization() {
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
		logger.info("Session : {},Path : {}",session,path);
		if (session == null) {
			//if no active session then redirect to login.html
			res.sendRedirect(path + "/login.html");
		} else {
			if (session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				logger.info("User Role : {}",user.getRole());
				if (user.getRole().equals("admin")) {
					logger.info("forward possible ");
					chain.doFilter(request, response);

					return;
				}

				else {
					//if role does not have previlage to access
					logger.info("Show error message");
					res.sendRedirect(path + "/response.jsp?id=6");
					return;
				}
			} else {
				//user attribute null hence
				logger.info("User is null user");
				session.invalidate();
				res.sendRedirect(path + "/login.html");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
