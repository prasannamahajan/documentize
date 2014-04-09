package com.lawyer.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DocumentRelation
 *
 */
@Entity

@IdClass(DocumentRelationPK.class)
public class DocumentRelation implements Serializable {

	   
	@Id
	private Integer documentId1;   
	@Id
	private String paramName1;   
	@Id
	private Integer documentId2;   
	@Id
	private String paramName2;
	private static final long serialVersionUID = 1L;

	public DocumentRelation() {
		super();
	} 
	
	public DocumentRelation(Integer documentId1, String paramName1,
			Integer documentId2, String paramName2) {
		super();
		this.documentId1 = documentId1;
		this.paramName1 = paramName1;
		this.documentId2 = documentId2;
		this.paramName2 = paramName2;
	}

	public Integer getDocumentId1() {
		return this.documentId1;
	}

	public void setDocumentId1(Integer documentId1) {
		this.documentId1 = documentId1;
	}   
	public String getParamName1() {
		return this.paramName1;
	}

	public void setParamName1(String paramName1) {
		this.paramName1 = paramName1;
	}   
	public Integer getDocumentId2() {
		return this.documentId2;
	}

	public void setDocumentId2(Integer documentId2) {
		this.documentId2 = documentId2;
	}   
	public String getParamName2() {
		return this.paramName2;
	}

	public void setParamName2(String paramName2) {
		this.paramName2 = paramName2;
	}
   
}
