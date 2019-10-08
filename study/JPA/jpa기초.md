# jpa 기초   
### 1. 배경지식   
#### (1) Domain Model   
데이터베이스를 설계할 때, Entity Relationship 다이어그램으로 데이터베이스의 저장 정보를 설계한다.   
이런 설계의 결과물을 모델이라고 부른다.   
즉, 데이터베이스 설계 작업에서 작성된 ER 다이어그램도 모델이다.   
도메인 모델은 ER 다이어그램과 매우 유사하다. 그러나 보통 도메인 모델은 UML 다이어그램으로도 작성된다.   
Domain - 업무, 분야, 영역이라고 이해해도 된다. 예) 대학교 학사 업무 영역(학사 정보 시스템의 도메인)  
대학교 학사 정보 시스템에는 과목, 강좌, 학기, 수강, 학점, 교수, 재수강 등의 개념이 포함된다.   

대학교 학사 정보 시스템을 개발하려면 먼저 학사 업무를 이해해야 한다.   
그러기 위해, 포함된 개념을 정리하고 사이 관계를 정리해야 한다.   
보통 UML 다이어그램을 그리면서 정리하고 이 모델을 학사 업무에 대한 도메인 모델이라고 한다.   
이것으로부터 데이터베이스 스키마가 만들어진다.   
그리고 도메인 모델의 객체와 그들간의 관계가 그대로 JPA Entity 클래스로 구현된다.   
JPA Entity 클래스에서 Entity의 의미는 ER 다이어그램의 Entity와도 같은 의미이다.   

Domain model - Conceptual model - Business model  **>>**  유사한 개념  


#### (2) JPA Repository 기본 메소드   
```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}
```  
위와 같은 형태로 인터페이스를 선언하면, 이 인터페이스를 구현한 클래스를 spring JPA가 자동으로 구현해준다.   
이렇게 자동으로 구현된 클래스에는 다음과 같은 기본 메소드가 포함된다.  

<details>  
<summary>기본 메소드</summary>  
<div markdown="1">    
  
- employeeRepository.findAll()           
  employee 테이블에서 레코드 전체 목록을 조회한다.    
  List<Employee> 객체가 리턴된다.     

- employeeRepository.findById(id)     
  employee 테이블에서 기본키 필드 값이 id인 레코드를 조회한다.    
  Optional<Employee> 타입의 객체가 리턴된다. 이 객체의 get 메소드를 호출하면 Employee 객체가 리턴된다.    
  예: Employee employee = employeeRepository.findById(id).get();     
  (Optional 이란, “존재할 수도 있지만 안 할 수도 있는 객체”, 즉, “null이 될 수도 있는 객체”을 감싸고 있는 일종의 래퍼 클래스입니다.    
  원소가 없거나 최대 하나 밖에 없는 Collection이나 Stream으로 생각하셔도 좋습니다.)    
  
- employeeRepository.save(employee)   
  Employee 객체를 employee 테이블에 저장한다.    
  Employee 객체의 id(기본키) 속성값이 0 이면 INSERT 되고, 0 이 아니면 UPDATE 된다.   

- employeeRepository.saveAll(employeeList)  
  Employee 객체 목록을 employee 테이블에 저장한다.  

- employeeRepository.delete(employee)   
  Employee 객체의 id(기본키) 값과 일치하는 레코드가 삭제된다.    

- employeeRepository.deleteAll(employeeList)   
  Employee 객체 목록을 테이블에서 삭제한다.    

- employeeRepository.count()   
  employee 테이블의 전체 레코드 수를 리턴한다.   

- employeeRepository.exists(id)   
  employee 테이블에서 id에 해당하는 레코드가 있는지 true/false를 리턴한다.    

- employeeRepository.flush()      
  지금까지 employee 테이블에 대한 데이터 변경 작업들이 디스크에 모두 기록되도록 한다.
