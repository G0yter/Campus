package com.gmail.goyter012.campus.controller;

import com.gmail.goyter012.campus.model.Faculty;
import com.gmail.goyter012.campus.model.Group;
import com.gmail.goyter012.campus.service.FacultyService;
import com.gmail.goyter012.campus.service.GroupService;
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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GroupController {

    @Value("${upload.path}")
    private String uploadPath;

    private GroupService groupService;

    private FacultyService facultyService;

    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public String groups(Model model) {
        model.addAttribute("groups", groupService.allGroups());
        return "group/group";
    }

    @GetMapping("/getAllGroups")
    public String getAllGroups(Model model) {
        model.addAttribute("groups", groupService.allGroups());
        return "group/group";
    }


    @GetMapping("/delGroup/{id}")
    public String StudDelForm(@PathVariable Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "group/groupDel";
    }


    @PostMapping("/deleteGroup")
    public String studDel(@RequestParam Long id) {
        Group group = groupService.getGroupById(id);
        groupService.delete(group);

        return "redirect:/groups";
    }


    @PostMapping("/groups")
    public String addGroup(@RequestParam String cipher,
                           @RequestParam String facName, Model model,
                           @RequestParam("groupFile") MultipartFile file) throws IOException {
        return saveOrEdit(file, null, cipher, facName, model, "save");
    }


    @GetMapping("/editGroup/{id}")
    public String groupEditForm(@PathVariable Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "group/editGroup";
    }

    @PostMapping("/updateGroup")
    public String editGroup(@RequestParam Long id,
                            @RequestParam String cipher,
                            @RequestParam String facName, Model model,
                            @RequestParam("editGroupFile") MultipartFile file) throws IOException {
        return saveOrEdit(file, id, cipher, facName, model, "edit");
    }


    @GetMapping("/filterGroups")
    public String filter(@RequestParam String filterGroups, Model model) {
        model.addAttribute("groups", groupService.getAllGroupsByCipher(filterGroups));
        return "group/group";
    }


    private String saveOrEdit(MultipartFile file,
                              @RequestParam Long id,
                              @RequestParam String cipher,
                              @RequestParam String facName,
                              Model model, String choice) throws IOException {


        Group group;
        if (id != null) {
            group = groupService.getGroupById(id) != null ? groupService.getGroupById(id) : new Group();
        } else group = new Group();

        group.setCipher(cipher);

        if (cipher.equals("")) {
            return "errors/errorInput";
        }

        List<Faculty> faculties = facultyService.allFaculties();

        for (int i = 0; i < faculties.size(); i++) {
            if (faculties.get(i).getName().equals(facName)) {
                group.setFaculty(faculties.get(i));
                break;
            }
        }
        if (group.getFaculty() == null) {
            return "errors/facultyNotInList";
        }

        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            group.setFilename(resultFilename);

        }


        groupService.saveGroup(group);
        List<Group> groups = groupService.allGroups();
        model.addAttribute("groups", groups);


        if (choice.equals("save")) return "group/group";
        if (choice.equals("edit")) return "redirect:/groups";

        return "/";

    }


}
