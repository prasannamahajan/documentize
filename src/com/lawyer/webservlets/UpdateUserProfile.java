package com.lawyer.webservlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.User;
import com.lawyer.filter.EntityManagerListener;
import com.lawyer.user.service.UserAccount;

import org.slf4j.Logger;

@WebServlet("/user/updateuserprofile")
public class UpdateUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateUserProfile() {
		super();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {
		UserAccount account = new UserAccount();
		
		Logger logger = LoggerFactory.getLogger(UpdateUserProfile.class);
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String street_address = request.getParameter("street_address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int postal_code = Integer.parseInt(request.getParameter("postal_code").toString());
		long phone_number = Long.parseLong(request.getParameter("phone_number").toString());

		EntityManager em = EntityManagerListener.getEntityManager();
		String email = request.getSession().getAttribute("email").toString();
		
		boolean result = account.update(first_name, last_name, email, street_address, city, state, postal_code, phone_number);
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		reply = writeResponse(result);
		response.getWriter().write(reply.toString());
		response.getWriter().flush();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected JSONObject writeResponse(boolean result) throws JSONException {
		JSONObject res = new JSONObject();
		if (result == true) {
			res.put("success", true);
		} else {
			res.put("success", false);
			res.put("errorMessage", "Update profile failed due to some reasons");
		}
		return res;
	}
}
