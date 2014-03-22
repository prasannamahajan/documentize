package com.lawyer.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@Entity
@NamedQuery(name="Document.getDocumentsByCatagory",query="Select d from Document d where d.catagory = :catagory")
public class Document implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	// This is not right
	//@Column(columnDefinition="MEDIUMINT NOT NULL AUTO_INCREMENT")
	private Integer documentId;
	private String catagory;
	
	
	private String documentName;
	private String documentDescription;
	private static final long serialVersionUID = 1L;
	
	public String getDocumentDescription() {
		return documentDescription;
	}
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}


	public Document() {
		super();
	}  
	
	public Document(String catagory, String documentName,
			String documentDescription) {
		super();
		this.catagory = catagory;
		this.documentName = documentName;
		this.documentDescription = documentDescription;
	}

	public Integer getDocument_id() {
		return this.documentId;
	}

	public void setDocument_id(Integer document_id) {
		this.documentId = document_id;
	}   
	public String getCatagory() {
		return this.catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}    
	public String getDocument_name() {
		return this.documentName;
	}

	public void setDocument_name(String document_name) {
		this.documentName = document_name;
	}   

   
}
