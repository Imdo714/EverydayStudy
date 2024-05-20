package com.matcha.mvc.chatting.vo;

public class Chatting {

	private String msg;
	private String name;
	private String time;
	
	public Chatting() {}

	public Chatting(String msg, String name, String time) {
		super();
		this.msg = msg;
		this.name = name;
		this.time = time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Chatting [msg=" + msg + ", name=" + name + ", time=" + time + "]";
	}
	
	
}
