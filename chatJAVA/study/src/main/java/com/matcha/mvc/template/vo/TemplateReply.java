package com.matcha.mvc.template.vo;

import java.sql.Date;

public class TemplateReply {

	private int templateReplyNo;
	private String templateReplyContent;
	private Date templateReplyDate;
	private int userNo;
	private int templateNo;

	
	public TemplateReply() {}


	public TemplateReply(int templateReplyNo, String templateReplyContent, Date templateReplyDate, int userNo,
			int templateNo) {
		super();
		this.templateReplyNo = templateReplyNo;
		this.templateReplyContent = templateReplyContent;
		this.templateReplyDate = templateReplyDate;
		this.userNo = userNo;
		this.templateNo = templateNo;
	}


	public int getTemplateReplyNo() {
		return templateReplyNo;
	}


	public void setTemplateReplyNo(int templateReplyNo) {
		this.templateReplyNo = templateReplyNo;
	}


	public String getTemplateReplyContent() {
		return templateReplyContent;
	}


	public void setTemplateReplyContent(String templateReplyContent) {
		this.templateReplyContent = templateReplyContent;
	}


	public Date getTemplateReplyDate() {
		return templateReplyDate;
	}


	public void setTemplateReplyDate(Date templateReplyDate) {
		this.templateReplyDate = templateReplyDate;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public int getTemplateNo() {
		return templateNo;
	}


	public void setTemplateNo(int templateNo) {
		this.templateNo = templateNo;
	}


	@Override
	public String toString() {
		return "TemplateReply [templateReplyNo=" + templateReplyNo + ", templateReplyContent=" + templateReplyContent
				+ ", templateReplyDate=" + templateReplyDate + ", userNo=" + userNo + ", templateNo=" + templateNo
				+ "]";
	}
	
	
}
