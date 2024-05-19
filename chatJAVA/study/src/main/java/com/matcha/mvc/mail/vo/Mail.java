package com.matcha.mvc.mail.vo;

public class Mail {

	private int mailNo;
	private String mailGetName;
	private String mailTitle;
	private String mailContent;
	private int userNo;

	public Mail() {}

	public Mail(int mailNo, String mailGetName, String mailTitle, String mailContent, int userNo) {
		super();
		this.mailNo = mailNo;
		this.mailGetName = mailGetName;
		this.mailTitle = mailTitle;
		this.mailContent = mailContent;
		this.userNo = userNo;
	}

	public int getMailNo() {
		return mailNo;
	}

	public void setMailNo(int mailNo) {
		this.mailNo = mailNo;
	}

	public String getMailGetName() {
		return mailGetName;
	}

	public void setMailGetName(String mailGetName) {
		this.mailGetName = mailGetName;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "Mail [mailNo=" + mailNo + ", mailGetName=" + mailGetName + ", mailTitle=" + mailTitle + ", mailContent="
				+ mailContent + ", userNo=" + userNo + "]";
	}

	
	
}
