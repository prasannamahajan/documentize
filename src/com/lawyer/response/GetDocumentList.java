package com.lawyer.response;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;
import com.lawyer.filter.EntityManagerListener;

@WebServlet("/user/getdocumentlist")
public class GetDocumentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(GetDocumentList.class);
	private EntityManager em = EntityManagerListener.getEntityManager();
	private EntityTransaction etx = em.getTransaction();
	private int limit;
	private int start;
	private JSONObject reply = new JSONObject();

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int userId = (Integer) request.getSession().getAttribute("userId");
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
					.createNativeQuery("Select userdoc.document_id,doc.documentName,userdoc.date "
							+ "from UserDocument userdoc,Document doc"
							+ " where "
							+ "userdoc.user_id=:user_id "
							+ "and "
							+ "userdoc.document_id = doc.documentId");
			query.setParameter("user_id", userId);
			query.setFirstResult(start);
			query.setMaxResults(limit);
			
			List allDocuments;
			etx.begin();
			allDocuments = query.getResultList();
			etx.commit();

			Iterator it = allDocuments.iterator();

			JSONArray data = new JSONArray();
			for (; it.hasNext();) {
				Object[] docIdName = (Object[]) it.next();
				JSONObject docData = new JSONObject();
				int documentId = (Integer) docIdName[0];
				String documentName = (String) docIdName[1];
				String documentDate = docIdName[2].toString();
				docData.put("documentId", documentId);
				docData.put("documentName", documentName);
				docData.put("documentDate", documentDate);
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
