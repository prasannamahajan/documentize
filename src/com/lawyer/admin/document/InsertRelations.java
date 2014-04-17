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

@WebServlet(name = "insertrelations", urlPatterns = { "/admin/insertrelations" })
public class InsertRelations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException {
		response.setContentType("application/json");
		JSONObject reply = new JSONObject();
		DocumentRelations docRel = new DocumentRelations();
		
		boolean result=docRel.insertRelations(request.getParameter("relations"));
		
		
		reply.put("success",result);
		response.getWriter().write(reply.toString());
    	response.getWriter().flush();
		
	}
    public InsertRelations() {
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
