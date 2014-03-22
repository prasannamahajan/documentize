package com.lawyer.pdfgeneration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class pdf_compression {
    /** The resulting PDF file. 
     * @param realpath 
     * @param  
     * @throws IOException 
     * @throws DocumentException 
     * @throws FileNotFoundException */
    
   public void pdfCompression(String realpath) throws FileNotFoundException, DocumentException, IOException
   {
	  
	   
        PdfReader reader = new PdfReader(realpath+"\\"+"output.pdf");
    
	PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(realpath+"Output_compressed.pdf"), PdfWriter.VERSION_1_5);
    stamper.getWriter().setFullCompression();
    int total = reader.getNumberOfPages() + 1;
    for (int i = 1; i < total; i++) {
    reader.setPageContent(i, reader.getPageContent(i));
    }
    stamper.setFullCompression();
    stamper.close();
    
   }
    
    }
