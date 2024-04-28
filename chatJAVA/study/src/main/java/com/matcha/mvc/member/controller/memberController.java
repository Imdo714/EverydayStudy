package com.matcha.mvc.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.matcha.mvc.member.service.memberService;
import com.matcha.mvc.member.vo.Member;



@Controller
public class memberController {
	
	@Autowired
	memberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
//	마이 페이지가기
	@RequestMapping("/myPage.me")
	public String myPage(Model model){
		System.out.println("================");
		
		return "member/myPage";
	}

//	마이 페이지에서 내 템플릿 보기
	@RequestMapping("/myTemplate.me")
	public String mytemAll(Model model){
		
		return "member/myTemplateAll";
	}
	
//	마이 페이지에서 내 댓글 보기
	@RequestMapping("/myComment.me")
	public String mycomAll(Model model){
		
		return "member/myCommentAll";
	}

//	로그인
	@ResponseBody
	@RequestMapping(value="/loginMember.me", produces="application/json; charset=UTF-8")
    public String loginMember(Member m, HttpSession session, ModelAndView mv) {
        
		Member loginUser = memberService.loginMember(m.getUserId()); //아이디로만 멤버객체 가져오기
		
		if(loginUser == null || !bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) { // 로그인실패 => 에러문구를 requestScope에 담고 에러페이지로 포워딩
			mv.addObject("message", "로그인 실패");
			return new Gson().toJson(mv);
		} else {
			session.setAttribute("loginUser", loginUser);
			mv.addObject("message", "로그인 성공");
			
			return new Gson().toJson(mv);
		}
	
    }
	
	
//	회원가입
	@ResponseBody
	@RequestMapping(value="/insert.me", produces="application/json; charset=UTF-8")
    public String insert(Member m, Model model, ModelAndView mv) {
		
		int res = memberService.selectMember(m.getUserId()); // 아이디 있나 없나 체크

		if(res > 0) {
			mv.addObject("message", "아이디있음");
			return new Gson().toJson(mv);
		} else {
				// 암호화 작업
				String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
				
				m.setUserPwd(encPwd); // Member객체에 userPwd필드에 평문이 아닌 암호문으로 변경
				
				int result = memberService.insertMember(m);
				
				if(result > 0) {
					mv.addObject("message", "성공");
					return new Gson().toJson(mv);
				} else {
					mv.addObject("message", "실패");
					return new Gson().toJson(mv);
				}
			
		}
		
    }
	
	
	
	
	
}
