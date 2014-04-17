package com.lawyer.document;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.lawyer.entity.*;

import com.lawyer.filter.EntityManagerListener;

public class DocumentQueries implements Serializable {

	public DocumentQueries() {
		// TODO Auto-generated constructor stub
	}
	
	public int getDocumentCount(int userId)
	{
		int total = 0;
		try{
		EntityManager em = EntityManagerListener.getEntityManager();
		EntityTransaction etx = em.getTransaction();
				Query totalquery = em.createNativeQuery("Select Count(*) from UserDocument u where u.user_id=:user_id");
				totalquery.setParameter("user_id", userId);
		etx.begin();
	total = Integer.parseInt(totalquery.getSingleResult().toString());
		etx.commit();
		
		
		}
		catch(Exception e)
		{
			
		}
		return total;
		
	}
	
	public List getDocumentList(int userId,int start,int limit)
	{
		EntityManager em = EntityManagerListener.getEntityManager();
		EntityTransaction etx = em.getTransaction();
		Query query = em
				.createNativeQuery("Select userdoc.document_id,doc.documentName,userdoc.date "
						+ "from UserDocument userdoc,Document doc"
						+ " where "
						+ "userdoc.user_id=:user_id "
						+ "and "
						+ "userdoc.document_id = doc.documentId");
						//+"order by userdoc.date desc");
		query.setParameter("user_id", userId);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		
		List allDocuments;
		etx.begin();
		allDocuments = query.getResultList();
		etx.commit();
		return allDocuments;
	}

}
