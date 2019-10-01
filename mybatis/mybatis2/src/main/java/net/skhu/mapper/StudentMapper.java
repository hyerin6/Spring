package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import net.skhu.dto.Student;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM Student WHERE id = #{id}")
    Student findOne(int id);

    @Select("SELECT * FROM Student WHERE studentNumber = #{studentNumber}")
    Student findByStudentNumber(String studentNumber);

    @Select("SELECT * FROM Student WHERE departmentId = #{departmentId}")
    List<Student> findByDepartmentId(int departmentId);

    @Select("SELECT s.*, d.departmentName, NOW() time " +
            "FROM Student s LEFT JOIN department d ON s.departmentId = d.id")
    List<Student> findAll();

    @Insert("INSERT Student (studentNumber, name, departmentId, year) " +
            "VALUES (#{studentNumber}, #{name}, #{departmentId}, #{year})")
    void insert(Student student);

    @Update("UPDATE Student SET " +
            "studentNumber = #{studentNumber}, " +
            "name = #{name}, " +
            "departmentId = #{departmentId}, " +
            "year = #{year} " +
            "WHERE id = #{id}")
    void update(Student student);

    @Delete("DELETE FROM Student WHERE id = #{id}")
    void delete(int id);
}
