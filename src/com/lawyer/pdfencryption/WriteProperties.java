package com.lawyer.pdfencryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteProperties {
	public void createpropertiesfile() throws IOException
	{
		Properties prop =new Properties();
		prop.setProperty("Public", "ram007");
		prop.setProperty("Private", "ram0075");
		File f=new File("C:\\proj\\keystore.properties");
		FileOutputStream fos=new FileOutputStream(f);
		prop.store(fos, "KeyFile");
		fos.close();
	}

}
