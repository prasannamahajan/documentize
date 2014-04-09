package com.lawyer.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

/**
 * ID class for entity: DocumentRelation
 *
 */ 
public class DocumentRelationPK  implements Serializable {   
   
	         
	private Integer documentId1;         
	private String paramName1;         
	private Integer documentId2;         
	private String paramName2;
	private static final long serialVersionUID = 1L;

	public DocumentRelationPK() {}

	

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
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof DocumentRelationPK)) {
			return false;
		}
		DocumentRelationPK other = (DocumentRelationPK) o;
		return true
			&& (getDocumentId1() == null ? other.getDocumentId1() == null : getDocumentId1().equals(other.getDocumentId1()))
			&& (getParamName1() == null ? other.getParamName1() == null : getParamName1().equals(other.getParamName1()))
			&& (getDocumentId2() == null ? other.getDocumentId2() == null : getDocumentId2().equals(other.getDocumentId2()))
			&& (getParamName2() == null ? other.getParamName2() == null : getParamName2().equals(other.getParamName2()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getDocumentId1() == null ? 0 : getDocumentId1().hashCode());
		result = prime * result + (getParamName1() == null ? 0 : getParamName1().hashCode());
		result = prime * result + (getDocumentId2() == null ? 0 : getDocumentId2().hashCode());
		result = prime * result + (getParamName2() == null ? 0 : getParamName2().hashCode());
		return result;
	}
   
   
}
