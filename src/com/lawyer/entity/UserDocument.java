package com.lawyer.entity;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserDocument
 * 
 */
@Entity
@NamedQuery(name = "UserDocument.getDocumentsByUserId", query = "Select d.document_id,d.date,doc.documentName from UserDocument d,Document doc where d.user_id=:user_id and d.document_id = doc.documentId")
@IdClass(UserDocumentPK.class)
public class UserDocument implements Serializable {

	@Id
	private Integer user_id;
	@Id
	private Integer document_id;
	@Id
	private long date;
	public long getEpoch_time() {
		return date;
	}

	public void setEpoch_time(long epoch_time) {
		this.date = epoch_time;
	}

	private static final long serialVersionUID = 1L;

	public UserDocument() {
		super();
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

}
