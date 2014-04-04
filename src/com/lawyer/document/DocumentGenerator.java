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

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.lawyer.document.DocumentPath;
public class DocumentGenerator {
	private static String readFileAsString(String filePath) throws IOException {
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
	private static String insert(String input,TreeMap<String,String> treemap)
	{
		Iterator<String> it = treemap.keySet().iterator();
		while(it.hasNext())
		{
			String key=it.next();
			String value = treemap.get(key);
			input = input.replace(key, value);
		}
		return input;
	}
	private static boolean generateDocument(int userId,int documentId,int date,TreeMap<String,String> treemap)
	{
			DocumentPath path = new DocumentPath();
			path.createUserDocumentFolder(userId, documentId, date);
			
			String inputFilePath=path.getSampleDocumentPath(documentId);
			String outputFilePath=path.getDocumentPath(userId, documentId, date);
		try {
		    String k = insert(readFileAsString(inputFilePath),treemap);
		    OutputStream file = new FileOutputStream(new File(outputFilePath));
		    Document document = new Document();
		    PdfWriter.getInstance(document, file);
		    document.open();
		    @SuppressWarnings("deprecation")
			HTMLWorker htmlWorker = new HTMLWorker(document);
		    htmlWorker.parse(new StringReader(k));
		    document.close();
		    file.close();
		    return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		    return false;
		}
	}
}
