package pdfgen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.itextpdf.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class Pdfreaderparser{
	public static String s;
	public static String u;
	//	public static char s1[];

	
	 public void pdfReader(String realpath)throws IOException
{
		Xmltohashmap xh= new Xmltohashmap();
		System.out.println(xh.hmap);
		PdfReader pdr=new PdfReader("C:\\Proj\\Samplepdf.pdf");
		int i=pdr.getNumberOfPages();
		String s1=null;
		String rep1=null;
		for(int j=1;j<=i;j++)
		{
			
			s=PdfTextExtractor.getTextFromPage(pdr,j );
			s1=s1+s;
		}
				
		for(int f=0;f<xh.hmap.size();f++)
		{
		String key="*"+(String)xh.hmap.keySet().toArray()[f]+"*";
		System.out.println("Key:"+key);
		String value=(String)xh.hmap.values().toArray()[f];
		System.out.println("Value:"+value);
		if(f==0)
		{
		rep1=s1.replace(key,value);
		}
		else
		{
			rep1=rep1.replace(key, value);
		}
		}
		//System.out.println(rep1);
		

final Charset UTF_8 = Charset.forName("UTF-8");

String text = rep1;
byte[] bytes = text.getBytes(UTF_8);
//System.out.println(""+Arrays.toString(bytes));
//System.out.println(""+new String(bytes, UTF_8));

try {
	OutputStream file = new FileOutputStream(new File(realpath+"\\"+"output.pdf"));

	Document document = new Document();
	
	PdfWriter.getInstance(document, file);

	document.open();
	com.itextpdf.text.Image image =com.itextpdf.text.Image.getInstance("C:\\Proj\\images.jpg");
    document.setPageSize(new com.itextpdf.text.Rectangle(image.getScaledWidth(), image.getScaledHeight()));
    image.setScaleToFitHeight(true);
    image.setXYRatio(1);
    image.getAlignment();
   // image.setAbsolutePosition(0, 0);
    document.add(image);
	
	document.add(new Paragraph(rep1));
	//document.add(new Paragraph(new Date().toString()));

	document.close();
	file.close();

} catch (Exception e) {

	e.printStackTrace();
}
}
}


