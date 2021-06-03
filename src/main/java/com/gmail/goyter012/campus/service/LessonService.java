package com.gmail.goyter012.campus.service;

import com.gmail.goyter012.campus.model.Group;
import com.gmail.goyter012.campus.model.Lesson;
import com.gmail.goyter012.campus.repo.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    public final LessonRepo lessonRepo;

    @Autowired
    public LessonService(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    public List<Lesson> getAllLessons() {
        return lessonRepo.findAll();
    }

    public List<Lesson> getLessonsByGroup(Group group) {
        return lessonRepo.findAllByLessonGroup(group);
    }

    public List<Lesson> filterLessonsByOrder(List<Lesson> lessons, Integer lessonOrder) {
        return lessons.stream()
                .filter(lesson -> lesson.getLessonOrder().equals(lessonOrder))
                .collect(Collectors.toList());
    }

}
