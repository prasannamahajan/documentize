package com.lawyer.entity;

import java.io.Serializable;
import java.lang.Integer;

/**
 * ID class for entity: User_document
 *
 */ 
public class UserDocumentPK  implements Serializable {   
   
	         
	private Integer user_id;         
	private Integer document_id;
	private static final long serialVersionUID = 1L;

	public UserDocumentPK() {}

	

	public UserDocumentPK(Integer user_id, Integer document_id) {
		super();
		this.user_id = user_id;
		this.document_id = document_id;
	}



	public Integer getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	

	public Integer getDocument_id() {
		return this.document_id;
	}

	public void setDocument_id(Integer document_id) {
		this.document_id = document_id;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof UserDocumentPK)) {
			return false;
		}
		UserDocumentPK other = (UserDocumentPK) o;
		return true
			&& (getUser_id() == null ? other.getUser_id() == null : getUser_id().equals(other.getUser_id()))
			&& (getDocument_id() == null ? other.getDocument_id() == null : getDocument_id().equals(other.getDocument_id()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getUser_id() == null ? 0 : getUser_id().hashCode());
		result = prime * result + (getDocument_id() == null ? 0 : getDocument_id().hashCode());
		return result;
	}
   
   
}
