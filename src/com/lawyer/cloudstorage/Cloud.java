package com.lawyer.cloudstorage;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.lawyer.storageservice.Xmlgeneric;
import com.microsoft.windowsazure.services.core.storage.*;
import com.microsoft.windowsazure.services.blob.client.*;
public class Cloud {
	public static final String storageConnectionString = 
		    "DefaultEndpointsProtocol=http;" + 
		    "AccountName=agreeyastorage;" + 
		    "AccountKey=jH9S/UudYLfSFj7TGAxAycDRaaunMcDt9Cg/Ei/Cqi3HuwLeblMtsGk92FMLD38p43+UawObPtDe3ImRTYkwrA==";
   
	public void cloudfunction(int i,String uri) throws InvalidKeyException, URISyntaxException, StorageException, FileNotFoundException, IOException
	{
		if(i==1)
		{
		createcontainer();
		}
		if(i==2)
		{
			System.out.println("In cloud");
			storepdf(uri);
		}
	}

	private void storepdf(String uri) throws InvalidKeyException, URISyntaxException, StorageException, FileNotFoundException, IOException {
		CloudStorageAccount storageAccount =CloudStorageAccount.parse(storageConnectionString);
             
			// Create the blob client
			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			// Retrieve reference to a previously created container
			CloudBlobContainer container = blobClient.getContainerReference("userdocumentstorage");
           Xmlgeneric xg=new Xmlgeneric();
           
			// Create or overwrite the "myimage.jpg" blob with contents from a local file
//			CloudBlockBlob blob = container.getBlockBlobReference(xg.documentstring);
//		    File source=new File(uri+"temp"+"//"+xg.documentstring);
//			blob.upload(new FileInputStream(source), source.length());
/*			boolean success = (new File
			         (uri+"temp"+"//"+xg.documentstring)).delete();
			         if (success) {
			            System.out.println("The file has been successfully deleted"); 
			         }*/
		
	}

	private void createcontainer() throws InvalidKeyException, URISyntaxException, StorageException {
		// TODO Auto-generated method stub
		CloudStorageAccount storageAccount =CloudStorageAccount.parse(storageConnectionString);
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
        CloudBlobContainer container = blobClient.getContainerReference(" ");
        container.createIfNotExist();
		
	}

}
