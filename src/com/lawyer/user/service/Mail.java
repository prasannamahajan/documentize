package com.lawyer.user.service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
/**
 * @author Prasanna This Class is implemented to provide functionality for
 *         sending email
 */
public class Mail {

	private static Logger logger = LoggerFactory.getLogger(Mail.class);
//	@Resource(lookup="java:/gmail_system")
//	private Session mySession;

	/**
	 * Sendemail.
	 * 
	 * @param to
	 *            email address of user
	 * @param from
	 *            email address of our system
	 * @param subject
	 *            the subject of email
	 * @param body
	 *            the content of mail in html
	 * @throws Exception
	 *             the exception
	 */
	public void sendemail(String to, String from, String subject, String body)
			{
		try{
		
	
		Context initial = new InitialContext();
		Session session = (Session)initial.lookup("java:/gmail_system");
		
		logger.info("Mail Sesssion : {}",session);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		Address toAddress = new InternetAddress(to);
		message.addRecipient(Message.RecipientType.TO, toAddress);
		message.setSubject(subject);
		message.setContent(body, "text/html; charset=utf-8");
		//message.setContent(body, "text/html");
		Transport.send(message);
		}
		catch(Exception e)
		{
			logger.error("Mail Sending Failed");
			e.printStackTrace();
		}
	}

}
