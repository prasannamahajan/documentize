package com.lawyer.storageservice;
import java.io.IOException;
import com.lawyer.storageservice.PdfStorage;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import pdfgen.*;

import com.lawyer.storageservice.StorageService;
public class XmlCreator
{
	
	public void createxml(String realpath)throws ParserConfigurationException, TransformerException, IOException, SAXException
	{
		Xmlgeneric xg=new Xmlgeneric();
		//xg.listoper();
		
		
		DocumentBuilderFactory docfactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder docbuilder=docfactory.newDocumentBuilder();
	    Document  doc=docbuilder.newDocument();

	    Element quest=null;
	    Element e=null;
	    Element rootelement=doc.createElement("Tempalte");
	  //  System.out.println("size:l1"+xg.l1.size());
	   // System.out.println("size:l2"+xg.l2.size());
	    doc.appendChild(rootelement);
	    quest=doc.createElement("Questions");
	    rootelement.appendChild(quest);
	    for(int i=0;i<xg.l1.size();i++)
	    {
	    	
	    	String s="Question";
	    	String t="Answer";
	    	e=doc.createElement(s);
	    	//Attr attr=doc.createAttribute("id");
	    	String f=Integer.toString(i);
	    	e.appendChild(doc.createTextNode(xg.l1.get(i)));
	    	e.setAttribute("id",f );
	    	quest.appendChild(e);
	    	e=doc.createElement(t);
	    	e.appendChild(doc.createTextNode(xg.l2.get(i)));
	    	e.setAttribute("id",f);
	    	quest.appendChild(e);
	    //	lf.addAll((Collection<? extends String>) e);
	    	
	    }
	  
	   TransformerFactory transformfactory=TransformerFactory.newInstance();
		Transformer transform=transformfactory.newTransformer();
		DOMSource source=new DOMSource(doc);
	/*	Random rand=new Random();
		int randomgen =rand.nextInt(500);
		String number=Integer.toString(randomgen);*/
		
		StreamResult result=new StreamResult(realpath+"//"+"file.xml");
		transform.transform(source, result);
		String xml_filepath=realpath+"//"+"file.xml";
	    Xmltohashmap xhm=new Xmltohashmap();
	    xhm.storexml(xml_filepath);
	    Pdfreaderparser prp=new Pdfreaderparser();
	    String pdf_storage_path=realpath+"//";
	   
	//	prp.pdfReader(pdf_storage_path);
		pdf_generation_try5 pdftry5=new pdf_generation_try5();
		pdftry5.AllFunctions(pdf_storage_path,xg.sample_pdf_path);
	
				
		
	}
	
	
}
