package com.gmail.goyter012.campus.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dormitory")
@Data
public class Dormitory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dormitory_address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "controller_full_name")
    private String commFullName;

    @Column(name = "dorm_number")
    private Integer dormNumber;

    private String filename;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();


    public void addStudent(Student student) {
        students.add(student);
        student.setDormitory(this);
    }

    public void delStudent(Student student) {
        students.remove(student);
    }


}
