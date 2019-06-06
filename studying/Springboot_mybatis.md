# Spring boot and mybatis  
Spring boot + mybatis 기술을 사용하여 DB 조회, 수정, 삽입, 삭제 기능을 구현한다.  

## 1. 배경지식  
<details markdown="1">
<summary>접기/펼치기</summary>
   
### (1) ORM (Object Relational Mapping)   
ORM 에서 Object 는 객체지향 언어의 객체를 의미한다.  
Ralational 은 관계형 데이터베이스(Relational Database)의 데이터를 의미한다.  
Mapping이 의미하는 것은 객체지향 언어의 객체와 관계형 데이터를 서로 변환해 준다는 것이다.  

**ORM 이란?**  
   관계형 데이터베이스에서 조회한 데이터를 Java 객체로 변환하여 리턴해 주고,   
   Java 객체를 관계형 데이터베이스에 저장해 주는    
   라이브러리 혹은 기술을 말한다.    

Java ORM 기술로 유명한 것은  
mybatis, Hibernate, JPA 이다.  

mybatis와 Hibernate는 오픈소스 프로젝트이고 jar 라이브러리 형태로 제공된다.  

JPA(Java Persistence API)는 제품의 이름이 아니고, API 표준의 이름이다.  
JPA 표준 규격대로 만들어진 제품 중에서 유명한 것이 Hibernate 오픈소스 라이브러리이다.  
우리가 사용하는 Spring JPA에 Hibernate 라이브러리가 포함되어 있다.  

우리 나라의 전자 정부 표준 프레임웍에서 Spring mybatis를 채택하고 있기 때문에,    
우리 나라 공공 프로젝트에서 mybatis를 사용하는 경우가 많다. 그렇지만 JPA가 좀 더 미래지향적인 기술이기 때문에 점점 JPA를 사용하는 경우가 늘어나고 있다.   

### (2) JPA와 mybatis 비교   
MySQL, Oracle, SQL Server 등 DBMS  제품 마다 SQL 문법은 조금씩 다르다.
그래서 DBMS 제품을 교체하려면, SQL 문장도 수정해야 한다.

- JPA의 장점  
SQL 명령을 구현할 필요가 없다. 그래서 DBMS 제품을 교체하더라도 소스코드를 수정할 필요가 없다.  
자동으로 처리되는 부분이 많아서, 구현할 소스코드의 양이 상대적으로 적다.  
관계형 데이터베이스가 아니더라도 적용할 수 있다.  

- JPA의 단점  
복잡한 조회 명령을 구현해야 할 때, 익숙한 SQL 명령으로 구현할 수가 없고, JPA의 고급 기능을 공부해야 한다.  

- mybatis의 장점  
익숙한 SQL 명령으로 구현할 수 있다.  
SQL 문장을 그대로 사용하여 구현하기 때문에, SQL 문장에 익숙한 개발자에게 myBatis가 편하다.</br>  
DB 조회 결과를 복잡한 객체 구조로 변환해야 할 때 myBatis 기능이 좋다.  
mybatis의 resultMap 기능이 바로 그것이다.  
이 기능은 복잡한 보고서(report)를 출력해야 할 때, 특히 유용하다.</br>  
데이터베이스 성능 개선을 위해, 어떤 인덱스를 생성해야 하는지 파악하기 위해,   
SQL 쿼리들을 분석해야 하는데, 이때 myBatis는 SQL 문장을 그대로 사용하기 때문에,   
SQL 쿼리 분석하기 편하다.  
 
- mybatis의 단점  
구현할 소스코드의 양이 상대적으로 많다.  
관계형 데이터베이스에만 적용할 수 있다.</br>  
DBMS 제품을 교체하면 SQL 소스코드를 수정해야 한다.  
Oracle, MS SQL Server, mySQL 등 DBMS 마다 SQL 문법이 약간씩 차이가 있다.  
그래서 DBMS를 바꾸면 SQL 문도 수정해야 하는 불편함이 있다.  
SQL 문을 사용하지 않는 Hibernate, JPA에는 이런 문제가 없다.  

### (3) mybatis mapper   
데이터베이스는 테이블에 대한 SELECT / INSERT / UPDATE / DELETE SQL 명령들을 mybatis mapper에 구현한다.  
보통 데이터베이스 테이블 한 개당 mybatis mapper 한 개를 구현한다.  

mybatis mapper는 Java Interface 파일 한 개와, XML 파일 한 개로 구현된다.   

DB 테이블에 대한 조회, 삽입, 수정, 삭제 SQL 명령을 mapper XML 파일에 구현한다.   
그리고 이 명령을 호출하기 위한 Java 메소드를 mapper Java Interface 파일에 선언한다.  

mapper 메소드를 호출하기 위한 Java 메소드를 Java Interface에 선언하기만 하면 된다.    
하지만 이 메소드를 구현(implements)할 필요는 없다.     
즉, Mapper Java Interface만 만들면되고, 이 인터페이스는 mybatis spring이 자동으로 구현해주기 때문에 구현할 필요는 없다.   

### (4) Auto Increment 필드 (identity 필드)  
Student 테이블의 기본키(primary key)는 id 필드이다.   
MySQL에서 Student 테이블을 생성할 때, id 필드를 Auto Increment 필드로 지정하였다.  

Auto Increment 필드의 값은 1부터 시작하는 일련번호이다.  
테이블에 새 레코드를 insert 할 때, 이 필드의 값에 일련번호가 자동으로 부여된다.   

Auto Increment 필드의 값인 자동으로 부여되기 때문에,  
insert나 update SQL 문에서 이 필드의 값을 저장하는 것이 에러이다.  

