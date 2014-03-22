package com.lawyer.admin.document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
//import javax.persistence.TypedQuery;
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
	private EntityManager em = EntityManagerListener.getEntityManager();
	private EntityTransaction etx = em.getTransaction();
	private int limit;
	private int start;
	private JSONObject reply = new JSONObject();

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			try{
			limit = Integer.parseInt(request.getParameter("limit"));
			start = Integer.parseInt(request.getParameter("start"));
			}
			catch(NullPointerException n)
			{
				limit = 10;
				start = 1;
			}
			
			Query query = em
					.createNativeQuery("select u.user_id," +
							"u.first_name," +
							"u.last_name," +
							"u.email," +
							"u.phone_number" +
							",u.role" +
							",u.street_address" +
							",u.city," +
							"u.state," +
							"u.postal_code," +
							"u.active " +
							"from User u");
			
			query.setFirstResult(start);
			query.setMaxResults(limit);
			
			List allUsers;
			etx.begin();
			allUsers = query.getResultList();
			etx.commit();

			Iterator it = allUsers.iterator();

			JSONArray data = new JSONArray();
			for (; it.hasNext();) {
				Object[] UserListObject = (Object[]) it.next();
				User user = new User();
				JSONObject docData = new JSONObject();
				
				user.setUser_id((Integer) UserListObject[0]);
				user.setFirst_name(UserListObject[1].toString());
				user.setLast_name(UserListObject[2].toString());
				user.setEmail(UserListObject[3].toString());
				user.setPhone_number(Long.parseLong(UserListObject[4].toString()));
				user.setRole(UserListObject[5].toString());
				user.setStreet_address(UserListObject[6].toString());
				user.setCity(UserListObject[7].toString());
				user.setState(UserListObject[8].toString());
				user.setPostal_code((Integer)UserListObject[9]);
				user.setActive(Boolean.parseBoolean(UserListObject[10].toString()));
			
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
			reply.put("data", data);

		}
		catch (Exception e) {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print("\"success\":\"false\"");
			
			logger.info("Something Went wrong ");
			logger.info("EM {}", em);
			logger.info("etx {}", etx);
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
