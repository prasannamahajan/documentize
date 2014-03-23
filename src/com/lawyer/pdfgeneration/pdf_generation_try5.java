package com.lawyer.pdfgeneration;


import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;

import com.itextpdf.awt.geom.misc.RenderingHints;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.lawyer.pdfgeneration.Xmltohashmap;
import com.lawyer.storageservice.*;

public class pdf_generation_try5 {
	private static String INPUTFILE = null;
	private static String OUTPUTFILE=null ;
	/**/
	  
	
	public static String rep1=null;
  
	/*We have initialized the font, We have to make this dynamic*/
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD);
  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 8,
      Font.NORMAL, BaseColor.RED);
  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
      Font.BOLD);
  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10,
      Font.NORMAL);
  float value=0;

  public static float FACTOR = 0.5f;
 
  //PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
  
  
  public  void AllFunctions(String realpath,String sample_pdf_path) 
  {
        
	  try {
	      INPUTFILE=sample_pdf_path;
	     // System.out.println("Inputfile: "+INPUTFILE);
		  Document document = new Document();
          //PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
		  OUTPUTFILE=filepath(realpath);
		  //System.out.println("Outputfile"+OUTPUTFILE);
          PdfReader reader = new PdfReader(INPUTFILE);
          PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(OUTPUTFILE));
   //     writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
      
      document.open();
      addTitlePage(document);
      
      int n = reader.getNumberOfPages();
      PdfImportedPage page;
      // Go through all pages
      for (int i = 1; i <= n; i++) {
      	
          page = writer.getImportedPage(reader, i);
          Image instance = Image.getInstance(page);
          instance.setAlignment(Element.ALIGN_LEFT);
     //  document.add(instance);
        
      }
     
      addMetaData addmetainfo=new addMetaData();
      addmetainfo.addMetaData(document);
      
      pdfReaderFunction pdfReader=new pdfReaderFunction();
      pdfReaderFunction.pdfRead(reader);
      
      AddParagraph addpara=new AddParagraph();
      addpara.addNewParagraph(document,smallBold);
    
      addContent addC=new addContent();
   //   addC.addContent(document);

      
      // scaleImage();
  	qrGenerator qrCode=new qrGenerator();
  	String imageRealPath = null;
//	qrCode.qrGenerator(document,imageRealPath);
      
	

  	document.close();
  	
   pdf_compression compression=new pdf_compression();
	
	try {
		compression.pdfCompression(realpath);
	    } 
	catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	
      
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
  
  public String filepath(String realpath)
	{
		String  path=realpath+"\\"+"output.pdf";
	  //String  path=realpath; 
	  return path;	
		
	}
  
  //iText allows to add metadata to the PDF which can be viewed in your Adobe
  // Reader
  // under File -> Properties
   

    
  private  void addTitlePage(Document document)
      throws DocumentException, MalformedURLException, IOException {
	/* 
	  String stamptype = null;
	  if(stamptype=="major-stamp-500")
	  { Image image1 = Image.getInstance("timthumb.php.jpg");
	   // image1.setAbsolutePosition(000f, 650f);
	    image1.enableBorderSide(3);
	    
	    int  x_cord = (int) image1.getAbsoluteX();
	    int  Y_cord = (int) image1.getAbsoluteY();
	    System.out.println(x_cord);
	    System.out.println(Y_cord);
//    image1.scaleAbsolute(0,650);

    document.add(image1);
	  }
	  
	  else {
		  
		  Image image1 = Image.getInstance("C:\\Proj\\stamp-paper-100.jpg");
		    image1.setAbsolutePosition(000f, 325f);
		    image1.enableBorderSide(3);
		    //document.setPageSize(PageSize.A3);
		    // document.setPageSize(new com.itextpdf.text.Rectangle(image1.getScaledWidth(), image1.getScaledHeight()));
		    image1.setScaleToFitHeight(true);
		    image1.setScaleToFitLineWhenOverflow(true);
		    System.out.println("bottom value"+image1.getBottom());
		     value=image1.getHeight();
		     
		    int  x_cord = (int) image1.getAbsoluteX();
		    int  Y_cord = (int) image1.getAbsoluteY();
		    System.out.println(x_cord);
		    System.out.println(Y_cord);
//	        image1.scaleAbsolute(0,650);

     	    document.add(image1);
     	  
		  
	  }
	  
	*/  
  /*  try
    {
            BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D)bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY));
            boolean b = g2d.drawImage(image1.getImage(), 0, 0, 50, 50, null);
            System.out.println(b);
            ImageIO.write(bi, "jpg", new File("/tmp/apple50.jpg"));
    }
    catch(Exception e)
    {
    	
    }
      int indentation=0;
	    float scaler = ((document.getPageSize().getWidth() - document.leftMargin()  - document.rightMargin() - indentation) / image1.getWidth()) * 100;
        image1.scalePercent(scaler);
	    */


        /* the below segment can be particularly helpfull in case we want to add the photos of the user 
         * the uploaded fotos will be directly placed at a specific position */

	  /*	    String imageUrl = "http://www.wikihow.com/Image/:"+"Get-the-URL-for-Pictures-Step-1.jpg";
	    Image image2 = Image.getInstance(new URL(imageUrl));
	    image2.setAbsolutePosition(200, 200);
	    document.add(image2);
*/
  }
  
  //PdfreaderFunction ends above

	  
	  public  void AddParagraph(Document document) throws DocumentException
	  {
	  }

  public void addContent(Document document) throws DocumentException {
    Anchor anchor = new Anchor("Chapter 1: used for various paragraphs", catFont);
    anchor.setName("First Chapter");

    // Second parameter is the number of the chapter
    Chapter catPart = new Chapter(new Paragraph(anchor), 1);

    Paragraph subPara = new Paragraph("Subcategory 1", subFont);
    Section subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("Hello"));

    subPara = new Paragraph("Subcategory 2", subFont);
    subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("Paragraph 1"));
    subCatPart.add(new Paragraph("Paragraph 2"));
    subCatPart.add(new Paragraph("Paragraph 3"));

    // add a list
    createList(subCatPart);
    Paragraph paragraph = new Paragraph();
    addEmptyLine(paragraph, 5);
    subCatPart.add(paragraph);

    // add a table
    createTable(subCatPart);

    // now add all this to the document
    document.add(catPart);

    // Next section
    anchor = new Anchor("Second Chapter : only for checking", catFont);
    anchor.setName("Second Chapter");

    // Second parameter is the number of the chapter
    catPart = new Chapter(new Paragraph(anchor), 1);

    subPara = new Paragraph("Subcategory", subFont);
    subCatPart = catPart.addSection(subPara);
    subCatPart.add(new Paragraph("This is a very important message"));

    // now add all this to the document
    document.add(catPart);

  }

  public  void createTable(Section subCatPart)
      throws BadElementException {
    PdfPTable table = new PdfPTable(3);

    // t.setBorderColor(BaseColor.GRAY);
    // t.setPadding(4);
    // t.setSpacing(4);
    // t.setBorderWidth(1);

    PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Table Header 2"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Table Header 3"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    table.setHeaderRows(1);

    table.addCell("1.0");
    table.addCell("1.1");
    table.addCell("1.2");
    table.addCell("2.1");
    table.addCell("2.2");
    table.addCell("2.3");

    subCatPart.add(table);

  }

  public  void createList(Section subCatPart) {
    List list = new List(true, false, 10);
    list.add(new ListItem("First point"));
    list.add(new ListItem("Second point"));
    list.add(new ListItem("Third point"));
    subCatPart.add(list);
  }
  
public  void addEmptyLine(Paragraph paragraph, int number) {
for (int i = 0; i < number; i++) {
 paragraph.add(new Paragraph(" "));
}
}
  
 
	  
  
    public  void scaleImage()
          {
                  try
                  {
                          ImageIcon ii = new ImageIcon("timthumb.php.jpg");
                          BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
                          Graphics2D g2d = (Graphics2D)bi.createGraphics();
                          g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                                  RenderingHints.VALUE_RENDER_QUALITY));
                          boolean b = g2d.drawImage(ii.getImage(), 0, 0, 50, 50, null);
                         // System.out.println(b);
                          ImageIO.write(bi, "jpg", new File("images_changed.jpg"));
                  }
                  catch (Exception e)
                  {
                          e.printStackTrace();
                  }
          
   
                 } 
}