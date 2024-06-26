package com.matcha.mvc.member.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.matcha.mvc.member.service.memberService;
import com.matcha.mvc.member.vo.Member;
import com.matcha.mvc.member.vo.MemberImg;
import com.matcha.mvc.template.service.templateService;



@Controller
public class memberController {
	
	@Autowired
	memberService memberService;
	
	@Autowired
	templateService templateService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
// 로그아웃
	@RequestMapping(value="/logout.me")
    public String logout(HttpSession session) {
		
		session.removeAttribute("loginUser");
		
        return "redirect:/";
    }

//	로그인
	@ResponseBody
	@RequestMapping(value="/loginMember.me", produces="application/json; charset=UTF-8")
    public String loginMember(Member m, HttpSession session, ModelAndView mv) {
        
		Member loginUser = memberService.loginMember(m.getUserId()); //아이디로만 멤버객체 가져오기
		
		if(loginUser == null || !bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) { // 로그인실패 => 에러문구를 message에 담고 리턴
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
				
				int result = memberService.insertMember(m); // 암호문으로 바꾼 비밀번호 진짜 회원가입
				
				MemberImg mi = new MemberImg();
				
				if("남자".equals(m.getGender())) {
					mi.setMemberImgUrl("/study/resources/img/profile/기본프로필.png"); // 임이로 그냥 만듬
					mi.setMemberImgOrginName("기본프로필.png");
					mi.setMemberImgChangName("/study/resources/img/profile/기본프로필.png");
				} else {
					mi.setMemberImgUrl("/study/resources/img/profile/여자기본프로필.png"); 
					mi.setMemberImgOrginName("여자기본프로필.png");
					mi.setMemberImgChangName("/study/resources/img/profile/여자기본프로필.png");
				}
				
				int profile = memberService.defaultImg(mi);
				
				mv.addObject("message", result > 0 ? "성공" : "실패");
				return new Gson().toJson(mv);
		}
    }
	
//	마이 페이지가기
	@RequestMapping("/myPage.me")
	public ModelAndView myPage(HttpSession session, ModelAndView mv){
		
		Member m = (Member) session.getAttribute("loginUser");
		
		MemberImg mi = memberService.selectMemberImg(m.getUserNo());
		
		Member user = memberService.selectUser(m.getUserNo());
		
		mv.addObject("memberImg", mi).addObject("user", user).setViewName("member/myPage");
		
      return mv;
	}
	
//	마이페이지 레벨 구하는 메서드
	@ResponseBody
	@RequestMapping(value="/myPageLevel.me", produces="application/json; charset=UTF-8")
    public String MemberLv(HttpSession session, ModelAndView mv) {
        
		Member m = (Member) session.getAttribute("loginUser");
		
		int templateCount = templateService.userTemplateCount(m.getUserNo());
		
		int replyCount = templateService.userReplyCount(m.getUserNo());
		
		mv.addObject("templateCount", templateCount).addObject("replyCount", replyCount);
		
		return new Gson().toJson(mv);
    }
	
	
//	마이 페이지가기
	@RequestMapping("/myUpdate.me")
	public String myUpdate(Member m, HttpSession session, ArrayList<MultipartFile> upfile, ModelAndView mv){

		Member me = (Member) session.getAttribute("loginUser");
		
		for(MultipartFile mf : upfile) {
			if(mf != null && !mf.isEmpty()) {
				MemberImg mi = new MemberImg();
				String changeName = saveFile(mf, session, "resources/img/profile/memberProfile/");
				
				mi.setMemberImgUrl("resources/img/profile/memberProfile/");
				mi.setMemberImgOrginName(mf.getOriginalFilename());
				mi.setMemberImgChangName("resources/img/profile/memberProfile/" + changeName);
				
				int upImg = memberService.updateImg(mi, me.getUserNo());
			}
		}
		
		int upMember = memberService.updateMember(m, me.getUserNo());
		
		session.setAttribute("alertMsg", upMember > 0 ? "업데이트 성공" : "업데이트 실패");
		
      return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String saveFile(MultipartFile upfile, HttpSession session, String filepath) {
		//파일명 수정 후 서버 업로드 시키기(기존파일명 -> 202311091027+5자리랜덤숫자+파일형식)
		//년월일시분초 + 랜덤숫자 5개 + 확장자 
		
		//원래파일명
		String originName = upfile.getOriginalFilename();
		
		//시간정보(년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(0));
		
		//랜덤숫자 5자리
		int ranNum = (int)(Math.random()* 90000) + 10000;// 10000부터 99999까지
		
		//확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		//변경된 이름
		String changeName = currentTime + ranNum + ext;
		
		//첨부파일 저장할 폴더의 물리적인 경로
		String savePath = session.getServletContext().getRealPath(filepath);
		
		try {
			upfile.transferTo(new File(savePath + changeName));//업로드 파일의 정보를 변경해서넣어줘 
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		return changeName;
	}
}
