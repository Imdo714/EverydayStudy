package com.matcha.mvc.template.vo;

public class TemplateImg {
	
	private int templateImgNo;
	private String templateImgUrl;
	private String templateOrginName;
	private String templateChangName;
	private int templateNo;
	
	public TemplateImg() {}
	
	
	public TemplateImg(int templateImgNo, String templateImgUrl, String templateOrginName, String templateChangName,
			int templateNo) {
		super();
		this.templateImgNo = templateImgNo;
		this.templateImgUrl = templateImgUrl;
		this.templateOrginName = templateOrginName;
		this.templateChangName = templateChangName;
		this.templateNo = templateNo;
	}


	public int getTemplateImgNo() {
		return templateImgNo;
	}


	public void setTemplateImgNo(int templateImgNo) {
		this.templateImgNo = templateImgNo;
	}


	public String getTemplateImgUrl() {
		return templateImgUrl;
	}


	public void setTemplateImgUrl(String templateImgUrl) {
		this.templateImgUrl = templateImgUrl;
	}


	public String getTemplateOrginName() {
		return templateOrginName;
	}


	public void setTemplateOrginName(String templateOrginName) {
		this.templateOrginName = templateOrginName;
	}


	public String getTemplateChangName() {
		return templateChangName;
	}


	public void setTemplateChangName(String templateChangName) {
		this.templateChangName = templateChangName;
	}


	public int getTemplateNo() {
		return templateNo;
	}


	public void setTemplateNo(int templateNo) {
		this.templateNo = templateNo;
	}


	@Override
	public String toString() {
		return "TemplateImg [templateImgNo=" + templateImgNo + ", templateImgUrl=" + templateImgUrl
				+ ", templateOrginName=" + templateOrginName + ", templateChangName=" + templateChangName
				+ ", templateNo=" + templateNo + "]";
	}
	

	
}
