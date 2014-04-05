package com.lawyer.document;

import java.io.IOException;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Prasanna
 * This servlet creates new document
 */
@WebServlet(name = "createdocument", urlPatterns = { "/user/createdocument" })
public class CreateDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {
		JSONObject reply = new JSONObject();
		JSONObject data = new JSONObject();
		Enumeration<String> paramlist = request.getParameterNames();
		TreeMap<String, String> treemap = new TreeMap<String, String>();
		try {
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			int documentId = Integer.parseInt(request
					.getParameter("documentId").toString());

			long date = System.currentTimeMillis() / 1000L;
			while (paramlist.hasMoreElements()) {
				String key = paramlist.nextElement();
				String value = request.getParameter(key);
				key = "*" + key + "*";
				treemap.put(key, value);
				data.put(key, value);
			}
			DocumentGenerator documentGenerator = new DocumentGenerator();
			boolean newDocument=true;
			if (documentGenerator.generateDocument(userId, documentId, date,
					treemap,newDocument,request))
				reply.put("success", true);
			else
				reply.put("success", false);

			response.getWriter().write(reply.toString());
			response.getWriter().flush();
		} catch (Exception e) {
			reply.put("success", false);
			response.getWriter().write(reply.toString());
			response.getWriter().flush();
			return;
		}

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
