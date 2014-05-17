package com.lawyer.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lawyer.entity.User;
import javax.persistence.*;

import com.lawyer.user.service.LawyerAccount;
import com.lawyer.user.service.Mail;
import com.lawyer.document.DocumentPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.*;
import com.lawyer.filter.EntityManagerListener;
import com.lawyer.user.service.UserAccount;
import org.json.*;


@WebServlet("/user/sendtolawyer")
public class SendToLawyer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(UserAccount.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
	int documentId = Integer.parseInt(request.getSession().getAttribute("documentId").toString());
	long documentDate = Long.parseLong(request.getSession().getAttribute("documentDate").toString());
	
	int lawyerId = Integer.parseInt(request.getParameter("lawyerid"));
	
	DocumentPath path = new DocumentPath();
	String filesource = path.getDocumentPath(userId, documentId, documentDate);
	
	UserAccount account = new UserAccount();
	User user = account.findUserbyId(userId);
	
	LawyerAccount lawyeraccount = new LawyerAccount();
	Lawyer lawyer = lawyeraccount.getLawyerById(lawyerId);
	
	Mail mail = new Mail();
	//sending file to lawyer
	String lcontent = "Request came from user :"+user.getFirst_name()+" "+user.getLast_name()+"\n"
			+"contact no : "+user.getPhone_number()+"\n"
			+"email : "+user.getEmail()+"\n"
			+"address : \n "+user.getStreet_address()
			+"\n"+user.getCity()
			+"\n"+user.getState()
			+"\n Document is attached";
	
	mail.sendemail(lawyer.getEmail(), user.getEmail(),"Request for validation", lcontent, filesource);
	
	response.sendRedirect("../response.jsp?id=7");

}
}

