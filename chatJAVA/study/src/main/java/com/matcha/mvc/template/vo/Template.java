package com.matcha.mvc.template.vo;

import java.sql.Date;

public class Template {

	private int templateNo;
	private String templateTitle;
	private String templateContent;
	private Date templateDate;
	private int userNo;
	private int categoryNo;
	
	
	public Template() {}


	public Template(int templateNo, String templateTitle, String templateContent, Date templateDate, int userNo,
			int categoryNo) {
		super();
		this.templateNo = templateNo;
		this.templateTitle = templateTitle;
		this.templateContent = templateContent;
		this.templateDate = templateDate;
		this.userNo = userNo;
		this.categoryNo = categoryNo;
	}


	public int getTemplateNo() {
		return templateNo;
	}


	public void setTemplateNo(int templateNo) {
		this.templateNo = templateNo;
	}


	public String getTemplateTitle() {
		return templateTitle;
	}


	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}


	public String getTemplateContent() {
		return templateContent;
	}


	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}


	public Date getTemplateDate() {
		return templateDate;
	}


	public void setTemplateDate(Date templateDate) {
		this.templateDate = templateDate;
	}


	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public int getCategoryNo() {
		return categoryNo;
	}


	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}


	@Override
	public String toString() {
		return "Template [templateNo=" + templateNo + ", templateTitle=" + templateTitle + ", templateContent="
				+ templateContent + ", templateDate=" + templateDate + ", userNo=" + userNo + ", categoryNo="
				+ categoryNo + "]";
	}
	
	
}
