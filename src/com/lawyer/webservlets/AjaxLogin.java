package com.lawyer.webservlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

import com.lawyer.entity.User;
import com.lawyer.user.service.UserAccount;
import com.lawyer.cookie.*;

@WebServlet("/ajaxlogin")
public class AjaxLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static Logger logger = LoggerFactory.getLogger(AjaxLogin.class);
    public AjaxLogin() {
        super();
        }
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException , IOException, JSONException
    {
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String keepMeLogin = request.getParameter("remember");
    	String role="";
    	boolean remember = "true".equals(keepMeLogin);
    	//boolean remember = false;
    	UserAccount account = new UserAccount();
    	boolean result = account.authenticate(email, password);
    	if(result == true)
    	{
    		User user = account.findUserByEmail(email);
    		request.getSession().setAttribute("email", email);
    		request.getSession().setAttribute("role", user.getRole().toString());
    		role = user.getRole().toString();
    		request.getSession().setAttribute("user", user);
    		request.getSession().setAttribute("userId", user.getUser_id());
    		logger.info("{} logged in",email);
    		if(remember)
    		{
    				Cookies.addCookie(response,"lawyer_remember",email,4*3600*24);
    		}
    		else
    		{
    			request.getSession().setMaxInactiveInterval(5*60);
    				Cookies.removeCookie(response, "lawyer_remember");
    		}
    	}
    	response.setContentType("application/json");
    	JSONObject reply = new JSONObject();
    	reply = writeResponse(result,role);
    	response.getWriter().write(reply.toString());
    	response.getWriter().flush();
    	
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected JSONObject writeResponse(boolean result,String role) throws JSONException
	{
		JSONObject res = new JSONObject();
		if(result == true)
		{
			res.put("success", true);
			res.put("role", role);
		}
		else
		{
			res.put("success", false);
			//res.put("errorMessage", "Password or Email is incorrect");
		}
		return res;
	}
}
