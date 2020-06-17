package com.gmail.goyter012.campus.controller;

import com.gmail.goyter012.campus.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String start() {
        return "index";
    }


    @GetMapping("/main")
    public String studentsList(Model model) {
        model.addAttribute("students", studentService.allStudents());
        return "stud/students";
    }


}
