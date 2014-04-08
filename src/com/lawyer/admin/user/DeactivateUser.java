package com.lawyer.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lawyer.entity.User;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.*;
import com.lawyer.filter.EntityManagerListener;
import com.lawyer.user.service.UserAccount;
import org.json.*;


@WebServlet("/admin/deactivateuser")
public class DeactivateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(UserAccount.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public boolean activate(String userId) {
		EntityManager em = EntityManagerListener.getEntityManager();
		User user;
		user = null;
		try {
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			user = em.find(User.class,Integer.parseInt(userId));
			user.setActive(false);
			em.persist(user);
			etx.commit();
			
		} catch (Exception e) {
			
			return false;
		}

		return true;
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("userId");
		
		
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		try {
			reply = writeResponse(activate(user_id));
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
			res.put("errorMessage", "Activation Failed");
		}
		return res;
	}

}

