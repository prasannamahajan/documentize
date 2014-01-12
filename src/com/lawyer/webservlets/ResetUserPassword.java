package com.lawyer.webservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.cookie.Cookies;
import com.lawyer.entity.User;
import com.lawyer.user.service.UserAccount;

/**
 * Servlet implementation class ResetUserPassword
 */
@WebServlet("/resetuserpassword")
public class ResetUserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static Logger logger = LoggerFactory.getLogger(ResetUserPassword.class);
    public ResetUserPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException , IOException, JSONException
    {
    	
    	UserAccount account = new UserAccount();
    	boolean result=true;
    	
    	try{
    	String email = request.getSession().getAttribute("email").toString();
    	String oldPassword = request.getParameter("oldPassword");
    	String newPassword = request.getParameter("newPassword");
    	if(account.authenticate(email, oldPassword))
    		result=account.resestPassword(email, newPassword);
    	}
    	catch(Exception e)
    	{
    		result = false;
    	}
    	response.setContentType("application/json");
    	JSONObject reply = new JSONObject();
    	reply = writeResponse(result);
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

	protected JSONObject writeResponse(boolean result) throws JSONException
	{
		JSONObject res = new JSONObject();
		if(result == true)
		{
			res.put("success", true);
		}
		else
		{
			res.put("success", false);
			//res.put("errorMessage", "Password or Email is incorrect");
		}
		return res;
	}

}
