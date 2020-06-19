package com.gmail.goyter012.campus.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stud_gr")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cipher_gr")
    private String cipher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private String filename;

    @OneToMany(mappedBy = "groupOfStuds", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();


}

