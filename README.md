# EverydayStudy
> src == 프론트 <br>
> chatJAVA == 자바

## 프로젝트 개요
- 👋 내가 css쪽이 많이 부족한거 같아 매일 공부하면서 조금씩 만들었는데 
- 👀 이제는 나도 잔디를 깔아야 할거 같아서 매일 1일 1PUSH를 하기로 했어
- 🌱 매일 했는데 잔디가 없으니 아무도 안 믿어주는거 같아서 시작하기로 했어 😄😄
- 🐣 모든 페이지를 반응형 미디어 쿼리로 만들었어 공부 할거면 제대로 해야지
- 🕘 2024-04-08 부로 1일 1PUSH 시작할게


<details>
<summary>
  input password 폰트
</summary>
   <br>
  input type password를 사용하다보면 숨겨진 패스워드 표시(●)가 안 보이는 경우가 있다. <br>
  이런 문제가 발생하는 이유는 폰트가 (●)문자를 지원하지 않아서이다. <br>
  이 경우는 현재 사용중인 폰트가 해당 문자를 지원하지 않아서 발생하는 것으로 input type이 password인것의 font-family를 변경하면 해결된다. <br>

  input[type=password]{font-family:"굴림";}  <br>
  위와 같은 코드를 사용하면 input type=password 폰트만 굴림으로 바뀌는데 이렇게 변경하면 오류가 해결된다.
</details>

<details>
<summary>
  컨트롤러 보내는 법
</summary>
	
## 컨트롤러
DispatcherServlet을 생성한 후 url의 요청을 받아서 DispatcherServlet에서 처리한다. <br>
RequestMapping으로 설정할 것들을 Web에 들어가서 밑에 처럼 추가해준다

```
<!-- ===DispatcherServlet 생성===(Controller, ViewResolver, hanlderMapping) -->
<servlet>
  <!-- 이 서블릿 이름대로 (서블릿이름)-servlet.xml 파일을 DispatcherServlet으로 삼음 -->
  <servlet-name>dispatcher</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <!-- contextLoader가 아래 위치의 설정 파일을 읽어서 이 파일을 DispatcherServlet으로 만든다. -->
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/config/dispatcher-servlet.xml</param-value>
  </init-param>
</servlet>

<servlet-name>dispatcher</servlet-name>
  <url-pattern>*.do</url-pattern>
  <url-pattern>*.te</url-pattern>
  <url-pattern>*.ml</url-pattern>
  <url-pattern>*.di</url-pattern>
</servlet-mapping>   
```

## 서블릿
view의 경로, 확장자를 정해주는 부분 : DispatcherServlet이 이 경로를 따라서 컨트롤러가 날린 뷰의 이름에 해당하는 뷰가 있는지 찾아봄. <br>
 model에 대한 패키지명을 추가해주어야 한다 추가하지 않으면 못 찾는다
 
```
<!-- ViewResolver 생성 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  <property name="prefix" value="/WEB-INF/views/"/>
  <property name="suffix" value=".jsp"/>
</bean>

<!-- model에 대한 패키지명을 추가해준다 -->
<context:component-scan base-package="com.matcha.mvc" />
```
</details>

<details>
<summary>
  회원가입, 로그인 비밀번호 암호문으로 바꾸는 꿀팁 (Spring Security)
</summary>
  
  ## Spring Security는 Spring 기반의 애플리케이션의 보안(인증과 권한, 인가 등)을 담당하는 스프링 하위 프레임워크이다.
  - 인증(Authentication): 해당 사용자가 본인이 맞는지를 확인하는 절차
  - 인가(Authorization): 인증된 사용자가 요청한 자원에 접근 가능한지를 결정하는 절차

  ## Spring Security 사용하기 위해서는 pom.xml에 dependencies를 추가해주어야 합니다.
  ```
  <!-- 3. Spring Security Module(core, web, config) -->
  <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-core</artifactId>
      <version>5.5.2</version> 
   </dependency>
   <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-web</artifactId>
      <version>5.5.2</version>
   </dependency>
   <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-config</artifactId>
      <version>5.5.2</version>
   </dependency>
  ```
  ## web.xml 공통으로 사용할 의존성 설정 파일의 위치를 담는 파라미터 를 설정해줍니다.
  ```
  <context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>
  		/WEB-INF/config/spring-security.xml
  	</param-value>
  </context-param>
  ```
  ## 경로에 맞에 파일을 세팅한 후 spring-security.xml에 빈을 등록하면 세팅 끝
  ```
  <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:security="http://www.springframework.org/schema/security"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-5.5.xsd">

	  <bean class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" id="bcryptPasswordEncoder"/>
  </beans>
  ```
  ## 회원가입 하는 과정 (encode)
  encode : 해당 암호화 방식으로 암호화한 문자열을 리턴해줍니다. 회원가입 시 DB에 넣기전에 사용하면 됩니다. <br>
  DB확인을 하면 평문이였였던 비밀번호가 암호문으로 바꿔있는 것을 확인할 수 있다.
  ```
  @ResponseBody
  @RequestMapping(value="/insert.me", produces="application/json; charset=UTF-8")
    public String insert(Member m, Model model, ModelAndView mv) {
    
      // 암호화 작업
      String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
      
      m.setUserPwd(encPwd); // Member객체에 userPwd필드에 평문이 아닌 암호문으로 변경
      
      int result = memberService.insertMember(m);
    }
  ```
  ## 로그인 하는 과정 (matches)
  matches : Member m으로 들어온 비밀번호는 암호화되어 DB에 저장된 암호화된 비밀번호와 같은지 비교를 하여 확인할 수 있습니다.
  ````
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
  ````

</details>



<hr>

🌐내가 사용하는 언어 비율이야🌐 
<br>

![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=imdo714&layout=compact&theme=tokyonight)

<br>
깃허브에 대한 평판...🤧🤧
<br>

![Anurag's github stats](https://github-readme-stats.vercel.app/api?username=imdo714&show_icons=true&theme=tokyonight)





 


