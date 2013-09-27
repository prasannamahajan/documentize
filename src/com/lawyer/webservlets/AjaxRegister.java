package com.lawyer.webservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import com.lawyer.user.service.UserAccount;
import com.lawyer.user.service.Mail;

@WebServlet("/ajaxregister")
public class AjaxRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(AjaxRegister.class);

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {
		logger.info("In ajax register");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String street_address = request.getParameter("street_address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int postal_code = Integer.parseInt(request.getParameter("postal_code"));
		long phone_number = Long
				.parseLong(request.getParameter("phone_number"));

		UserAccount account = new UserAccount();
		boolean result = account.register(first_name, last_name, email,
				password, street_address, city, state, postal_code,
				phone_number);

		if (result) {
			Mail mail = new Mail();
			String message = "Please click on link below<br>"
					+ "To activate your account<br>" + "<a rel='nofollow' target='_blank'  href=\"http://localhost:8080/"
					+ request.getContextPath() +"/activate?email=" + email
					+ "\">Activate</a>";
			try {
				mail.sendemail(email, "webapp2011993@gmail.com", "Activate",
						message);
			} catch (Exception e) {
				logger.info("Error occured while sending email");
			}
		}
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		reply = writeResponse(result);
		response.getWriter().write(reply.toString());
		response.getWriter().flush();

	}

	protected JSONObject writeResponse(boolean result) throws JSONException {
		JSONObject res = new JSONObject();
		if (result == true) {
			res.put("success", true);
		} else {
			res.put("success", false);
			// res.put("errorMessage","Registration failed due to some reasons");

		}
		return res;
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

	@Override
	public String getServletInfo() {
		return "This servlet is used for registering the user";
	}

}
