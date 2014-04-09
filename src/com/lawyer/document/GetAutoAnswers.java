package com.lawyer.document;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lawyer.document.DocumentAnswer;

@WebServlet("/user/getautoanswers")
public class GetAutoAnswers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(GetAutoAnswers.class);

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			int userId = Integer.parseInt(request.getSession()
					.getAttribute("userId").toString());
			int documentId = Integer.parseInt(request
					.getParameter("documentId").toString());

			DocumentAnswer documentAnswer = new DocumentAnswer();
			JSONObject reply = documentAnswer
					.getAutoAnswers(userId, documentId);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(reply.toString());
			out.flush();

		} catch (NullPointerException e) {

		}

		response.setContentType("application/json");

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
