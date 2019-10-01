package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Department;

@Mapper
public interface DepartmentMapper {
    List<Department> findAll();
    List<Department> findAllWithStudents();
    void update(Department department);
}

