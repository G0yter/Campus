package com.gmail.goyter012.campus.controller;

import com.gmail.goyter012.campus.model.Dormitory;
import com.gmail.goyter012.campus.model.Faculty;
import com.gmail.goyter012.campus.model.Group;
import com.gmail.goyter012.campus.model.Student;
import com.gmail.goyter012.campus.service.DormitoryService;
import com.gmail.goyter012.campus.service.FacultyService;
import com.gmail.goyter012.campus.service.GroupService;
import com.gmail.goyter012.campus.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private StudentService studentService;

    private FacultyService facultyService;

    private GroupService groupService;

    private DormitoryService dormitoryService;

    @Autowired
    public void setDormitoryService(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/")
    public String start() {
        return "index";
    }


    @GetMapping("/main")
    public String studentsList(Model model) {
        model.addAttribute("students", studentService.allStudents());
        return "stud/main";
    }


    @PostMapping("/main")
    public String addStudCard(@RequestParam String cNum,
                              @RequestParam String fName,
                              @RequestParam String doB,
                              @RequestParam String pAddrs,
                              @RequestParam String facName,
                              @RequestParam String grCipher,
                              @RequestParam String dormNum,
                              @RequestParam String room, Model model) {

        return saveOrEdit(null, cNum, fName, doB, pAddrs, facName, grCipher, dormNum, room, model, "save");
    }

    @GetMapping("/filterStudents")
    public String filter(@RequestParam String filterStudents, Model model) {
        Iterable<Student> students;
        try {
            int cardNumber = Integer.valueOf(filterStudents);
            students = studentService.getAllStudentsByCardNumber(cardNumber);
        } catch (NumberFormatException e) {
            students = studentService.getAllStudentsByName(filterStudents);
        }
        model.addAttribute("students", students);

        return "stud/main";
    }

    @GetMapping("/edit/{id}")
    public String StudEditForm(@PathVariable Long id, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        model.addAttribute("cardNum", ("" + student.getCardNumber()).replaceAll(" ", ""));
        model.addAttribute("dateOfBirth", sdf.format(student.getDateOfBirth()));
        return "stud/studEdit";
    }

    @PostMapping("/update")
    public String studEdit(@RequestParam Long id,
                           @RequestParam String cNum,
                           @RequestParam String fName,
                           @RequestParam String doB,
                           @RequestParam String pAddrs,
                           @RequestParam String facName,
                           @RequestParam String grCipher,
                           @RequestParam String dormNum,
                           @RequestParam String room,
                           Model model) {

        return saveOrEdit(id, cNum, fName, doB, pAddrs, facName, grCipher, dormNum, room, model, "edit");
    }


    @GetMapping("/del/{id}")
    public String StudDelForm(@PathVariable Long id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "stud/studDel";
    }

    @PostMapping("/delete")
    public String studDel(@RequestParam Long id) {
        Student student = studentService.getById(id);
        studentService.delete(student);

        return "redirect:/main";
    }


    private String saveOrEdit(@RequestParam Long id,
                              @RequestParam String cNum,
                              @RequestParam String fName,
                              @RequestParam String doB,
                              @RequestParam String pAddrs,
                              @RequestParam String facName,
                              @RequestParam String grCipher,
                              @RequestParam String dormNum,
                              @RequestParam String room,
                              Model model, String choice) {

        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Student student = new Student();


        if (fName.equals("") || pAddrs.equals("")) {
            return "errors/errorInput";
        }

        student.setFullName(fName);
        student.setAddressOfParents(pAddrs);

        try {
            student.setCardNumber(Integer.valueOf(cNum));
            student.setRoom(Integer.valueOf(room));
        } catch (NumberFormatException e) {
            return "errors/errorInput";
        }

        try {
            date = sdf.parse(doB);
            student.setDateOfBirth(date);
        } catch (ParseException e) {
            return "errors/errorInput";
        }


        List<Faculty> faculties = facultyService.allFaculties();

        for (int i = 0; i < faculties.size(); i++) {
            if (faculties.get(i).getName().equals(facName)) {
                student.setFaculty(faculties.get(i));
                break;
            }
        }
        if (student.getFaculty() == null) {
            return "errors/facultyNotInList";
        }


        List<Group> groups = groupService.allGroups();

        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getCipher().equals(grCipher)) {
                student.setGroupOfStuds(groups.get(i));
                break;
            }
        }
        if (student.getGroupOfStuds() == null) {
            return "errors/groupNotInList";
        }


        List<Dormitory> dormitories = dormitoryService.allDormitories();

        Integer intDormNum;
        try {
            intDormNum = Integer.valueOf(dormNum);
            for (int i = 0; i < dormitories.size(); i++) {
                if (dormitories.get(i).getDormNumber().equals(intDormNum)) {
                    student.setDormitory(dormitories.get(i));
                    break;
                }
            }
        } catch (NumberFormatException e) {
            return "errors/dormitoryNotInlist";
        }

        if (student.getDormitory() == null) {
            return "errors/dormitoryNotInlist";
        }


        studentService.saveStudent(student);
        List<Student> students = studentService.allStudents();
        model.addAttribute("students", students);

        if (choice.equals("save")) {
            return "stud/main";

        }
        if (choice.equals("edit")) {
            Student st = studentService.getById(id);
            studentService.delete(st);

            return "redirect:/main";
        }

        return "/";

    }


}
