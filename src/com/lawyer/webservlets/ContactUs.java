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
import com.lawyer.user.service.Mail;

@WebServlet("/contact-us")
public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(AjaxLogin.class);

	public ContactUs() {
		super();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		String mobile = request.getParameter("mobile");
		Mail mail = new Mail();
		boolean result = true;
		try {
			mail.sendemail("realprasanna007@gmail.com", email,
					"Contact Us support", name+"<br>"+email + "<br>+mobile : " + mobile
							+ "<br>Says :<br>" + message);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		reply = writeResponse(result);
		response.getWriter().write(reply.toString());
		response.getWriter().flush();

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
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
			// res.put("errorMessage", "Password or Email is incorrect");
		}
		return res;
	}
}
