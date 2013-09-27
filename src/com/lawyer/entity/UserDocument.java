package com.lawyer.entity;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserDocument
 * 
 */
@Entity
@NamedQuery(name = "UserDocument.getDocumentsByUserId", query = "Select d.document_id,doc.documentName from UserDocument d,Document doc where d.user_id=:user_id and d.document_id = doc.documentId")
@IdClass(UserDocumentPK.class)
public class UserDocument implements Serializable {

	@Id
	private Integer user_id;
	@Id
	private Integer document_id;
	private byte[] pdf_file;
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

	public byte[] getPdf_file() {
		return this.pdf_file;
	}

	public void setPdf_file(byte[] pdf_file) {
		this.pdf_file = pdf_file;
	}

}
