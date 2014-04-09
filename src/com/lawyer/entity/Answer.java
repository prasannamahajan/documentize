package com.lawyer.entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;
import com.lawyer.entity.AnswerPK;

@Entity
@IdClass(AnswerPK.class)
public class Answer implements Serializable {

	   
	@Id
	private Integer userId;
	@Id
	private Integer documentId;
	@Id
	private long date;
	@Id
	private String paramName;
	private String answer;
	
	private static final long serialVersionUID = 1L;

	public Answer()
	{};
	
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Answer(Integer userId, Integer documentId, long date,
			String paramName, String answer) {
		super();
		this.userId = userId;
		this.documentId = documentId;
		this.date = date;
		this.paramName = paramName;
		this.answer = answer;
	}
	
	


   
}
