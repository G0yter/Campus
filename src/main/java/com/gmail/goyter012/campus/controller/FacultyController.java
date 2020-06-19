package com.gmail.goyter012.campus.controller;

import com.gmail.goyter012.campus.model.Faculty;
import com.gmail.goyter012.campus.service.FacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class FacultyController {

    private FacultyService facultyService;

    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @GetMapping("/faculties")
    public String faculties(Model model) {
        model.addAttribute("faculties", facultyService.allFaculties());
        return "faculty/faculty";
    }

    @GetMapping("/filterFaculties")
    public String filter(@RequestParam String filterFaculties, Model model) {
        model.addAttribute("faculties", facultyService.getAllFacultiesByName(filterFaculties));
        return "faculty/faculty";
    }


    @GetMapping("/delFaculty/{id}")
    public String StudDelForm(@PathVariable Long id, Model model) {
        Faculty faculty = facultyService.getFacultyById(id);
        model.addAttribute("faculty", faculty);
        return "faculty/facultyDel";
    }


    @PostMapping("/deleteFaculty")
    public String studDel(@RequestParam Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        facultyService.delete(faculty);

        return "redirect:/faculties";
    }


    @PostMapping("/faculties")
    public String addFaculty(@RequestParam String fNum,
                             @RequestParam String phone,
                             @RequestParam String dName, Model model) {
        return saveOrEdit(null, fNum, phone, dName, model, "save");
    }


    @GetMapping("/editFaculty/{id}")
    public String facEditForm(@PathVariable Long id, Model model) {
        Faculty faculty = facultyService.getFacultyById(id);
        model.addAttribute("faculty", faculty);
        return "faculty/editFaculty";
    }


    @PostMapping("/updateFaculty")
    public String editFaculty(@RequestParam Long id,
                              @RequestParam String fNum,
                              @RequestParam String phone,
                              @RequestParam String dName, Model model) {
        return saveOrEdit(id, fNum, phone, dName, model, "edit");
    }


    private String saveOrEdit(@RequestParam Long id,
                              @RequestParam String fNum,
                              @RequestParam String phone,
                              @RequestParam String dName, Model model, String choice) {

        Faculty faculty = new Faculty();

        faculty.setDecanName(dName);
        faculty.setName(fNum);
        faculty.setPhone(phone);

        if (fNum.equals("") || phone.equals("") || dName.equals("")) {
            return "errors/errorInput";
        }

        facultyService.saveFaculty(faculty);
        List<Faculty> faculties = facultyService.allFaculties();
        model.addAttribute("faculties", faculties);


        if (choice.equals("save")) {
            return "faculty/faculty";
        }
        if (choice.equals("edit")) {
            Faculty fc = facultyService.getFacultyById(id);
            facultyService.delete(fc);

            return "redirect:/faculties";
        }
        return "/";


    }


}
