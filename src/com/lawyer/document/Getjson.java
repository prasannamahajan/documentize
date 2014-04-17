package com.lawyer.document;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/user/getjson")
public class Getjson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(GetAnswers.class);

	

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String link="";
		try{
			DocumentPath path = new DocumentPath(request.getServletContext().getRealPath("/"));
			int documentId = Integer.parseInt(request.getParameter("documentId").toString());
			link= path.getFormJsonFilePath(documentId);
		}
		catch(NullPointerException e)
		{
			logger.info("Link in getjson : {}",link);
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		OutputStream out = response.getOutputStream();
		FileInputStream reader = null;
		File file = new File(link);
		
		byte[] bfile = new byte[(int)file.length()];
//		byte[] bfile = document.getPdf_file();
		try {
			reader = new FileInputStream(file);
			reader.read(bfile);
		response.setContentLength((int)file.length());
		System.out.println(bfile.toString());
		out.write(bfile);
		out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
