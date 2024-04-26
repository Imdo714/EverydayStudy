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
  <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Eyes.png" alt="Eyes" width="2%" /> Spring 이슈
</summary>
   <br>
  Spring Legacy Project 에서 Spring MVC Project 를 생성하려고 했으나, 구글링을 통해 관련된 해결책을 모두 시도해 보았음에도 불구하고 Templates에서 'Spring MVC Project'를 찾을 수 없었다. 
  DynamicWeb Project 를 이용하여 수동으로 MVC를 생성하여 세팅하였다. 
  <br>
  💪 MVC 생성 과정을 공부하게 되어 자동 생성이 얼마나 좋았는지 뼈저리게 느낀다. 💪
</details>

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
  <br>
##컨트롤러
  <br>
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




<hr>

🌐내가 사용하는 언어 비율이야🌐 
<br>

![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=imdo714&layout=compact&theme=tokyonight)

<br>
깃허브에 대한 평판...🤧🤧
<br>

![Anurag's github stats](https://github-readme-stats.vercel.app/api?username=imdo714&show_icons=true&theme=tokyonight)





 


