package com.lawyer.admin.document;
import java.util.List;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.Document;
import com.lawyer.filter.EntityManagerListener;
import com.oreilly.servlet.multipart.FilePart;
import com.lawyer.document.DocumentPath;
public class SampleDocument {
	
private static Logger logger = LoggerFactory.getLogger(SampleDocument.class);
	
	public List<Document> getDocumentList(int start,int limit)
	{
		
		 EntityManager em = EntityManagerListener.getEntityManager();
			EntityTransaction etx = em.getTransaction();
			TypedQuery<Document> query = em.createNamedQuery("Document.getDocumentList", Document.class);
			query.setFirstResult(start);
			query.setMaxResults(limit);	
			etx.begin();
			List<Document> documentList= query.getResultList();
			etx.commit();
			return documentList;
	}
	
	public Document getDocumentInformation(int documentId)
	{
		 EntityManager em = EntityManagerListener.getEntityManager();
			EntityTransaction etx = em.getTransaction();
			
			etx.begin();
			Document document = em.find(Document.class,documentId);
			etx.commit();
			return document;
	}
	
	public boolean updateDocumentInformation(Document document)
	{
		try{
		EntityManager em = EntityManagerListener.getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.merge(document);
		etx.commit();
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteDocument(int documentId)
	{
		Document document = getDocumentInformation(documentId);
		try{
			EntityManager em = EntityManagerListener.getEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			em.remove(document);
			etx.commit();
			return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
	
	}
}
