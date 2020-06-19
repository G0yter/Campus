package com.gmail.goyter012.campus.repo;

import com.gmail.goyter012.campus.model.Faculty;
import com.gmail.goyter012.campus.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {

    List<Student> findAllByFacultyEquals(Faculty faculty);

    List<Student> findAllByCardNumber(Integer cardNumber);

    List<Student> findAllByFullName(String fullName);
}
