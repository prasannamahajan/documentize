package com.lawyer.storageservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.convert.Converter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@WebServlet("/user/StorageService")
public class StorageService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public StorageService() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result=true;
		Xmlgeneric xg=new Xmlgeneric();
		Enumeration parameters=request.getParameterNames();
		
		//System.out.println("*** "+request.getParameter("documentId"));
		//request.getSession().setAttribute("userId",1);
		//request.getSession().setAttribute("documentId", 3);
		String userId = request.getSession().getAttribute("userId").toString();
		String documentId = request.getParameter("documentId").toString();

		String param=null;
		int question_id;
	//	LinkedHashMap<String,String> storemap=new LinkedHashMap<String,String>();
		xg.init();
		while(parameters.hasMoreElements())
		{
			param=(String)parameters.nextElement();
			try{
			question_id=Integer.parseInt(param);
			xg.tmap.put(question_id,request.getParameter(param));
		//	System.out.println("Params : ["+question_id+"]:["+request.getParameter(param)+" ]");
			}
			catch(Exception e)
			{
				
			}
			
	
		}
		Iterator iter = xg.tmap.keySet().iterator();
		while(iter.hasNext()) {
		    Integer key = (Integer)iter.next();
		    String question=Integer.toString(key);
		    String val = (String)xg.tmap.get(key);
		    xg.l1.add(question);
		    xg.l2.add(val);
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
    
    //	System.out.println("treemap "+xg.tmap);
    	//System.out.println("List 2:"+xg.l2);
   	ServletContext context=request.getServletContext();
    	xg.path=context.getRealPath("/");
    	xg.sample_pdf_path=xg.path;
    	xg.path=xg.path+"//"+"userdocument";
    	xg.sample_pdf_path=xg.sample_pdf_path+"//"+"sampledocument"+"//"+documentId+"//"+"sample.pdf";
    try {
			documentgen(userId,documentId);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void documentgen(String userId,String documentId) throws ParserConfigurationException, TransformerException, IOException, SAXException {
		PdfStorage pdfs=new PdfStorage();
		pdfs.createfolder(userId,documentId);
	}

	protected JSONObject writeResponse(boolean result)throws JSONException {
		JSONObject res=new JSONObject();
		if(result==true)
		{
		res.put("success", true);
		Xmlgeneric xg = new Xmlgeneric();
		res.put("epoch", xg.documentEpoch);
		res.put("userId",xg.userId);
		}
		else
		{
			res.put("failure", false);
		}
		
		return res;
	}
	
}
