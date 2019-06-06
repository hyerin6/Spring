# Spring boot and mybatis  
#### Spring boot + mybatis 기술을 사용하여 DB 조회, 수정, 삽입, 삭제 기능을 구현한다.  

## 1. 배경지식  
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

