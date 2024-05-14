## EverydayStudy
src == 프론트  <br>
chatJAVA == 자바 

## 프로젝트 개요
- 🌱 이제 매일 조금씩 공부한거 기록도 할겸 매일 1일 1PUSH를 하기로 했어 👀👀
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
 model에 대한 패키지명을 추가해주어야 한다 추가하지 않으면 못 찾는다 <br>
 
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
  DB확인을 하면 평문이였였던 비밀번호가 암호문으로 바꿔있는 것을 확인할 수 있다. <br>
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
  matches : Member m으로 들어온 비밀번호는 암호화되어 DB에 저장된 암호화된 비밀번호와 같은지 비교를 하여 확인할 수 있습니다. <br>
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
   onMediaDelete : 이미지를 삭재하였을때 실행되는 함수 <br>
   ````
makeNote = () => {
    $('.summernote').summernote({
      placeholder: 'Hello stand alone ui',
        tabsize: 2,
        height: 500,

        callbacks:{
          onImageUpload : function(files, editor,	welEditable) { // 이미지를 첨부할때 실행되는 함수
            console.log("이미지 첨부 됨");
            console.log(files);
            for (var i = files.length - 1; i >= 0; i--) {
                console.log(files[i],this);
                sendFile(files[i],this);
            }
          },
          onMediaDelete : function ($target, editor, $editable) { // 이미지를 삭재하였을때 실행되는 함수
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
## 성공시 파일 위치와 이름을 통해 에디터에 이미지를 출력하여 보여준다. 
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
## 컨트롤러에서 파일 위치와 이름만 지정해서 다시 리턴하여 보내준다.
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
## 업데이트도 똑같아
위에 처럼 다 똑같이 하고 SQL에서 업데이트 성공시 코드들 저장 ~ <br>
````
@ResponseBody
@RequestMapping(value="/updateTemplate.te", produces="application/json; charset=UTF-8")
public String updateSummernote(Template t, ModelAndView mv)  {

	// 성공하면 success 보내주고 실패시 fail을 보내줌
	return new Gson().toJson(templateService.updateTemplate(t) > 0 ? "success" : "fail"); 
}

updateT = (result) => {
    if(result == 'success'){ // 업데이트 성공시
        var markup = $('.click2edit').summernote('code'); // 썸머노트가 코드 수정을 저장해줌
        $('.click2edit').summernote('destroy');
    } else {
        alert('템플릿 수정에 실패하였습니다.');
    }
}
````

</details>

<details>
<summary>
  Pagenation으로 리스트 활용하기 
</summary>

## PageInfo 객체 만들어주기 !
이 메서드는 페이지네이션 기능을 구현하기 위해 필요한 정보들을 계산하고 이를 담은 객체를 생성하여 반환하는 메서드입니다. <br>
````
public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		
	int maxPage = (int)Math.ceil((double)listCount / boardLimit);    // 가장 마지막페이지(총 페이지 수)
	int startPage= (currentPage - 1) / pageLimit*pageLimit + 1;  // 페이징바의 시작수
	int endPage = startPage + pageLimit - 1;    // 페이징바의 끝수
	endPage = endPage > maxPage ? maxPage : endPage;
	
	//*페이징바를 만들때 필요한 객체
	PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
	return pi;
}
````

## 컨트롤러에서 PageInfo 사용하는 방법
아래처럼 value 값을 정한 후 defaultValue 값을 무조건 1로 설정 currentPage(현재페이지)가 없을 수 없기 떄문이다.  <br>
````
// 템플릿 리스트 보여주기
@RequestMapping("/template.te")
public ModelAndView tem(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv){

	// PageInfo 객체를 통해 (총 갯수, 현재페이지, 펴이징 바, 보여줄 게시물 수)를 기입한다
	PageInfo pi = Pagenation.getPageInfo(templateService.selectListCount(), currentPage, 5, 9);

	// pi 정보를 통해 템플릿 정보들을 가가져온다
	ArrayList<Template> list = templateService.selectTemplateList(pi);
	
	mv.addObject("pi", pi)
	  .addObject("list", list)
	  .setViewName("template/template");
	
	return mv;
}
````
## DAO에서 PageInfo를 사용할때는 RowBounds 클래스를 사용한다 !
RowBounds 클래스는 MyBatis에서 사용되는 페이징을 처리하기 위한 클래스이다. <br>
예를 들어 offset 10번째부터 limit 20개의 데이터를 조회하는 식으로 돌아간다. <br>
````
// 템플릿 리스트 정보 가져오기 
public ArrayList<Template> selectTemplateList(SqlSessionTemplate sqlSession, PageInfo pi) {
	int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();  // 조회를 시작할 행의 인덱스
	int limit = pi.getBoardLimit();  // 조회할 행의 최대 개수
	
	RowBounds rowBounds = new RowBounds(offset, limit);

	// selectList 메서드가 반환하는 값이 실제로는 List 타입이기 때문에 다운 캐스팅(형변환을 함)
	return (ArrayList)sqlSession.selectList("TemplateMapper.selectTemplateList", null, rowBounds);
}
````
</details>

<details>
<summary>
  Ajax 댓글 리스트, 작성, 삭제, 기능 꿀팁 !
</summary>
	
# Ajax란?	
자바스크립트를 통해 비동기식으로 서버에 데이터를 요청하여 필요한 데이터를 받아와 전체 페이지를 새로 고치지 않고 변경이 필요한 부분만 고치는 기술이다.
	
## 1.  onload 이벤트를 통해 body 페이지 그리는 동시에 함수를 실행하는 방식을 선택!
onload란 웹 페이지가 로드될 때 발생하는 이벤트입니다. 예를 들어, 웹 페이지가 로드될 때 초기 데이터를 로드하여 화면에 표시하는 등의 작업을 onload 이벤트 핸들러에서 처리할 수 있습니다. <br>
````
<body onload="replyCommont(${tno})">

replyCommont = (tno) => { // onload 디테일뷰 들어오는 순간 댓글 페이징 바 그려주는 메서드

    data = {
        tno : tno
    }
    templateAjaxController.onloadReply(data, replySucc);
}

    onloadReply : (data, callback) =>{
        $.ajax({
            data : data,
            type : "POST",
            url : "onloadReply.te",   
            success: (result) => {
                callback(result)
            },
            error: (err) => {
                console.log(err)
            }
        })
    }
````
## 2. 컨트롤러를 통해 요청을 처리 하고 쿼리를 실행!

공통으로 수행되는 작업 코드를 줄이기 위해 ModelAndView 객체에 담아 반환하는 메서드를 만들었다. 
getReplyModelAndView메서드를 <br>만들기 전에는 페이징 처리, 댓글 정보 조회, 세션에서 로그인한 사용자의 번호 가져오기 같은 증복 코드들이 많았다 <br>
이렇게 하면 중복 코드를 최소화하고 코드를 더 간결하고 코드의 가독성이 향상되고 유지보수가 쉬워집니다. <br>
````
// --------------------------------- 공통된 작업을 수행하는 메서드 ---------------------------------
private ModelAndView getReplyModelAndView(int tno, HttpSession session, int currentPage) {
	
    PageInfo pi = Pagenation.getPageInfo(templateService.selectReplyCount(tno), currentPage, 5, 5); // 페이징 처리
    
    ArrayList<TemplateReply> ReplyList = templateService.detailReplyTemplate(pi, tno); // 댓글 정보
    
    // 삼항 연산자 세션이 없으면 0반환 있으면 회원 번호 반환
    int userNo = session.getAttribute("loginUser") == null ? 0 : ((Member) session.getAttribute("loginUser")).getUserNo();
    
    // 객체 생성 조회된 댓글 정보와 페이징 정보 그리고 사용자 번호를 ModelAndView 객체에 담아 반환
    ModelAndView mv = new ModelAndView();
    mv.addObject("ReplyList", ReplyList).addObject("tno", tno).addObject("userNo", userNo).addObject("pi", pi);
    
    return mv;
}

// --------------------------------- Ajax onload로 디테일뷰 댓글, 페이징 바 그려주는 메서드 ---------------------------------
@ResponseBody
@RequestMapping(value="/onloadReply.te", produces="application/json; charse행 코드이다. 

reply = (templateNo, tno) => { // 댓글 작성 하는 메서드
    let templateReplyContent = document.getElementById("text-commet").value;

    data = {
        templateReplyContent : templateReplyContent,
        templateNo : templateNo,
        tno : tno
    }

    templateAjaxController.replyInsert(data, replySucc);
}

delReply = (replyNo, tno) => {
    data = {
        templateReplyNo : replyNo,
        templateNo : tno
    }
    templateAjaxController.replyDel(data, replySucc);
}
````
## 댓글 작성, 삭제 컨트롤러 
 getReplyModelAndView메서드를 안 만들었으면 댓글 작성 삭제 메서드에도 증복 코드가 들어가 코드 생겼을 것이다. <br>
 
````
// --------------------------------- Ajax 댓글 작성하는 메서드 ---------------------------------
@ResponseBody
@RequestMapping(value="/repltInsert.te", produces="application/json; charset=UTF-8")
public String Reply(TemplateReply r, int tno, ModelAndView mv, HttpSession session, @RequestParam(value="cpage", defaultValue="1") int currentPage)  {
	
    Member m = (Member) session.getAttribute("loginUser");
    int res = templateService.replyInsert(r, m.getUserNo()); // 댓글 삽입
    
    // 메서드 호출 매개변수로는 댓글번호, 세션, currentPage를 전달 
    ModelAndView newMv = getReplyModelAndView(tno, session, currentPage); 
    // newMv로 리턴 받은 조회된 댓글 정보와 페이징 정보 그리고 사용자 번호를 반환
    return new Gson().toJson(newMv);
}

//  --------------------------------- Ajax 댓글 삭제해주는 메서드 -------------------------------------
@ResponseBody
@RequestMapping(value="/replyDelte.te", produces="application/json; charset=UTF-8")
public String replyDelte(TemplateReply tr, ModelAndView mv, HttpSession session, @RequestParam(value="tpage", defaultValue="1") int currentPage)  {
	
    int res = templateService.replyDelt(tr.getTemplateReplyNo()); // 댓글 삭제
    
    ModelAndView newMv = getReplyModelAndView(tr.getTemplateNo(), session, currentPage);
    return new Gson().toJson(newMv);
}
````
</details>

<details>
<summary>
  댓글 수정 꿀팁 !@!@
</summary>
	
## 댓글 수정 버튼 을 눌렀을때 실행되는 함수 
!! 중요한 부분 이전 코드에서 그려주는 함수에서 변수에 따옴표로 감싸지 않으면 함수 호출로 인식하려고 시도하며 해당 변수나 함수가 정의되지 않았기 때문에 undefined가 반환되어 따옴표로 감싸줘야 합니다. <br>
````
onclick="editReply(${r.templateReplyNo}, ${r.templateNo}, '${r.memberImgChangName}', '${r.userName}', '${r.templateReplyDate}', '${r.templateReplyContent}')">edit</button>
````

### 똑같이 밑에 코드도 보여주는 부분을 그려주고 span태그에서 textarea태그로 바꿔줘서 작성을 할 수 있게 만들어줍니다. 중요한 포인트는 내가 수정하는 댓글만 textarea태그로 바꿔줘야 하기 때문에 id가 comment-${replyNo} 인 요소만 선택 해서 그려줍니다.

````
const disableAllButtons = () => {  // 모든 버튼 비활성화 함
    const buttons = document.querySelectorAll('button');
    buttons.forEach(button => {
        button.disabled = true;
    });
}

editReply = (replyNo, tno, ImgChangName, userName, ReplyDate, ReplyContent) => { // 댓글 수정

    disableAllButtons(); // 모든 버튼 비활성화

    let str = "";
    str +=  `<div class="comment-container">`
                + `<div class="reply-container">`
                + `<div class="profile">`
                    + `<img src="`+ ImgChangName +`" alt="">`
                + `</div>`
                + `<div class="reply-center">`
                    +`<div class="name-container">`
                    +`<div class="name-container">`
                        +`<h3 style="font-size: 1.5rem;">`+ userName +`</h3>`
                        +`<p>` + ReplyDate +`</p>`
                    +`</div>`
                    +`<div class="btn-container">`       
                        +`<button class="edit-btn" onclick="editCheck(`+ replyNo + `,` + tno +`)">check</button>`
                        +`<button class="del-btn" onclick="replyCommont(`+ tno +`)">cancel</button>`
                    +`</div>`
                    +`</div>`

                    +`<div class="reply-comment">`
                    +`<textarea id="updateReply" style="width: 100%;">` + ReplyContent +`</textarea>`
                    +`</div>`
                +`</div>`
                +`</div>`
            +`</div>`;

        document.getElementById(`comment-${replyNo}`).innerHTML = str;
}
````
## 댓글 수정확인을 누르면 내가 입력한 값을 들고 와 컨트롤러를 통해 요청을 처리 하고 쿼리를 실행!
````
editCheck = (replyNo, tno) => {
    const updateReply = document.getElementById("updateReply").value;

    data = {
        templateReplyNo : replyNo,
        templateReplyContent : updateReply,
        templateNo : tno
    }
    
    templateAjaxController.upDateReply(data, replySucc);
}
````
````
	@ResponseBody
	@RequestMapping(value="/replyUpdate.te", produces="application/json; charset=UTF-8")
	public String replyUpdate(TemplateReply tr, ModelAndView mv, HttpSession session, @RequestParam(value="tpage", defaultValue="1") int currentPage)  {
		
	    int upReply = templateService.replyUpdate(tr);
	    
	    ModelAndView newMv = getReplyModelAndView(tr.getTemplateNo(), session, currentPage);
	    return new Gson().toJson(newMv);
	}
````
</details>


 


