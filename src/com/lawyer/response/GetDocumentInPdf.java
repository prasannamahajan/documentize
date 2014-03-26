package com.lawyer.response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.lawyer.entity.UserDocument;
import com.lawyer.entity.UserDocumentPK;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.filter.EntityManagerListener;

@WebServlet("/user/get_document_in_pdf")
public class GetDocumentInPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(GetDocumentInPdf.class);
	private String userId="";
	private String documentId="";
	private String epochTime="";
	private String link;
	
	public GetDocumentInPdf() {
		super();

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try{
			 userId = request.getSession().getAttribute("userId").toString();
			documentId = request.getParameter("documentId").toString();
			epochTime = request.getParameter("time").toString();
			link=request.getServletContext().getRealPath("/")+"userdocument\\"+userId+"\\"+documentId+"\\"+epochTime+"\\output.pdf";
			System.out.println("link : "+link);
		}
		catch(NullPointerException e)
		{
			logger.info("Link in getpdf : {}",link);
			e.printStackTrace();
			logger.info("UserId : {} , documentId: {}, documentTime: {}",userId,documentId,epochTime);
		}
		
		response.setContentType("application/pdf");
		OutputStream out = response.getOutputStream();
		FileInputStream reader = null;
		File file = new File(link);
		byte[] bfile = new byte[(int)file.length()];
//		byte[] bfile = document.getPdf_file();
		try {
			reader = new FileInputStream(file);
			reader.read(bfile);
		response.setContentLength((int)file.length());
		out.write(bfile);
		out.flush();
		} catch (Exception e) {
		//	e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
