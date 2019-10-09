# JPA relationship mapping 2   

## 1. 배경지식   
### (1) Java 객체 속성      
Java 객체의 속성을 getter, setter 메소드로 구현된다.     
**Example Code 1**     
```java
public class Student {
   String name;

   public String getName() { return this.name; }
   public void setName(String name) { this.name = name; }
}
```
Student 클래스에 name 속성이 구현되었다.        
속성 이름이 name 인 이유는, name 멤버 변수 때문이 아니고, getName, setName 메소드 이름 때문이다.       
즉 get, set 메소드가 그 클래스의 속성에 해당한다.        


**Example Code 2**         
id 속성을 정의하자.           
```java
public class Student {
    int i;

    public int getId() { return this.i; }
    void setId(int id) { this.i = id; }
}
```
Student 클래스에 id 속성을 만들기 위해서, getId, setId 메소드를 구현하였다.            
이런 메소드들을 getter, setter 라고 부른다.          

JSP 파일에서      
```jsp
${ student.name }
```
위의 소스 코드는 student 객체의 name 속성 값을 출력한다.          
즉 getName 메소드의 리턴 값을 출력한다.         


### (2) 다 대 1 관계   
<img width="522" alt="스크린샷 2019-10-09 오후 2 47 49" src="https://user-images.githubusercontent.com/33855307/66454800-df64ba00-eaa3-11e9-86a2-25059a087d9e.png">  

ER 다이어그램에서 다 대 1 관계는 위 그림의 선으로 표현한다.       
선의 끝 부분의 형태에 주목하자.        
선의 왼쪽 끝 부분은 다 를 나타내고, 오른쪽 끝 부분은 1 을 나타낸다.       
위 ER 다이어그램을 DB 테이블로 구현할 때,        
다 에 해당하는 왼쪽 테이블에 외래키가 포함되어야 한다. (register 테이블의 studentId 필드)       
이 외래키는 1 에 해당하는 테이블의 기본키를 가르킨다. (student 테이블의 id 필드)      

위 관계는 JPA 엔티티 클래스에서 다음과 같이 구현된다.    

**'다' 에 해당하는 테이블**  
```java
public class Registration {

    @ManyToOne
    @JoinColumn(name = "studentId")
    Student student;

}
```

**'1' 에 해당하는 테이블**  
```java
public class Student {

    @JsonIgnore
    @OneToMany(mappedBy="student", fetch = FetchType.LAZY) // FetchType.LAZY은 디폴트지지만 적어줌
    List<Registration> registrations;

}
```

### (3) equals 메소드, hashCode 메소드 재정의   
ArrayList, HashMap 등의 컬렉션 객체에, 어떤 클래스 객체를 보관하려면,       
그 클래스에 equals 메소드와 hashCode 메소드를 재정의(override) 해주어야 한다.           
(Collection에 객체를 보관해야겠다. 라고 생각했으면 이 두개를 재정의 해야 한다고 생각하면 된다.)            

equals 메소드를 재정의 하지 않으면, Object 클래스에 정의된 equals 메소드가 상속된다.        
Object 클래스의 equals 메소드는 equality를 비교하지 않고 identity를 비교한다.      
그렇기 때문에 내용이 동일한 두 객체를 비교해도 false가 된다.     
(Java 프로젝트 강의노트 참고)    

HashMap 같은 해시 테이블 자료구조를 활용하여 구현된 컬렉션 객체에     
어떤 클래스 객체를 보관할 때, 그 클래스의 hashCode 메소드가 호출된다.    

hashCode 메소드를 재정의 하지 않으면, Object 클래스에 정의된 equals 메소드가 상속된다.    
Object 클래스의 hashCode 메소드는 객체의 내용을 사용하여 해시 값을 계산하지 않고,    
객체의 identity 정보를 사용하여 해시 값을 계산한다.    
그렇기 때문에 내용이 동일한 두 객체의 해시 값이 동일하지 않다.    
        
내용이 동일한 두 객체의 해시 값은 같아야 한다.    
그렇게 hashCode 메소드를 재정의해야 한다.    

따라서 어떤 클래스 객체를 HashMap에 보관할 수 있으려면    
그 클래스에 hashCode 메소드를 재정의 해야 한다.    

### (4) lombok    
lombok 은 getter, setter 메소드를 자동으로 생성해 주는 도구이다.     
lombok 을 사용하면, getter, setter 메소드를 구현하지 않아도 되니 편하다.    
equals 메소드, hashCode 메소드, toString 메소드도 자동으로 생성해 준다.    
클래스에 @Data 어노테이션을 붙여주면 된다.    

equals 메소드를 구현할 때,    
객체의 내용에 해당하는 멤버 변수들의 값을 모두 비교해야 한다.    

hashCode 메소드를 구현할 때,    
객체의 내용에 해당하는 멤버 변수들의 값을 사용하여 해시 값을 계산해야 한다.    

**@Data 어노테이션**    
이 어노테이션을 클래스 앞에 붙이면,    
getter, setter, equals, hashCode, toString 메소드가 자동 구현된다.

**Example Code**  
```java
@Data 
public class Student {
   int id;
   String name;
}
```


equals, hashCode 메소드를 구현할 때    
객체의 내용에 해당하지 않는 멤버 변수의 값은 제외해야 한다.      

**Example Code**  
```java
@Data 
@EqualsAndHashCode(exclude="temp")
@ToString(exclude="temp")
public class Student {
   int id;
   String name;
   int temp;
}
```
equals, hashCode, toString 메소드가 생성될 때, temp 멤버 변수는 무시된다.   


**Example Code**   
```java
@Data 
@EqualsAndHashCode(exclude={"temp1","temp2"})
@ToString(exclude="temp")
public class Student {
   int id;
   String name;
   int temp1;
   String temp2;
}
```
equals, hashCode, toString 메소드가 생성될 때, temp1, temp2 멤버 변수는 무시된다.             
 
엔터티 클래스들 사이의 관계를 구현한 멤버 변수는          
equals, hashCode, toString 메소드를 구현할 때 제외해야 한다.          
          
예를 들어 Student 클래스의 id, name, departmentId 멤버 변수는 Student 객체의 내용에 해당한다.          
그런데 Student 테이블과 Department 테이블의 관계를 구현하면서          
추가된 department 멤버 변수는 Student 객체의 내용이 아니다.          
이 멤버 변수는 equals, hashCode, toString 메소드를 구현할 때 제외해야 한다.          


## 3. jpa11 프로젝트 생성   
### (1) 목표        

<img width="392" alt="스크린샷 2019-10-09 오후 3 09 27" src="https://user-images.githubusercontent.com/33855307/66455828-d3c6c280-eaa6-11e9-8263-50b52a7f054a.png">

도메인 모델은 구현해보자.     

### (2) 프로젝트 생성    

## 4. 엔티티 클래스 구현    
### (1) Department.java    





























