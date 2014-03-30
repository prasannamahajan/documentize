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
import com.lawyer.entity.User;
import com.lawyer.cookie.Cookies;
import com.lawyer.user.service.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/LoginControl")
public class LoginControl implements Filter {
	private Logger logger = LoggerFactory.getLogger(LoginControl.class);
	public LoginControl() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String path = request.getServletContext().getContextPath();
		HttpSession session = req.getSession(false);
		logger.info("Session : {}",session);
		UserAccount account = new UserAccount();
		if (session == null) {
			String email = Cookies.getCookieValue(req, "email");
			logger.info("email from cookie email :{}",email);
			User user;
			if(email!=null)
			 user = account.findUserByEmail(email);
			else 
				user = null;
			logger.info("user : {}",user);
			if (user != null) {
				req.getSession().setAttribute("email", email);
				req.getSession()
						.setAttribute("role", user.getRole().toString());
				req.getSession().setAttribute("user", user);
				req.getSession().setAttribute("userId", user.getUser_id());
				String role = user.getRole();
				Cookies.addCookie(res,"email",email,4*3600*24);
				
				if(role.equals("admin"))
					res.sendRedirect(path+"/admin/home.html");
				if(role.equals("user"))
					res.sendRedirect(path+"/user/home.html");
				if(role.equals("lawyer"))
					res.sendRedirect(path+"/lawyer/home.html");
			}
			else
				{
				res.sendRedirect(path+"/login.html");
				return;
				}
		}
		else
		{
			logger.info("If session not null , forward it");
		chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
