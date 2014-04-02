package com.lawyer.document;

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

@WebServlet(name = "senddocumentbymail", urlPatterns = { "/user/senddocumentbymail" })
public class MailDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException {
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		DocumentPath doc = new DocumentPath(request.getServletContext().getRealPath("/"));
		Mail mail = new Mail();
		int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
		int documentId = Integer.parseInt(request.getParameter("documentId").toString());
		int documentDate = Integer.parseInt(request.getParameter("documentDate").toString());
		String to = (String)request.getSession().getAttribute("email");
		String from = "webapp2011993@gmail.com";
		String subject = "Document";
		String  body = "See Attachment for document";
		String filesource = doc.getDocumentPath(userId, documentId, documentDate);
		mail.sendemail(to, from, subject, body, filesource);
		
		reply.put("success", true);
		response.getWriter().write(reply.toString());
    	response.getWriter().flush();
		
	}
    public MailDocument() {
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
