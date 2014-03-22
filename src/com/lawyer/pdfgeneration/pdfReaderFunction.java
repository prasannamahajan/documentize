package com.lawyer.pdfgeneration;

import java.io.IOException;
import java.util.Iterator;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.lawyer.storageservice.Xmlgeneric;

public class pdfReaderFunction {



	public static String rep1=null;
	
	public static void pdfRead(PdfReader reader) throws IOException {
		// TODO Auto-generated method stub
	

		String s;
		String u;
	    String k;
		Xmltohashmap xh= new Xmltohashmap();
  		//System.out.println(xh.hmap);
  		int i=reader.getNumberOfPages();
  		String s1=null;
  		for(int j=1;j<=i;j++)
  		{
  			
  			s=PdfTextExtractor.getTextFromPage(reader,j );
  		    k=s.format(s, null);
  	//	    System.out.println(k);
  			s1=s1+s;
  			int st1 = s1.length();
  			String nullChar=s1.substring(0,4);
  		    s1=s1.substring(5,st1);
  			System.out.println("This is null character"+nullChar);
  			
  		}
  		Xmlgeneric xg = new Xmlgeneric();
  		Iterator<Integer> iter = xg.tmap.keySet().iterator();
  		for(int f=0;f<xg.tmap.size();f++)
  		{
  			
  			String key="";
  			String value="";
  				int keyOfMap = iter.next();
  				key="*"+keyOfMap+"*";
  				value=xg.tmap.get(keyOfMap); 

  		
  	
	if(f==0)
  		{
  		rep1=s1.replace(key,value);
  		}
  		else
  		{
  			rep1=rep1.replace(key, value);
  		}
  		}

	}


}
