package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Department;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DepartmentMapper {
    @Select("SELECT *, NOW() time FROM Department")
    List<Department> findAll();

    @Update("UPDATE Department " +
            "SET departmentName = #{departmentName} " +
            "WHERE id = #{id}")
    void update(Department department);
}

