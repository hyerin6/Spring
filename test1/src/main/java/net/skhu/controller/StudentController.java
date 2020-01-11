package net.skhu.controller;

import net.skhu.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// test
@Controller
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

}
