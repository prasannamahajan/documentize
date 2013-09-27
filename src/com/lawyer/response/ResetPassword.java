package com.lawyer.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lawyer.entity.User;
import com.lawyer.user.service.UserAccount;
import org.json.*;

/**
 * Reset Password :
 * This is servlet which accept parameters 
 * old password : for confirmation
 * new password : new password to set
 * 
 * This servlet gives response in json format
 * "success":true         if password get updates successfully
 * "success":false 		  if password update fails
 */
@WebServlet("/user/resetuserpassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResetPassword() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		User user = (User) request.getSession().getAttribute("user");
		UserAccount account = new UserAccount();
		boolean result;
		if (user.getPassword().equals(oldPassword))
			result = account.resestPassword(user.getEmail(), newPassword);
		else
			result = false;

		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		try {
			reply = writeResponse(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().write(reply.toString());
		response.getWriter().flush();
	}

	protected JSONObject writeResponse(boolean result) throws JSONException {
		JSONObject res = new JSONObject();
		if (result == true) {
			res.put("success", true);
		} else {
			res.put("success", false);
			res.put("errorMessage", "Please Enter your old Password correct");
		}
		return res;
	}

}
