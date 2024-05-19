package com.matcha.mvc.mail.service;

import com.matcha.mvc.mail.vo.Mail;

public interface mailService {

	// 메일 DB에 기록
	int sendMail(Mail m, int userNo);
}
