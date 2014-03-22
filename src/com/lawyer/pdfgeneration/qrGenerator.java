package com.lawyer.pdfgeneration;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

public class qrGenerator {

	public void qrGenerator(Document document,String imageRealPath) throws MalformedURLException, IOException, DocumentException
	 {
			  
			  Image qr_code = Image.getInstance("C:\\Proj\\qr_code.png");  
			  qr_code.enableBorderSide(3);
			  System.out.println(qr_code.getPlainWidth());
			  System.out.println(qr_code.getPlainHeight());
			  
			  System.out.println(qr_code.getScaledWidth());
			  System.out.println(qr_code.getScaledHeight());
			  
			  document.add(qr_code);
			  
			  }	
	

}
