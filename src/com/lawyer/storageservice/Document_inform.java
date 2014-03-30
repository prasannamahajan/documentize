package com.lawyer.storageservice;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Document_inform {
	
	private String epochtime_calculator()
	{
		long epoch = System.currentTimeMillis()/1000;
		String epoch_time=String.valueOf(epoch);
	    return epoch_time;
	}
	
	protected void document_inform(String user_id,String document_id) throws ParserConfigurationException, TransformerException, IOException, SAXException
	{
	String epoch_time=epochtime_calculator();
	get_id(user_id,document_id,epoch_time);
	return ;
	}

	private void get_id(String user_id, String document_id, String epoch_time) throws ParserConfigurationException, TransformerException, IOException, SAXException {
		PdfStorage pdfs=new PdfStorage();
		pdfs.createfolder(user_id,document_id,epoch_time);
		
	}
	

}
