package com.lawyer.entity;

import java.io.Serializable;


public class AnswerPK implements Serializable {
	private static final long serialVersionUID = 9081854936596488347L;
	private Integer userId;
	private Integer documentId;
	private long date;
	private String paramName;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public AnswerPK(Integer userId, Integer documentId, long date,
			String paramName) {
		super();
		this.userId = userId;
		this.documentId = documentId;
		this.date = date;
		this.paramName = paramName;
	}
	
	public AnswerPK()
	{}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result
				+ ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result
				+ ((paramName == null) ? 0 : paramName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		AnswerPK other = (AnswerPK) obj;
		if (date != other.date)
			return false;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (paramName == null) {
			if (other.paramName != null)
				return false;
		} else if (!paramName.equals(other.paramName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
	
	
}
