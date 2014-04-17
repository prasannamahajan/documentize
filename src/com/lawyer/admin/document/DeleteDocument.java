package com.lawyer.admin.document;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import com.lawyer.document.DocumentPath;
import com.lawyer.user.service.Mail;

@WebServlet(name = "deletedocument", urlPatterns = { "/admin/deletedocument" })
public class DeleteDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException {
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		SampleDocument sample = new SampleDocument();
	
		int documentId = Integer.parseInt(request.getParameter("documentId").toString());
		
		
		boolean result=sample.deleteDocument(documentId);
		
		
		reply.put("success",result);
		response.getWriter().write(reply.toString());
    	response.getWriter().flush();
		
	}
    public DeleteDocument() {
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
