package net.skhu.domain;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    @ManyToOne(fetch = FetchType.EAGER) // 거의 항상 가져오니까 EAGER
    @JoinColumn(name = "departmentId")
    Department department;

    @OneToOne(mappedBy="employee", fetch = FetchType.EAGER) // 주소는 가끔 가져오니까 LAZY로 적어주자.
            Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
