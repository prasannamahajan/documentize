package com.lawyer.admin.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.Lawyer;
import com.lawyer.filter.EntityManagerListener;
import com.lawyer.user.service.UserAccount;
import org.json.*;


@WebServlet("/user/savenredirect")
public class SaveIDnRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(UserAccount.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String documentId = request.getParameter("documentId");
		String documentDate = request.getParameter("documentDate");
		
		request.getSession().setAttribute("documentId", documentId);
		request.getSession().setAttribute("documentDate", documentDate);
		
		response.sendRedirect("./lawyerlist.jsp");
	}



}

