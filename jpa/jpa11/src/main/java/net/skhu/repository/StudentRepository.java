package net.skhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.skhu.domain.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>  {

    List<Student> findByName(String name);
    List<Student> findByNameStartsWith(String name);
    List<Student> findByDepartmentName(String name);
    List<Student> findByDepartmentNameStartsWith(String name);

    List<Student> findAllByOrderByName();
    List<Student> findAllByOrderByNameDesc();
    List<Student> findByDepartmentIdOrderByNameDesc(int id);
}

