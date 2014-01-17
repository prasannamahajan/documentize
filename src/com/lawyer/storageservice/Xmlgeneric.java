package com.lawyer.storageservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Xmlgeneric {
	static ArrayList<String> l1;
	static ArrayList<String> l2;
	static String str;
	public static String path,sample_pdf_path;
	public String randomnumber=null;
	static TreeMap<Integer,String> tmap;
	
	
	protected void init()
	{
		l1=new ArrayList<String>();
		l2=new ArrayList<String>();
		tmap=new TreeMap<Integer, String>();
	}
	/*public void listoper() throws IOException
	{
		
		BufferedReader in=null;
		in=new BufferedReader(new FileReader("C:\\Proj\\a.txt"));
		while((str=in.readLine())!=null)
		{
			l1.add(str);
		}
		
			
			
	}*/
}