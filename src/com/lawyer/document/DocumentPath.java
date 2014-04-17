package com.lawyer.document;

import java.io.File;

// TODO: Auto-generated Javadoc
/**
 * The Class DocumentPath.
 */
public class DocumentPath {
	
	/** The fixed path. */
	private String fixedPath="C:\\";
	
	/**
	 * Instantiates a new document path.
	 *
	 * @param fixedPath must be request.getServletContext().getRealPath('/')
	 */
	public DocumentPath(String fixedPath) {
		this.fixedPath="C:\\experiments\\";
		// this.fixedPath = fixedPath+"\\";
	}
	
	/**
	 * Default to C:\\.
	 */
	public DocumentPath()
	{
		fixedPath="C:\\";
	}
	
	/**
	 * Gets the document folder path 
	 *  userdocument\\userid\\documentid\\documentdate
	 *
	 * @param userId the user id
	 * @param documentId the document id
	 * @param documentDate the document date
	 * @return the document folder path
	 */
	public String getDocumentFolderPath(int userId,int documentId,long documentDate)
	{
		String path="";
		path = fixedPath+"userdocument\\"+userId+"\\"+documentId+"\\"+documentDate;
		return path;
	}
	
	/**
	 * Gets the document path of generated document.
	 * userdocument\\userid\\documentid\\documentdate\\output.pdf
	 *
	 * @param userId the user id
	 * @param documentId the document id
	 * @param documentDate the document date
	 * @return the document path
	 */
	public String getDocumentPath(int userId,int documentId,long documentDate)
	{
		
		return getDocumentFolderPath(userId, documentId, documentDate)+"\\output.pdf";
	
	}
	
	/**
	 * Gets the sample document path of htm file.
	 * sampledocument\\documentid\\sample.htm
	 *
	 * @param documentId the document id
	 * @return the sample document path
	 */
	public String getSampleDocumentPath(int documentId)
	{
		String path="";
		path = fixedPath+"sampledocument\\"+documentId+"\\sample.htm";
		return path;
	}
	
	public String getSampleDocumentFolderPath(int documentId)
	{
		String path="";
		path = fixedPath+"sampledocument\\"+documentId+"\\";
		return path;
	}
	
	/**
	 * Gets the form json file path.
	 *
	 * @param documentId the document id
	 * @return the form json file path
	 */
	public String getFormJsonFilePath(int documentId)
	{
		String path="";
		path = fixedPath+"docjson\\"+documentId+".json";
		return path;
	}
	
	/**
	 * Creates the user document folder.
	 *
	 * @param userId the user id
	 * @param documentId the document id
	 * @param documentDate the document date
	 * @return true, if successful
	 */
	public boolean createUserDocumentFolder(int userId,int documentId,long documentDate)
	{
		String path="";
		path = fixedPath+"userdocument\\"+userId+"\\"+documentId+"\\"+documentDate;
		File newfolder = new File(path);
		if(!newfolder.exists())
			if(newfolder.mkdirs())
				return true;
		return false;	
	}
	
	/*This function will delete all files below folder and then folder*/
	 /**
	 * Delete dir of given path
	 *
	 * @param dir the dir
	 * @return true, if successful
	 */
	private boolean deleteDir(File dir) {
	      if (dir.isDirectory()) {
	         String[] children = dir.list();
	         for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	               return false;
	            }
	         }
	      }
	      return dir.delete();
	  }
	 
	/**
	 * Delete user document folder.
	 *
	 * @param userId the user id
	 * @param documentId the document id
	 * @param documentDate the document date
	 * @return true, if successful
	 */
	public boolean deleteUserDocumentFolder(int userId,int documentId,long documentDate)
	{
		String path="";
		path = fixedPath+"userdocument\\"+userId+"\\"+documentId+"\\"+documentDate;
		File folder= new File(path);
		if(deleteDir(folder))
			return true;
		return false;	
	}
	
}
