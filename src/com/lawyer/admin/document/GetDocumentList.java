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

import com.lawyer.admin.document.SampleDocument;
import com.lawyer.entity.Document;
import com.lawyer.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;

import com.lawyer.filter.EntityManagerListener;

@WebServlet("/admin/getdocumentlist")
public class GetDocumentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(GetDocumentList.class);
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, JSONException {
		JSONObject reply = new JSONObject();
		JSONArray data = new JSONArray();
		
		SampleDocument sampledocument = new SampleDocument();
		List<Document> documentList = sampledocument.getDocumentList(0, 10);
		
		Iterator<Document> it = documentList.iterator();
		while(it.hasNext())
		{
			Document doc = it.next();
			JSONObject document = new JSONObject();
			document.put("documentId", doc.getDocument_id());
			document.put("documentName",doc.getDocument_name());
			document.put("documentCategory",doc.getCatagory());
			data.put(document);
			
		}
		
		try {
			reply.put("success", true);
			reply.put("data", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.print(reply.toString());

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
