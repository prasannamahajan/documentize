package com.lawyer.user.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.lawyer.entity.Lawyer;
import com.lawyer.filter.EntityManagerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LawyerAccount {
	public static Logger logger = LoggerFactory.getLogger(LawyerAccount.class);
	public Lawyer getLawyerByEmail(String email) {
		Lawyer lawyer;
		lawyer = null;
		EntityManager em = EntityManagerListener.getEntityManager();
		try {
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			lawyer = (Lawyer) em
					.createQuery("from Lawyer where email = :email")
					.setParameter("email", email).getSingleResult();
			etx.commit();
		} catch (Exception e) {
			lawyer = null;
			logger.error("Caught Exception while finding Lawyer from email address");
		}
		
		return lawyer;
	}
	
	public Lawyer getLawyerById(int id)
	{
		Lawyer lawyer;
		lawyer = null;
		EntityManager em = EntityManagerListener.getEntityManager();
		try {
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			lawyer = em.find(Lawyer.class, id);
			etx.commit();
		} catch (Exception e) {
			lawyer = null;
			logger.error("Caught Exception while finding Lawyer from email address");
		}
		
		return lawyer;
	}

}
