package com.gmail.goyter012.campus.repo;

import com.gmail.goyter012.campus.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {

}