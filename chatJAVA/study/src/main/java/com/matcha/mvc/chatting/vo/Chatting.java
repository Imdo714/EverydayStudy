package com.matcha.mvc.chatting.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Chatting {

//	private String msg;
//	private String name;
//	private String time;
	
	private int messageNo; // 채팅 메세지 번호
	private int roomNo; // 채팅 방 번호
	private String chattingContent; // 채팅 메세지
	private Date chattingDate; // 채팅 날짜/시간
	private int userNo;      // 보낸 사람 

}