(mybatis 캐시는 디폴트로 꺼져있지만, JPA는 캐시(혹은 버퍼)가 디폴트로 켜져있다. 저장안된 것을 저장하라고 하는 메소드이다.)     
(캐시와 버퍼는 성능 향상을 위한 메모리지만 매커니즘이 조금 다르다.    
캐시는 똑같은 데이터를 반복 조회하지 않으려는 것이고 버퍼는 디스크에 기록할 때 성능이 좋으려면 블록으로 기록해야한다.     
즉, 쓰기와 읽기 !)   
    
위 메소드들은 Spring JPA가 자동으로 구현해준 메소드이다.   

</details> 
</div>

#### (3) REST API    
안드로이드 앱을 위한 서버를 개발하거나,     
웹브라우저에서 실행되는 Javascript로 개발한 앱을 위한 서버를 개발할 때,     
REST API 서비스를 제공하는 서버를 구현한다.     

REST API 서비스라는 것은,    
클라이언트의 URL 요청에 대해서 JSON 헝태의 데이터를 출력하는 서버의 메소드를 말한다.        

#### (4) JSON(Javascript Object Notation)   
서버와 클라이언트 사이에 데이터를 주고 받을 때 흔히 사용되는 포멧이다.    
Javascript 언어의 객체나 배열의 문법과 동일하다.   

#### (5) RestController    
Spinrg MVC 프레임웍에서 REST API 서비스를 구현할 때,    
컨트롤러에 @RestController 어노테이션을 붙인다.    

RestController의 액션 메소드가 리턴하는 Java 객체는 자동으로 JSON 포멧으로 변환되어 클라이언트에 전송된다.   
RestController의 액션 메소드는, 데이터를 클라이언트에 전송하기 때문에, 뷰(view)가 필요 없다.   

#### (6) @ResponseBody   
액션메소드 앞에 @ResponseBody 어노테이션을 붙이면, 이 액션메소드는 자동으로 Java 객체를 JSON 형식으로 변환하여 클라이언트에 전달한다.   
그러나 컨트롤러에 @RestController 어노테이션을 붙였다면, 생략해도 된다.    

#### (7) @RequestBody   
액션 메소드의 파라미터 변수 앞에 어노테이션을 붙여주면 JSON 형식으로 데이터를 받을 수 있다.   

#### (8) Request Method    
서버에 데이터를 요청할 때 **GET**     
서버에 저장할 새 데이터를 전송할 때 **POST**     
서버에 기존 데이터를 수정하기 위해 전송할 때 **PUT**    
서버의 데이터 삭제를 요청할 떄 **DELETE**    

#### (9) REST API URL 패턴     
- query string 사용하지 않기    
REST API 서비스의 URL에 query string을 사용하지 않는 것이 관례이다.   <br/><br/>
예를 들어 아래 URL은 바람직하지 않다.  
http://localhost:8080/studentServer/api/student?id=3  <br/><br/>
아래와 같은 형태이어야 한다.  
http://localhost:8080/studentServer/api/student/3    
Request Method = GET   

- 동사 사용하지 않기    
REST API 서비스의 URL에 동사를 사용하지 않는 것이 관례이다.    <br/><br/>
예를 들어 아래 URL은 바람직하지 않다.  
http://localhost:8080/studentServer/studentDelete?id=3  <br/><br/>
아래와 같은 형태이어야 한다.  
http://localhost:8080/studentServer/api/student/3  
Request Method = DELETE  


#### (10) @PathVriable    
요청된 URL이 다음과 같다면,    
api/student/3    

URL에 들어있는 id값 3을 받기 위한 액션 메소드는 다음과 같이 구현한다.     
```java
@RequestMapping(value="api/student/{id}")
public Student student(@PathVriable("id") int id){...}
```
 
#### (11) 액션 메소드 URL     
컨트롤러 클래스에도 @RequestMapping("URL1") 어노테이션 붙어있고  
액센 메소드에도 @RequestMapping("URL2") 어노테이션이 붙어있다면,  
그 액션 메소드를 호출하기 위한 URL은 "URL1/URL2" 이다.  

**Example Code**    
```java
@Controller
@RequestMapping("studnet")
public class StudentController {

   @RequestMapping("list")
   public String list(...) { 
     ...
   }
}
```

list 액션 메소드를 호출하기 위한 URL은 "studnt/list" 이다.  

