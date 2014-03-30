package com.lawyer.webservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lawyer.cookie.Cookies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(Logout.class);
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try{
		String email = request.getSession().getAttribute("email").toString();
		String role = request.getSession().getAttribute("role").toString();
		Cookies.removeCookie(response, "email");
		request.getSession().invalidate();
		logger.info("Logout successful for email: {} of role:{}",email,role);
		}
		catch(NullPointerException e)
		{
			logger.error("Error during logout");
			
		}
		finally
		{
			request.getSession().invalidate();
		}
		response.sendRedirect("./login.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
