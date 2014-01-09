package com.lawyer.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
@Table(name = "user")
@NamedQuery(name="User.findUserByEmail",query="Select u FROM User u where u.email = :email")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer user_id;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private boolean active;
	@Column
	private String first_name;
	@Column
	private String last_name;
	@Column
	private String street_address;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private int postal_code;
	@Column
	private long phone_number;
	@Column
	private long registered_on;
	@Column
	private String role = "user";

	public User(String email, String password, boolean active,
			String first_name, String last_name, String street_address,
			String city, String state, int postal_code, long phone_number,
			long registered_on, String role) {
		super();
		this.email = email;
		this.password = password;
		this.active = active;
		this.first_name = first_name;
		this.last_name = last_name;
		this.street_address = street_address;
		this.city = city;
		this.state = state;
		this.postal_code = postal_code;
		this.phone_number = phone_number;
		this.registered_on = registered_on;
		this.role = role;
	}
	
	public User(int user_id,String email, 
			String first_name, String last_name, String street_address,
			String city, String state, int postal_code, long phone_number
			)
	{
		this.user_id = user_id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.street_address = street_address;
		this.city = city;
		this.state = state;
		this.postal_code = postal_code;
		this.phone_number = phone_number;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	public Integer getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFirst_name() {
		return this.first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return this.last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getStreet_address() {
		return this.street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPostal_code() {
		return this.postal_code;
	}

	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	public long getPhone_number() {
		return this.phone_number;
	}

	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}

	public long getRegistered_on() {
		return registered_on;
	}

	public void setRegistered_on(long registered_on) {
		this.registered_on = registered_on;
	}

}
