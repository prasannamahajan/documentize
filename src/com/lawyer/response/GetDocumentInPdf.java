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
	/*private static Logger logger = LoggerFactory.getLogger(GetDocumentInPdf.class);
	private int userId;
	private int documentId;
	private byte[] pdfFile;
	private EntityManager em = EntityManagerListener.getEntityManager();
	private EntityTransaction etx = em.getTransaction();
	*/
	public GetDocumentInPdf() {
		super();

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*------------------------------------------
		try{
			userId = (Integer) request.getAttribute("userId");
			documentId = Integer.parseInt(request.getParameter("documentId"));
		}
		catch(NullPointerException e)
		{
			logger.info("UserId : {} , documentId: {}",userId,documentId);
		}
		User_documentPK pk = new User_documentPK(userId,documentId);
	
		etx.begin();
		User_document document = em.find(User_document.class,pk);
		etx.commit();
		*/
		response.setContentType("application/pdf");
		OutputStream out = response.getOutputStream();
		FileInputStream reader = null;
		File file = new File("C:\\Proj\\output.pdf");
		byte[] bfile = new byte[(int)file.length()];
//		byte[] bfile = document.getPdf_file();
		try {
			reader = new FileInputStream(file);
			reader.read(bfile);
		response.setContentLength((int)file.length());
		out.write(bfile);
		out.flush();
		} catch (Exception e) {
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
