package com.lawyer.pdfencryption;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfEncryption {
    String src="C:\\Proj\\Sample.pdf";
    String dest="C:\\Proj\\encrypt.pdf";
    String dest1="C:\\Proj\\decrypt.pdf";
    public static byte[] USER = "Hello".getBytes();
    public static byte[] OWNER = "World".getBytes();
	public void pdfEncrypt() throws DocumentException, IOException
	{
		  PdfReader reader = new PdfReader(src);
	        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
	        stamper.setEncryption(USER, OWNER,
	        PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
	        stamper.close();
	        reader.close();
	}
	 public void decryptPdf() throws IOException, DocumentException {
	        PdfReader reader = new PdfReader(dest, OWNER);
	        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest1));
	        stamper.close();
	        reader.close();
	    }
	 
}
