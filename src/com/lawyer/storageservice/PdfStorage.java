package com.lawyer.storageservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.lawyer.storageservice.Xmlgeneric;
public class PdfStorage {
	/*
	static String id="1";
	static String document_id="2";
	*/
	String epoch_time=null;
	Xmlgeneric xg=new Xmlgeneric();
	XmlCreator xmlc=new XmlCreator();
	protected void createfolder(String id,String document_id) throws ParserConfigurationException, TransformerException, IOException, SAXException
	{
		//xg.path=xg.path+"//"+"userdocument";
		String realpath=xg.path+"\\"+id;
		pdfstorage(realpath);//for creation of user id folder
		realpath=xg.path+"\\"+id+"\\"+document_id;
		pdfstorage(realpath);//for creation of document id folder
		epoch_time=epochtime_calculator();//Find Epoch time
		System.out.println("Epoch Date:"+epoch_time);
	    boolean files = new File(realpath+"\\"+epoch_time+"\\"+"image_folder").mkdirs();
		realpath=realpath+"\\"+epoch_time;
		xg.documentEpoch=epoch_time;
		xg.userId=id;
		xmlc.createxml(realpath);
//		createoutputpdf(realpath);
	}
	private void  createoutputpdf(String realpath) throws FileNotFoundException
	{
	String path=realpath+"output.pdf";
	FileOutputStream fos=new FileOutputStream(path);
	}
	
private String epochtime_calculator()
{
	long epoch = System.currentTimeMillis()/1000;
	String epoch_time=String.valueOf(epoch);
    return epoch_time;
}
public void pdfstorage(String realpath)

		{
			System.out.println("Real Path is:"+realpath);
		File mkfolder=new File(realpath);
		if(!mkfolder.exists())
		{
			if (mkfolder.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Directoy Already Exists");
			}
		}
		

	     }

}
