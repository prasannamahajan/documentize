package com.lawyer.admin.document;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.lawyer.entity.Document;
import com.lawyer.filter.EntityManagerListener;


@WebServlet(name = "savedocumenttemplate", urlPatterns = { "/admin/savedocumenttemplate"})
public class SaveDocumentTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(SaveDocumentTemplate.class);
	private String catagory;
	private String documentName;
	private String documentDescription;

	
	private boolean result;
	
	private EntityManager em = EntityManagerListener.getEntityManager();
	private EntityTransaction etx = em.getTransaction();
    public SaveDocumentTemplate() {
        super();
 
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	logger.info("request to add document ");
    	try
    	{
    		catagory = request.getParameter("catagory");
    		documentName = request.getParameter("documentName");
    		documentDescription = request.getParameter("documentDescription");  		
    		Document document = new Document(catagory, documentName, documentDescription);
    		logger.info("cat : {} , docname: {},docdesc:{}",catagory,documentName,documentDescription);
    		etx.begin();
    		em.persist(document);
    		etx.commit();
    		result = true;
    		logger.info("New Document Added ");
    	}
    	catch(NullPointerException e)
    	{
    		logger.info("NullPointerException occured ");
    		logger.info("cat : {} , dNmae : {}",catagory,documentName);
    		logger.info("EntityManager : {}",em);
    		logger.info("EntityTransaction : {}",etx);
    		e.printStackTrace();
    		result = false;
    	}
    	catch(Exception e)
    	{
    		logger.info("Other exception");
    		logger.info("EntityManager : {}",em);
    		logger.info("EntityTransaction : {}",etx);
    		e.printStackTrace();
    		result = false;
    	}
    
    	JSONObject reply = new JSONObject();
    	try{
    		if(result==true)
    		{
    			reply.put("success", true);
    			reply.put("message","Document Successfully added");
    		}
    		else
    		{
    			reply.put("success", false);
    			reply.put("message","Document can not be added");
    		}
    	}
    	catch(JSONException j)
    	{
    		logger.info("Document can not be added due to json exception");
    		response.setContentType("application/json");
    		response.getWriter().write("\"success\":false,\"message\":\"Document can not be added\"");
    		response.getWriter().flush();
    		return;
    	}
    	finally{
    		response.setContentType("application/json");
    		response.getWriter().write(reply.toString());
    		response.getWriter().flush();
    	}
	}

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
