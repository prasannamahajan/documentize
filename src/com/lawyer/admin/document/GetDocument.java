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

@WebServlet("/admin/getdocument")
public class GetDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(GetDocument.class);
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, JSONException {
		JSONObject reply = new JSONObject();
		JSONObject doc = new JSONObject();
		int documentId=0;
		try{
			documentId=Integer.parseInt(request.getParameter("documentId").toString());
		}
		catch(NullPointerException n)
		{
			documentId=0;
		}
		SampleDocument sampledocument = new SampleDocument();
		Document document = sampledocument.getDocumentInformation(documentId);
		doc.put("documentId",document.getDocument_id());
		doc.put("documentName", document.getDocument_name());
		doc.put("documentDescription",document.getDocumentDescription());
		doc.put("documentCategory", document.getCatagory());
		
		
		try {
			reply.put("success", true);
			reply.put("data", doc);
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
