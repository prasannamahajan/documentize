package com.lawyer.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.lawyer.document.DocumentPath;
public class DocumentGenerator {
	private  String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return (fileData.toString().replace("\n", " "));
        //return fileData.toString();
    }
	private  String insert(String input,TreeMap<String,String> treemap)
	{
		Iterator<String> it = treemap.keySet().iterator();
		while(it.hasNext())
		{
			String key=it.next();
			String value = treemap.get(key);
			if(value.length()<1)
				value="________________";
			input = input.replace(key, value);
		}
		return input;
	}
	
	public  void writeAnswers(int userId,int documentId,long documentDate,JSONObject data,HttpServletRequest request) throws JSONException
	{
		JSONObject answers = new JSONObject();
		answers.put("success", true);
		answers.put("data", data);
		
		DocumentPath path = new DocumentPath(request.getServletContext().getRealPath("/"));
		String userfolder = path.getDocumentFolderPath(userId, documentId, documentDate);
		String filename = userfolder + "//answer.json";
		
		File file;
		FileOutputStream fileOutputStream = null;
		try{
			file = new File(filename);
			fileOutputStream = new FileOutputStream(file);
			
			if(!file.exists())
				file.createNewFile();
			
			fileOutputStream.write(answers.toString().getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Generate document : It generates document , store it in folder and make database entry
	 *
	 * @param userId the user id
	 * @param documentId the document id
	 * @param documentDate the date (epoch time)
	 * @param treemap the treemap<String,String> which contains placeholder and answers
	 * @param newDocument  newDocument true if document is new , false if generating document for update purpose
	 * @param request the request , needed to find getrealpath
	 * @return true, if successful
	 */
	public boolean generateDocument(int userId,int documentId,long documentDate,TreeMap<String,String> treemap,boolean newDocument,HttpServletRequest request)
	{
			DocumentPath path = new DocumentPath(request.getServletContext().getRealPath("/"));
			
			// Create folder for document storage
			if(newDocument)
			if(!path.createUserDocumentFolder(userId, documentId, documentDate))
				return false;
			
			String inputFilePath=path.getSampleDocumentPath(documentId);
			String outputFilePath=path.getDocumentPath(userId, documentId, documentDate);
		try {
		    String content = insert(readFileAsString(inputFilePath),treemap);
		    OutputStream file = new FileOutputStream(new File(outputFilePath));
		    Document document = new Document();
		    PdfWriter.getInstance(document, file);
		    document.open();
		    @SuppressWarnings("deprecation")
			HTMLWorker htmlWorker = new HTMLWorker(document);
		    htmlWorker.parse(new StringReader(content));
		    document.close();
		    file.close();
		    
		    if(newDocument)
		    {
		    	DocumentCRUD documentDAO = new DocumentCRUD();
		    if(documentDAO.insertDocument(userId, documentId, documentDate))
		    		return true;
		    }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		    return false;
		}
		return true;
	}
}
