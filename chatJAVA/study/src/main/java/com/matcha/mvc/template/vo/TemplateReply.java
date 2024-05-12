package com.matcha.mvc.template.vo;

import java.sql.Date;

public class TemplateReply {

	private int templateReplyNo;
	private String templateReplyContent;
	private Date templateReplyDate;
	private int userNo;
	private int templateNo;
	private String userName;
	private String memberImgUrl;
	private String memberImgChangName;

	
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
	
// 이미지
	public TemplateReply(int templateReplyNo, String templateReplyContent, Date templateReplyDate, int userNo,
			int templateNo, String userName, String memberImgUrl, String memberImgChangName) {
		super();
		this.templateReplyNo = templateReplyNo;
		this.templateReplyContent = templateReplyContent;
		this.templateReplyDate = templateReplyDate;
		this.userNo = userNo;
		this.templateNo = templateNo;
		this.userName = userName;
		this.memberImgUrl = memberImgUrl;
		this.memberImgChangName = memberImgChangName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	public String getMemberImgUrl() {
		return memberImgUrl;
	}


	public void setMemberImgUrl(String memberImgUrl) {
		this.memberImgUrl = memberImgUrl;
	}


	public String getMemberImgChangName() {
		return memberImgChangName;
	}


	public void setMemberImgChangName(String memberImgChangName) {
		this.memberImgChangName = memberImgChangName;
	}


	@Override
	public String toString() {
		return "TemplateReply [templateReplyNo=" + templateReplyNo + ", templateReplyContent=" + templateReplyContent
				+ ", templateReplyDate=" + templateReplyDate + ", userNo=" + userNo + ", templateNo=" + templateNo
				+ ", userName=" + userName + ", memberImgUrl=" + memberImgUrl + ", memberImgChangName="
				+ memberImgChangName + "]";
	}


	

	
}
