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

<details>
<summary>
  썸머노트 파일 업로드 및 삭제 꿀팁 !
</summary>
	
   ## 썸머노트 에디터를 적용한 callbacks함수를 사용하여여 자바스크립트 구현 
   onImageUpload : 이미지를 첨부할때 실행되는 함수  <br>
   onMediaDelete : 이미지를 삭재하였을때 실행되는 함수
   ````
makeNote = () => {
    $('.summernote').summernote({
      placeholder: 'Hello stand alone ui',
        tabsize: 2,
        height: 500,

        callbacks:{
          onImageUpload : function(files, editor,	welEditable) {
            console.log("이미지 첨부 됨");
            console.log(files);
            for (var i = files.length - 1; i >= 0; i--) {
                console.log(files[i],this);
                sendFile(files[i],this);
            }
          },
          onMediaDelete : function ($target, editor, $editable) {
            var deletedImageUrl = $target

                .attr('src')
                .split('/')
                .pop()

                // $target.attr('src'): 삭제된 미디어 요소의 src 속성을 통해 삭제된 이미지의 URL을 가져옵니다.
                // .split('/'): URL을 / 기준으로 분할합니다.
                // .pop(): 분할된 URL에서 마지막 요소를 가져옵니다. 이것은 파일의 이름이 될 것입니다.
                console.log(deletedImageUrl)

                data = new FormData()
                data.append('file', deletedImageUrl)
              
                console.log('aaaaa', data)

            // summernote에서 이미지 삭제시 실행할 함수 
            templateAjaxController.deleteFile2(data, fileDele);
          }
        },
      });
}

   ````
## onImageUpload 함수가 실행되면 data에 파일정보를 담아 ajax를 통해 컨트롤러로 보내준다.
````
sendFile = (file, editor) => {
  data = new FormData();
  data.append("file", file);
  console.log(data)

  templateAjaxController.insertTemplateImg(data, editor);
}
````
## 성공시 파일 위치와 이름을 통해 에디터에 이미지를 출력하여 보여준다 
````
insertTemplateImg : (data, editor) =>{
        console.log(data)
        $.ajax({
            data : data,
            type : "POST",
            url : "insertTemplateImg.te",  
            contentType : false,
            processData : false,
            enctype : 'multipart/form-data',   
            success: function (data) { // 처리가 성공할 경우
                console.log(data)
                // 에디터에 이미지 출력
                $(editor).summernote('editor.insertImage', data);
            },
            error: (err) => {
                console.log(err)
            }
        })
    },
````
## 컨트롤러에서 파일 위치와 이름만 지정해서 다시 리턴하여 보내준다
````
//  서머노트 작성시 이미지파일 올렸을때 내 실제 경로 폴더에도 올려주는 메서드
@ResponseBody
@RequestMapping(value="/insertTemplateImg.te", produces="application/json; charset=UTF-8")
// @RequestParam은 자바스크립트에서 설정한 이름과 반드시 같아야한다!
public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session )  {
	
	System.out.println(multipartFile);

	String cName = saveFile(multipartFile, session, "resources/img/templateImgFile/insertTemplate/");
	String changeName = "resources/img/templateImgFile/insertTemplate/" + cName;
	
	return new Gson().toJson(changeName);
}
````
## onMediaDelete 삭제함수 실행시 target을 통해 파일 위치와 이름을 알아내는 부분이다.
````
onMediaDelete : function ($target) {
            var deletedImageUrl = $target

                .attr('src') // $target.attr('src'): 삭제된 미디어 요소의 src 속성을 통해 삭제된 이미지의 URL을 가져옵니다.
                .split('/')  // .split('/'): URL을 / 기준으로 분할합니다.
                .pop()       // .pop(): 분할된 URL에서 마지막 요소를 가져옵니다. 이것은 파일의 이름이 될 것입니다.

                console.log(deletedImageUrl)
                data = new FormData()
                data.append('file', deletedImageUrl)

            // summernote에서 이미지 삭제시 실행할 함수 
            templateAjaxController.deleteFile2(data, fileDele);
          }
````
## ajax를 이용해 컨틀롤러를 보내줘서 파일 위치와 이름을 통해 삭제를 해주면 끝 참 쉽죠 ~?
````
deleteFile2 : (data, callback) =>{
        console.log(data)
        $.ajax({
            data : data,
            type : "POST",
            url : "deleteTemplateImage.te",  
            contentType : false,
            processData : false,
            enctype : 'multipart/form-data',   
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    },


// 서머노트 작성시 이미지파일 삭제했을때 내 실제 경로 폴더에도 삭제하는 메서드 
@ResponseBody
@RequestMapping(value="/deleteTemplateImage.te", produces = "application/json; charset=utf8")
public String deleteSummernoteImageFile(@RequestParam("file") String file, HttpSession session )  {

	new File(session.getServletContext().getRealPath("resources/img/templateImgFile/insertTemplate/"+file)).delete();
	
	return "good";
}
````



</details>



 


