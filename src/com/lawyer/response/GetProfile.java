package com.lawyer.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lawyer.user.service.*;
import com.lawyer.entity.User;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "getuserprofile", urlPatterns = { "/user/getuserprofile" })
public class GetProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException {
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		JSONObject data = new JSONObject();
		UserAccount account = new UserAccount();
		String email = null;
		if(request.getSession().getAttribute("email") != null)
			 email = request.getSession().getAttribute("email").toString();
		if(email != null)
		{
			User user = account.findUserByEmail(email);
			reply.put("success",true);
			data.put("first_name", user.getFirst_name());
			data.put("last_name", user.getLast_name());
			data.put("street_address",user.getStreet_address());
			data.put("city", user.getCity());
			data.put("state",user.getState());
			data.put("postal_code",user.getPostal_code());
			data.put("phone_number",user.getPhone_number());
			reply.put("data", data);
		}
		else
		{
			reply.put("success",false);
			reply.put("errorMessage", "Email id does not exist");
		}
		response.getWriter().write(reply.toString());
    	response.getWriter().flush();
		
	}
    public GetProfile() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
