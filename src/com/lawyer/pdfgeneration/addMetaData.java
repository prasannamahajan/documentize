package com.lawyer.pdfgeneration;

import com.itextpdf.text.Document;

public class addMetaData {
 
	public void addMetaData(Document document) {
		// TODO Auto-generated method stub
		 document.addTitle("YourLawyer");
		    document.addSubject("NAME CHANGE AFFIDAVIT");
		    document.addKeywords("Affidavit, Name ,User-type");
		    document.addAuthor("Extract name from the user");
		    document.addCreator("YourLawyer");
		  
	}

}
