package com.lawyer.document;
import java.util.List;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.UserDocument;
import com.lawyer.entity.UserDocumentPK;
import com.lawyer.filter.EntityManagerListener;



public class DocumentCRUD {

	private static Logger logger = LoggerFactory.getLogger(DocumentCRUD.class);
	
	public boolean createDocument(int userId,int documentId,long time)
	{
		UserDocument document = new UserDocument(userId,documentId,time);
		EntityManager em = EntityManagerListener.getEntityManager();
		try{
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			em.merge(document);
			etx.commit();
		}
		catch(Exception e)
		{
			logger.info("Exception while inserting document");
			logger.info("EntityManager : {}",em);
			return false;
		}
		return true;
	}
	
	public boolean deleteDocument(int userId,int documentId,long time)
	{
		UserDocumentPK key = new UserDocumentPK(userId, documentId, time);
		EntityManager em = EntityManagerListener.getEntityManager();
		try{
			EntityTransaction etx = em.getTransaction();
		
			UserDocument document = em.find(UserDocument.class, key);
			etx.begin();
			em.remove(document);
			etx.commit();
		}
		catch(Exception e)
		{
			logger.info("Exception while inserting document");
			logger.info("EntityManager : {}",em);
			return false;
		}
	
		return true;
	}
	
	
	
}