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
	private long date;
	private static final long serialVersionUID = 1L;

	public long getEpoch_time() {
		return date;
	}



	public void setEpoch_time(long epoch_time) {
		this.date = epoch_time;
	}



	public UserDocumentPK() {}

	

	public UserDocumentPK(Integer user_id, Integer document_id,long epoch_time) {
		super();
		this.user_id = user_id;
		this.document_id = document_id;
		this.date = epoch_time;
	}



	public long getDate() {
		return date;
	}



	public void setDate(long date) {
		this.date = date;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((document_id == null) ? 0 : document_id.hashCode());
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDocumentPK other = (UserDocumentPK) obj;
		if (document_id == null) {
			if (other.document_id != null)
				return false;
		} else if (!document_id.equals(other.document_id))
			return false;
		if (date != other.date)
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
   
   
   
}
