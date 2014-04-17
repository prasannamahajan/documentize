package com.lawyer.admin.document;

import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lawyer.entity.DocumentRelation;
import com.lawyer.filter.EntityManagerListener;
public class DocumentRelations {


	private static Logger logger = LoggerFactory.getLogger(DocumentRelations.class);
	public DocumentRelations() {
	
	}

	public boolean insertRelations(String file)
	{
	String[] lines = file.split("\n");
	logger.info("File = {}",file);
	for(String line:lines)
	{
		try{
		String[] values = line.split("-");
		logger.info("{} - {} - {} - {}",values[0],values[1],values[2],values[3]);
		DocumentRelation docRelation = new DocumentRelation();
		docRelation.setDocumentId1(Integer.parseInt(values[0].trim()));
		docRelation.setParamName1(values[1].trim());
		docRelation.setDocumentId2(Integer.parseInt(values[2].trim()));
		docRelation.setParamName2(values[3].trim());
		EntityManager em = EntityManagerListener.getEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.merge(docRelation);
		etx.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	return true;
	}
	

}