### (5) Referential Integrity Constraint 참조 무결성 제약   
Student 테이블의 departmentId 필드는 외래키(foreign key) 이다.  
이 필드의 값은 department 테이블의 기본키인 id 필드값과 일치해야 한다.  
Student 테이블과 Department 테이블을 조인할 때, departmentId 필드를 사용한다.  

```
SELECT s.*, d.departmentName
FROM Student s LEFT JOIN department d ON s.departmentId = d.id
```
Department 테이블에서 레코드를 한 개 삭제 하려고 할 때,   
만약 Student 테이블의 어떤 레코드의 departmentId 필드 값이,   
그 삭제하려는 Department 레코드의 id 필드 값과 일치한다면,  
삭제는 실패하고 에러가 발생한다.  
이 에러를 참조 무결성 제약조건 위반(referential intergity constraint violation)이라고 부른다.  
쉽게 표현하자면, 국어국문학과 소속 학생들이 존재한다면, 국어국문학과를 삭제할 수 없다는 얘기다.   

Register 테이블에 외래키인 studentId 필드가 들어있다.  
그래서 Student 테이블의 레코드를 삭제하려 할 때, 참조 무결성 제약조건 위반 에러가 발생할 수 있다.  
쉽게 표현하자면, 201132050 학생의 수강신청 내역이 존재한다면, 그 학생을 삭제할 수 없다는 얘기다.  

데이터베이스가 참조 무결성 제약조건을 실시간 검사해 준다.  
테이블을 생성할 때, 데이터베이스가 참조 무결성 제약조건을 설정해 주는 것이 바람직하다.  

참조 무결성을 제약조건을 외래키 제약조건이라고도 부른다.  

Student 테이블에 FK_Student_Department 이름의 외래키 제약조건이 이미 설정되어 있다.  
이 외래키 제약조건을 삭제하는 명령은 다음과 같다.  
```
ALTER TABLE Student
DROP FOREIGN KEY FK_Student_Department;
```

**외래키 제약조건 생성하기**    
Student 테이블의 departmentId 필드와 Department 테이블의 id 필드 사이에  
외래키 제약조건을 생성하는 명령은 다음과 같다.  
```
ALTER TABLE Student
ADD CONSTRAINT FK_Student_Department
FOREIGN KEY (departmentId) REFERENCES Department(id);
```
제약조건의 이름은 FK_Student_Department 이다.  
외래키는 Student 테이블의 departmentId 필드이다.  
이 필드는 Department 테이블의 id 필드를 참조(references)한다.  

### (6) 참조 무결성 제약조건 위반 피하기  
- 먼저 삭제하기  
Department 테이블의 레코드를 삭제하기 전에, 먼저 그 레코드를 참조하는 Student 레코드들을 전부 삭제한다.  
예) 소프트웨어공학과를 없애려면 학생테이블의 소프트웨어공학과 학생들을 먼저 지우고 지워야 한다.   
```
DELETE FROM Student WHERE departmentId = 2;
DELETE FROM Department WHERE id = 2;
```

- Cascade Delete 옵션 
외래키 제약 조건을 생성할 때, Casecade Delete 옵션을 지정할 수 있다.  
이 옵션이 지정된 경우에는, Department 테이블의 레코드를 삭제할 때,  
그 레코드를 참조하는 Student 레코드들이 전부 자동으로 삭제된다.  
(자동으로 지워지도록 미리 설정 / 장점 : 편하다. / 단점 : 실수로 레코드 전부를 지울 수 있다.)  
```
ALTER TABLE Student
ADD CONSTRAINT FK_Student_Department
FOREIGN KEY (departmentId) REFERENCES Department(id)
ON DELETE CASCADE;
```
</details>


## 2. mybatis1 프로젝트    

<details markdown="1">
<summary>접기/펼치기</summary> 

### (1) 프로젝트 생성    

### (2) pom.xml - Maven 설정 파일   
pom.xml 파일의 <dependency> 태그들에 있는 항목들을 maven dependency라고 부른다.    
필요한 라이브러리나 빌드 방법을 설정하는 폴더이다.   
Maven : 프로젝트 관리 도구 (라이브러리 설치, 빌드)       

파일 하나의 결과 >> 디버깅   
프로젝트 전체 결과 >> 빌드   

### (3) application.properties - Spring boot 설정 파일   

`spring.mvc.view.prefix=/WEB-INF/views/`  
뷰 파일이 위치할 폴더를 지정한다.    

`spring.mvc.view.suffix=.jsp`  
뷰 파일의 확장자를 지정한다.  

`spring.datasource.driver-class-name=com.mysql.jdbc.Driver`  
JDBC 드라이버 클래스의 이름을 지정, Mysql JDBC 드라이버 클래스 

`spring.datasource.url=jdbc:mysql://localhost:3306/student1?useUnicode=yes&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC`  
DB 서버 IP와 DB 이름 설정, 서버 타임존 설정   

`spring.datasource.username=user1` DB 연결 계정 설정    

`spring.datasource.password=test123` DB 연결 계정 설정     
  
`mybatis.type-aliases-package=net.skhu.dto`  
여기서 net.skhu.dto 는 DB 조회 결과 데이터를 담은 클래스의 패키지를 지정한다.  
mybatis mapper XML 파일에서 select 태그의 resultType으로 등록된 클래스들의 패키지를 지정한다.    
예를들어, `<select id="findById" resultType="Student">`    
select 태그의 resultType으로 등록된 Student 클래스의 패키지는 net.skhu.dto 이어야 한다.   




</details>
























