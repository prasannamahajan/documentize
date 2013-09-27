package com.lawyer.filter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@WebListener
public class EntityManagerListener implements ServletContextListener {

	private static EntityManager em;
	private static Logger logger = LoggerFactory
			.getLogger(EntityManagerListener.class);

	private EntityManagerListener() {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("Lawyer");
		em = emf.createEntityManager();
		logger.info("EntityManager Created : {}", em);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		em.close();
	}

	public static synchronized EntityManager getEntityManager() {
		if (em == null) {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("Lawyer");
			em = emf.createEntityManager();
		}
		return em;

	}
}
