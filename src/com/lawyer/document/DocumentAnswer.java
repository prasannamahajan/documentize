package com.lawyer.document;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import javax.persistence.*;
import org.json.JSONException;
import org.json.JSONObject;

import com.lawyer.entity.Answer;
import com.lawyer.filter.EntityManagerListener;

public class DocumentAnswer {
	
	public void saveAnswers(int userId,int documentId,long date,TreeMap<String,String> treemap)
	{
		
		Iterator<String> it = treemap.keySet().iterator();
		while(it.hasNext())
		{
			String key=it.next();
			String value = treemap.get(key);
			
			EntityManager em = EntityManagerListener.getEntityManager();
			try{
				if(value.length()>=1)
				{
				Answer answer = new Answer(userId, documentId, date, key, value);
				EntityTransaction etx = em.getTransaction();
				etx.begin();
				em.merge(answer);
				etx.commit();
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	public JSONObject getAutoAnswers(int userId,int documentId)
	{
		JSONObject reply = new JSONObject();
		EntityManager em = EntityManagerListener.getEntityManager();
		try{
			EntityTransaction etx = em.getTransaction();
			Query query = em.createNativeQuery("select a1.paramName,a1.answer from " +
					"(select r.paramName1 as paramName,u.answer as answer " +
					"from documentrelation r,answer u " +
					"where r.documentId2 = u.documentId " +
					"and " +
					"r.paramName2 = u.paramName " +
					"and r.documentId1=? and u.userId=?) as a1" +
					" group by a1.answer" +
					" having count(a1.answer) >= all (select count(a2.answer)" +
					" from" +
					" (select r.paramName1 as paramName,u.answer as answer " +
					"from documentrelation r,answer u " +
					"where" +
					" r.documentId2 = u.documentId and r.paramName2 = u.paramName and r.documentId1=? and u.userId=?) as a2 where a2.paramName = a1.paramName " +
					"group by a2.answer )");
			query.setParameter(1, documentId);
			query.setParameter(2,userId);
			query.setParameter(3, documentId);
			query.setParameter(4,userId);
			List allAnswer;
			
			etx.begin();
			allAnswer = query.getResultList();
			etx.commit();
			
			Iterator it = allAnswer.iterator();
			JSONObject docData = new JSONObject();
			
			for (; it.hasNext();) {
				Object[] answer = (Object[]) it.next();
				
				String paramName = (String) answer[0];
				String documentAnswer = (String) answer[1];
				docData.put(paramName, documentAnswer);
			}
			reply.put("data", docData);
			reply.put("success", true);
		}
		catch(Exception e)
		{
			try {
				reply.put("success", false);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return reply;
	}

}
