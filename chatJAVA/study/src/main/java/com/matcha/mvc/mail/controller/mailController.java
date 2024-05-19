package com.matcha.mvc.mail.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.matcha.mvc.mail.service.mailService;
import com.matcha.mvc.mail.vo.Mail;
import com.matcha.mvc.member.vo.Member;

@Controller
public class mailController {
	
	@Autowired
	private mailService mailService;
	
	@Autowired
	private JavaMailSender sender;
	

	@RequestMapping("/mail.ml")
	public String mail(Model model){
		
		return "mail/mail";
	}
	
	@RequestMapping("/insertM.ml")
	public String insertM(Model model){
		
		return "mail/insertMail";
	}
	
	@RequestMapping("/sendM.ml")
	public String sendM(Mail m, HttpSession session){
		// 이메일 메시지 객체 생성
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setSubject(m.getMailTitle()); // 제목
		message.setText(m.getMailContent()); // 내용
		
		String[] to = {m.getMailGetName()};	// 받는 사람
		message.setTo(to);
		
		String[] cc = {"gusehdla2489@gmail.com"};	// 참조 (같이 볼수 있음)
		message.setCc(cc);
		// 이메일 전송
		sender.send(message);
		
		Member member = (Member) session.getAttribute("loginUser");
		int res = mailService.sendMail(m, member.getUserNo());
		
		return "mail/mail";
	}
	

}
