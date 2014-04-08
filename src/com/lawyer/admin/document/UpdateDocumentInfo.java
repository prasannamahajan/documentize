package com.lawyer.admin.document;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lawyer.admin.document.SampleDocument;
import com.lawyer.entity.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;


@WebServlet("/admin/updatedocumentinfo")
public class UpdateDocumentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(UpdateDocumentInfo.class);
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, JSONException {
		JSONObject reply = new JSONObject();
		
		int documentId = Integer.parseInt(request.getParameter("documentId"));
		String documentName = request.getParameter("documentName");
		String documentDescription = request.getParameter("documentDescription");
		String documentCategory = request.getParameter("documentCategory");
		
		Document document = new Document(documentId,documentCategory, documentName, documentDescription);
		SampleDocument sampleDocument = new SampleDocument();
		logger.info("docid {}",documentId);
		if(sampleDocument.updateDocumentInformation(document))
			reply.put("success", true);
		else
			reply.put("success", false);
		logger.info("json {}",reply.toString());
		
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
