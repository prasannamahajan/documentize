package com.lawyer.admin.document;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lawyer.admin.document.SampleDocument;
import com.lawyer.document.DocumentPath;
import com.lawyer.entity.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;


@WebServlet("/admin/updatedocumentfiles")
public class UpdateDocumentFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(UpdateDocumentFiles.class);
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, JSONException {
		JSONObject reply = new JSONObject();
		int documentId=0;
		FilePart htmlfile=null;
		FilePart jsonfile=null;
		MultipartParser parser=new MultipartParser(request,5*1024*1024 );
				Part part=null;
			    while((part=parser.readNextPart())!=null)
				{
			    	
					if(part.isFile())
					{
						
						FilePart fpart=(FilePart)part;
						String filename=fpart.getFileName();
						 if(filename!=null)
						 {
							 DocumentPath documentPath = new DocumentPath(getServletContext().getRealPath("/"));	 
						 if(filename.contains(".htm"))
						 {
							 htmlfile = fpart;
							 String storagepath=getServletContext().getRealPath("/")+"//"+"sampledocument"+"//";
							 boolean files=new File(storagepath+documentId).mkdir();
							
							// String path = getServletContext().getRealPath("/")+"//"+"sampledocument"+"//"+documentId+"//"+"sample.htm";
							 String path = documentPath.getSampleDocumentPath(documentId);
							 fpart.writeTo(new File(path));
						 }
						 if(filename.contains(".json"))
							 {
							 jsonfile = fpart;
							 //String path = getServletContext().getRealPath("/")+"//"+"docjson"+"//"+documentId+".json";
							 String path = documentPath.getFormJsonFilePath(documentId);
							 fpart.writeTo(new File(path));
							 }
						 }
						
							
					}
					else if(part.isParam())
					{
						
						 ParamPart paramPart = (ParamPart) part;
						 String paramName = part.getName();
						if(paramName.equals("documentId"))
							documentId = Integer.parseInt(paramPart.getStringValue());
						logger.info("id :{}",documentId);
					}
				}
			 
		
			   reply.put("success", true);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.print(reply.toString());

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
