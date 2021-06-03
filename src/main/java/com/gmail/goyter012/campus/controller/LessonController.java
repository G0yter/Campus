package com.gmail.goyter012.campus.controller;

import com.gmail.goyter012.campus.model.Group;
import com.gmail.goyter012.campus.model.Lesson;
import com.gmail.goyter012.campus.service.FacultyService;
import com.gmail.goyter012.campus.service.GroupService;
import com.gmail.goyter012.campus.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LessonController {


    private LessonService lessonService;
    private GroupService groupService;

    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/lessons/{id}")
    public String lessons(@PathVariable Long id, Model model) {
        List<Lesson> groupLessons = lessonService.getLessonsByGroup(groupService.getGroupById(id));
        model.addAttribute("lessons", groupLessons);
        return "lessonList";
    }
}
