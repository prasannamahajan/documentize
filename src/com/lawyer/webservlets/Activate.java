package com.lawyer.webservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lawyer.user.service.UserAccount;

@WebServlet("/activate")
public class Activate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Activate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String email = request.getParameter("email").toString();
		if(email != null)
		{
			UserAccount account = new UserAccount();
			boolean result = account.ActivateAccount(email);
			if(result == true)
			{
				String context = request.getContextPath();
				response.sendRedirect(context+"/login.html");
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>" +
				"<body>" +
				"Activation of account failed , contact admin"+
				"<a href='/login.html'>login</a>"+
				"</body>" +
				"<html>"
				);
		out.flush();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
