package com.gmail.goyter012.campus.service;

import com.gmail.goyter012.campus.model.Student;
import com.gmail.goyter012.campus.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;


    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public List<Student> allStudents() {
        return (List<Student>) studentRepo.findAll();
    }

    public Student saveStudent(Student student) {
        studentRepo.save(student);
        return student;
    }

    public void delete(Student student) {
        studentRepo.delete(student);
    }

    public Student getById(Long id) {
        return studentRepo.findById(id).get();
    }

}