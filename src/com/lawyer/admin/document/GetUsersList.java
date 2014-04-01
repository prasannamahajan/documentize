package com.lawyer.admin.document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lawyer.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;
import com.lawyer.filter.EntityManagerListener;

@WebServlet("/admin/getuserslist")
public class GetUsersList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(GetUsersList.class);
	
	private int limit;
	private int start;
	private long totalCount=1;
	private JSONObject reply = new JSONObject();

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			 EntityManager em = EntityManagerListener.getEntityManager();
			EntityTransaction etx = em.getTransaction();
			try{
			limit = Integer.parseInt(request.getParameter("limit"));
			start = Integer.parseInt(request.getParameter("start"));
			}
			catch(NullPointerException n)
			{
				limit = 10;
				start = 1;
			}
			//Query totalCountQuery = em.createNativeQuery("Select count(*) from User");
			
			etx.begin();
			totalCount = em.createNamedQuery("User.findUsersCount", Long.class).getSingleResult().longValue();
			etx.commit();
			
			TypedQuery<User> query = em.createNamedQuery("User.findAllUser", User.class);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			
			
			etx.begin();
			List<User> list = query.getResultList();
			etx.commit();

			Iterator<User> it = list.iterator();

			JSONArray data = new JSONArray();
			for (; it.hasNext();) {
				
				User user = it.next();
				JSONObject docData = new JSONObject();
				
				
			
				docData.put("user_id",user.getUser_id());
				docData.put("first_name",user.getFirst_name());
				docData.put("last_name", user.getLast_name());
				docData.put("email",user.getEmail());
				docData.put("phone_number",user.getPhone_number());
				docData.put("role",user.getRole());
				docData.put("street_address",user.getStreet_address());
				docData.put("city",user.getCity());
				docData.put("state",user.getState());
				docData.put("postal_code",user.getPostal_code());
				docData.put("active",Boolean.toString(user.getActive()));
				
				
				data.put(docData);
			}

			reply.put("success", true);
			reply.put("totalCount", totalCount);
			reply.put("data", data);

		}
		catch (Exception e) {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print("\"success\":\"false\"");
			
			logger.info("Something Went wrong ");
		//	logger.info("EM {}", em);
			// logger.info("etx {}", etx);
			e.printStackTrace();
			return;
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.print(reply.toString());

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
