package com.matcha.mvc.member.vo;

public class MemberImg {

	private int memberImgNo;
	private String memberImgUrl;
	private String memberImgOrginName;
	private String memberImgChangName;
	private int userNo;
	
	public MemberImg() {}

	public MemberImg(int memberImgNo, String memberImgUrl, String memberImgOrginName, String memberImgChangName,
			int userNo) {
		super();
		this.memberImgNo = memberImgNo;
		this.memberImgUrl = memberImgUrl;
		this.memberImgOrginName = memberImgOrginName;
		this.memberImgChangName = memberImgChangName;
		this.userNo = userNo;
	}

	public int getMemberImgNo() {
		return memberImgNo;
	}

	public void setMemberImgNo(int memberImgNo) {
		this.memberImgNo = memberImgNo;
	}

	public String getMemberImgUrl() {
		return memberImgUrl;
	}

	public void setMemberImgUrl(String memberImgUrl) {
		this.memberImgUrl = memberImgUrl;
	}

	public String getMemberImgOrginName() {
		return memberImgOrginName;
	}

	public void setMemberImgOrginName(String memberImgOrginName) {
		this.memberImgOrginName = memberImgOrginName;
	}

	public String getMemberImgChangName() {
		return memberImgChangName;
	}

	public void setMemberImgChangName(String memberImgChangName) {
		this.memberImgChangName = memberImgChangName;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "MemberImg [memberImgNo=" + memberImgNo + ", memberImgUrl=" + memberImgUrl + ", memberImgOrginName="
				+ memberImgOrginName + ", memberImgChangName=" + memberImgChangName + ", userNo=" + userNo + "]";
	}
	
	
}
