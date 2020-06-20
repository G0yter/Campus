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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    @Value("${upload.path}")
    private String uploadPath;

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
        return "redirect:/main";
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
                              @RequestParam String room, Model model,
                              @RequestParam("studentFile") MultipartFile file
    ) throws IOException {

        return saveOrEdit(null, cNum, fName, doB, pAddrs, facName, grCipher, dormNum, room, file, model, "save");
    }


    @GetMapping("/getAllStudents")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.allStudents());
        return "stud/main";
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
                           @RequestParam("editStudentFile") MultipartFile file,
                           Model model) throws IOException {

        return saveOrEdit(id, cNum, fName, doB, pAddrs, facName, grCipher, dormNum, room, file, model, "edit");
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
                              MultipartFile file,
                              Model model, String choice) throws IOException {

        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        Student student;
        if(id != null){
            student = studentService.getById(id) != null ? studentService.getById(id) : new Student();
        } else student = new Student();


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


        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            student.setFilename(resultFilename);

        }


        studentService.saveStudent(student);
        List<Student> students = studentService.allStudents();
        model.addAttribute("students", students);

        if (choice.equals("save")) return "stud/main";
        else if (choice.equals("edit")) return "redirect:/main";

        return "/";

    }


}
