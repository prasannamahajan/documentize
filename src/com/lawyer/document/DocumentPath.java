package com.lawyer.document;

import java.io.File;

public class DocumentPath {
	private String fixedPath="C:\\";
	/**
	 * @param fixedPath must be request.getServletContext().getRealPath('/')
	 */
	public DocumentPath(String fixedPath) {
		this.fixedPath = fixedPath+"\\";
	}
	
	/**
	 * Default to C:\\
	 */
	public DocumentPath()
	{}
	
	public String getDocumentFolderPath(int userId,int documentId,long documentDate)
	{
		String path="";
		path = fixedPath+"userdocument\\"+userId+"\\"+documentId+"\\"+documentDate;
		return path;
	}
	
	public String getDocumentPath(int userId,int documentId,long documentDate)
	{
		
		return getDocumentFolderPath(userId, documentId, documentDate)+"\\output.pdf";
	
	}
	
	public String getSampleDocumentPath(int documentId)
	{
		String path="";
		path = fixedPath+"sampledocument\\"+documentId+"\\sample.htm";
		return path;
	}
	
	public String getFormJsonFilePath(int documentId)
	{
		String path="";
		path = fixedPath+"jsondoc\\"+documentId+".json";
		return path;
	}
	
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
