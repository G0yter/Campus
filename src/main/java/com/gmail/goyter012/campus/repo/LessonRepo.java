package com.gmail.goyter012.campus.repo;

import com.gmail.goyter012.campus.model.Group;
import com.gmail.goyter012.campus.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByLessonGroup(Group group);
}
