package storageservices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Xmlgeneric {
	static ArrayList<String> l1=new ArrayList<String>();
	static ArrayList<String> l2=new ArrayList<String>();
	static String str;
	public void listoper() throws IOException
	{
		
		BufferedReader in=null;
		in=new BufferedReader(new FileReader("C:\\Proj\\a.txt"));
		while((str=in.readLine())!=null)
		{
			l1.add(str);
		}
		
			
			
	}
}