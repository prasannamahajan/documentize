package com.lawyer.filter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.User;
import com.lawyer.user.service.UserAccount;

/**
 * Application Lifecycle Listener implementation class InitialConfiguration
 *
 */
@WebListener
public class InitialConfiguration implements ServletContextListener {

	private static EntityManager em;
	private static User user;
	private static Logger logger = LoggerFactory
			.getLogger(InitialConfiguration.class);

    public InitialConfiguration() {
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	UserAccount account = new UserAccount();
    	User user = account.findUserByEmail("realprasanna007@gmail.com");
    	if(user==null)
    	{
    		user = new User("realprasanna007@gmail.com","02532bdc1420c1aab2c5c7699cdb4cb5", true,"prasanna","mahajan","pune", "pune", "Maharashtra", 411027, 8446266907L, 56658458, "admin");
    		EntityManager em = EntityManagerListener.getEntityManager();
    		em.getTransaction().begin();
    		em.persist(user);
    		em.getTransaction().commit();
    		logger.info("Admin account created");
    	}
    	else
    	{
    		EntityManager em = EntityManagerListener.getEntityManager();
    		user.setActive(true);
    		user.setRole("admin");
    		em.getTransaction().begin();
    		em.merge(user);
    		em.getTransaction().commit();	
    	}
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	
    }
	
}
