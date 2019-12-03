# Spring Form Validation 1          

### 1. 배경지식   
1) spring form validation       
입력폼에 입력된 내용의 오류를 spring이 자동으로 검사해주는 기능이다.      

입력폼 submit 과정 #1       
spring web mvc로 구현한 입력폼의 submit 과정은 다음과 같다.     

⓵ 사용자가 웹 브라우저에서 입력폼에 데이터를 입력하고 submit 버튼을 누른다.  
⓶ 서버의 url이 요청된다. (http request)      
입력폼에 입력된 데이터도 이 요청에 같이 담겨 전송된다. (request parameter)
⓷ spring web mvc 엔진이 그 요청을 받아서 요청된 url과 일치하는 액션메소드를 찾는다.    

```
@RequestMapping(value="studentEdit", method=RequestMethod.POST)
public String studentEdit(@valid Student student, Model model,
						BingingResult bindingResult){ ... }
```

spring form validation 기능을 구현하기 위해,   
@vali 어노테이션과 BindingResult 객체가 추가되었다.     
검사결과를 bindingResult에 채워진 후에 액션 메소드가 호출된다.      

⓸ 위 액션메소드의 파라미터가 객체이기 때문에 spring web mvc 엔진이 아래의 일들을 자동으로 처리한다.     

```
Student student = new Student(); // Student 객체 생성
student.setStudentNumber("201732017"); // 생성된 객체에 request parameter 데이터를 채운다.  
model.addAttribute("student", student); // 객체를 model 객체에 등록한다.   
```
















































