package com.lawyer.user.service;

import java.util.List;

import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawyer.entity.*;
import com.lawyer.filter.EntityManagerListener;

//import com.lawyer.user.service.Mail;

/**
 * The Class UserAccount provide all kind of interaction with class User
 */
public class UserAccount implements Users {

	private static Logger logger = LoggerFactory.getLogger(UserAccount.class);

	/**
	 * Find user by email.
	 * 
	 * @param email
	 *            the email of user
	 * @return the user if successful otherwise null
	 */
	public User findUserByEmail(String email) {
		EntityManager em = EntityManagerListener.getEntityManager();
		User user;
		user = null;
		try {
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			TypedQuery<User> query = em.createNamedQuery(
					"User.findUserByEmail", User.class);
			query.setParameter("email", email);
			List<User> list = query.getResultList();
			etx.commit();
			if (list.isEmpty())
				user = null;
			else
				user = list.get(0);

			logger.info("email:{} , user:{}", email, user);
			logger.info("EntityManager Factory = {}", etx);
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
			logger.info("Error occured while finding account : {}", email);
			logger.info("EntityManager em = {}", em);

		}

		return user;
	}
	
	public User findUserbyId(int id)
	{
		EntityManager em = EntityManagerListener.getEntityManager();
		User user;
		user = null;
		try {
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			user = em.find(User.class, id);
			etx.commit();
			

		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}

		return user;
	}

	/**
	 * Checks if User account is activated.
	 * 
	 * @param email
	 *            the email address of user
	 * @return true, if is account activated otherwise returns false
	 */
	public boolean isAccountActivated(String email) {
		User user = null;
		user = findUserByEmail(email);
		if (user != null)
			if (user.getActive())
				return true;
		return false;
	}

	/**
	 * Activate user account
	 * 
	 * @param email
	 *            the email address of user
	 * @return true, if successfully activated
	 */
	public boolean ActivateAccount(String email) {
		User user = null;
		user = findUserByEmail(email);
		EntityManager em = EntityManagerListener.getEntityManager();
		if (user == null) {
			logger.info("User does not exist");
			return false;
		}
		try {
			EntityTransaction etx = em.getTransaction();
			user.setActive(true);

			etx.begin();
			em.merge(user);
			etx.commit();
		} catch (Exception e) {
			System.out.println("Exception occurs while activating account");
			logger.error(
					"Activatation of account failed for email = {} , EntityManager = {} ",
					email, em);
			return false;
		}
		return true;
	}

	/**
	 * Deactivate User account.
	 * 
	 * @param email
	 *            the email address of user
	 * @return true, if successful else false
	 */
	public boolean DeactivateAccount(String email) {
		User user = null;
		user = findUserByEmail(email);
		EntityManager em = EntityManagerListener.getEntityManager();
		if (user == null)
			return false;
		try {

			EntityTransaction etx = em.getTransaction();
			user.setActive(false);
			etx.begin();
			em.merge(user);
			etx.commit();
		} catch (Exception e) {
			System.out.println("Exception occurs while Deactivating account");
			logger.error("Exception occurs while Deactivating account");
			em.getTransaction().rollback();
			return false;
		}
		return true;
	}

	/**
	 * Register Used to register new user
	 * 
	 * @param first_name
	 *            the first name of user
	 * @param last_name
	 *            the last name of user
	 * @param email
	 *            the email of user
	 * @param password
	 *            the password of user
	 * @param street_address
	 *            the street address of user
	 * @param city
	 *            the city of user
	 * @param state
	 *            the state of user
	 * @param postal_code
	 *            the postal code of user
	 * @param phone_number
	 *            the phone number of user
	 * @return true, if successful registration
	 */
	public boolean register(String first_name, String last_name, String email,
			String password, String street_address, String city, String state,
			int postal_code, long phone_number) {
		User user = findUserByEmail(email);
		EntityManager em = EntityManagerListener.getEntityManager();

		if (user == null || !user.getActive()) {
			try {
				long registered_on = System.currentTimeMillis() / 1000L;
				boolean active = false;
				String role = "user";
				User newuser = new User(email, password, active, first_name,
						last_name, street_address, city, state, postal_code,
						phone_number, registered_on, role);
				if(user != null)
					newuser.setUser_id(user.getUser_id());
				em.getTransaction().begin();
				em.merge(newuser);
				em.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				logger.error("Error occured while registering email: {}", email);
				return false;
			}
		} else
			return false;
	}

	/**
	 * Update user account
	 * 
	 * @param first_name
	 *            the first name of user
	 * @param last_name
	 *            the last name of user
	 * @param email
	 *            the email of user
	 * @param street_address
	 *            the street address of user
	 * @param city
	 *            the city of user
	 * @param state
	 *            the state of user
	 * @param postal_code
	 *            the postal code of user
	 * @param phone_number
	 *            the phone_number of user
	 * @return true, if update successful
	 */
	public boolean update(String first_name, String last_name, String email,
			String street_address, String city, String state, int postal_code,
			long phone_number) {
		User user = findUserByEmail(email);
		EntityManager em = EntityManagerListener.getEntityManager();
		if (user != null) {
			try {

				user.setActive(true);
				user.setFirst_name(first_name);
				user.setLast_name(last_name);
				user.setEmail(email);
				user.setStreet_address(street_address);
				user.setCity(city);
				user.setState(state);
				user.setPostal_code(postal_code);
				user.setPhone_number(phone_number);

				em.getTransaction().begin();
				em.merge(user);
				em.getTransaction().commit();
				logger.info("User updated his info user:{}", user);
				return true;
			} catch (Exception e) {
				logger.error(
						"Error occured while Updating account , email = {}",
						email);
				e.printStackTrace();
				em.getTransaction().rollback();

				return false;
			}
		} else {

			return false;
		}
	}

	/**
	 * Authenticate user by checking email and password match or not
	 * 
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @return true, if successful
	 */
	public boolean authenticate(String email, String password) {
		User user = null;
		user = findUserByEmail(email);
		if (user != null)
			if (user.getPassword().equals(password) && user.getActive() == true) {
				logger.info("email : {} authenticated", email);
				return true;
			}
		logger.info("Email : {} not activated or password is incorrect", email);
		return false;
	}

	/**
	 * Reset password of user
	 * 
	 * @param email
	 *            the email
	 * @param password
	 *            the password
	 * @return true, if successful
	 */
	public boolean resestPassword(String email, String password) {
		User user = null;
		user = findUserByEmail(email);
		EntityManager em = EntityManagerListener.getEntityManager();
		if (user == null) {
			return false;
		}
		try {
			EntityTransaction etx = em.getTransaction();
			user.setPassword(password);

			etx.begin();
			em.merge(user);
			etx.commit();
		} catch (Exception e) {
			// System.out.println("Exception occurs while resetting password");
			logger.info("Reset Password Failed for email = {}", email);

			return false;
		}

		logger.info("Password for email : {} successfully updated", email);
		return true;
	}
}
