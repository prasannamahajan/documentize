package com.lawyer.admin.document;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.DocumentException;
import com.lawyer.entity.Document;
import com.lawyer.filter.EntityManagerListener;
//import com.lawyer.pdfencryption.PdfEncryption;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

/**
 * Servlet implementation class SaveData
 * @param <FileItem>
 */
@WebServlet(name = "savedata", urlPatterns = { "/admin/savedata"})
public class SaveData<FileItem> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(SaveDocumentTemplate.class);
	ArrayList<String> documentInfo;
	private String catagory;
	private String documentName;
	private String documentDescription;
    private String documentPath;
    private	static String jsonId="100";

	private EntityManager em = EntityManagerListener.getEntityManager();
	private EntityTransaction etx = em.getTransaction();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean result=true;
        documentInfo=new ArrayList<String>();
        MultipartParser parser=new MultipartParser(request,5*1024*1024 );
  //      MultipartRequest multirequest=new MultipartRequest(request, filepath);
		Part part=null;
		String name=null;
		String filepath=null;
	    while((part=parser.readNextPart())!=null)
		{
			if(part.isFile())
			{
				System.out.println("File paramters found");
				FilePart fpart=(FilePart)part;
				 name=fpart.getFileName();
			    String[] parts=name.split(".");
	//		    String extension=parts[1];
			    filepath=databaseStorage(name);
				if(name!=null)
				{
					 long filesize=fpart.writeTo(new java.io.File(filepath));	
					
				}
				
					
			}
			else if(part.isParam())
			{
				 ParamPart paramPart = (ParamPart) part;
				 String namePar = part.getName();
				 documentInfo.add(paramPart.getStringValue());
				 //System.out.println("Parameters are:"+fieldparameters);
                
                
			}
		}
			 
    		
		
		response.setContentType("application/json");
		JSONObject reply1=new JSONObject();
		try {
			reply1=writeResponse(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		response.getWriter().write(reply1.toString());
    	response.getWriter().flush();
    	/*
    	PdfEncryption pdfe=new PdfEncryption();
    	try {
			pdfe.pdfEncrypt();
			pdfe.decryptPdf();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
		
	}
	protected JSONObject writeResponse(boolean result)throws JSONException {
		JSONObject res=new JSONObject();
		if(result==true)
		{
		res.put("success", true);
		}
		else
		{
			res.put("failure", false);
		}
		
		return res;
	}
	private String databaseStorage(String type)
	{
		
		if(type.contains(".htm"))
		{
		
		 catagory=documentInfo.get(1);
		 documentName=documentInfo.get(0);
		 documentDescription=documentInfo.get(2);
		 Document doc = new Document(catagory, documentName, documentDescription);
		//	logger.info("cat : {} , docname: {},docdesc:{}",catagory,documentName,documentDescription);
		 etx.begin();
		 em.persist(doc);
		 etx.commit();
		  String documentId=doc.getDocument_id().toString();
		 String storagepath=getServletContext().getRealPath("/")+"//"+"sampledocument"+"//";
		 boolean files=new File(storagepath+documentId).mkdir();
		 System.out.println("Document id is:"+documentId);
         documentPath=storagepath+documentId+"//"+"sample.htm";
		 System.out.println("documentPath:"+documentPath);
		jsonId=documentId;
	    
		 System.out.println("Json id:1"+jsonId); 
		}
		 if(type.contains(".json"))
		{
			
			 System.out.println("Json id:2"+jsonId);  
  
			documentPath=getServletContext().getRealPath("/")+"//"+"docjson"+"//"+jsonId+".json";
			
			
		}
		
		return documentPath;	
	}
	
	
}
