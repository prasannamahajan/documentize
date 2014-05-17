package com.lawyer.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.Lawyer;
import com.lawyer.filter.EntityManagerListener;
import com.lawyer.user.service.UserAccount;
import org.json.*;


@WebServlet("/admin/insertlawyer")
public class Insertlawyer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(UserAccount.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public boolean insertLawyer(Lawyer lawyer) {
		EntityManager em = EntityManagerListener.getEntityManager();
		try {
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			em.persist(lawyer);
			etx.commit();
			
		} catch (Exception e) {
			
			return false;
		}

		return true;
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("userId");
		
		String fullname = request.getParameter("fullname");
		String aboutself = request.getParameter("aboutself");
		String address = request.getParameter("address");
		String phonenumber = request.getParameter("phonenumber");
		String email = request.getParameter("email");
		String specialization = request.getParameter("specialization");

		Lawyer lawyer = new Lawyer(fullname, aboutself, address, phonenumber, email, specialization);
		
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		try {
			reply = writeResponse(insertLawyer(lawyer));
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
			res.put("errorMessage", "Insertion failed");
		}
		return res;
	}

}

