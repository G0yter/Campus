package com.gmail.goyter012.campus.controller;

import com.gmail.goyter012.campus.model.Faculty;
import com.gmail.goyter012.campus.model.Group;
import com.gmail.goyter012.campus.service.FacultyService;
import com.gmail.goyter012.campus.service.GroupService;
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
@Slf4j
@RequiredArgsConstructor
public class GroupController {

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
                           @RequestParam String facName, Model model) {
        return saveOrEdit(null, cipher, facName, model, "save");
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
                            @RequestParam String facName, Model model) {
        return saveOrEdit(id, cipher, facName, model, "edit");
    }


    @GetMapping("/filterGroups")
    public String filter(@RequestParam String filterGroups, Model model) {
        model.addAttribute("groups", groupService.getAllGroupsByCipher(filterGroups));
        return "group/group";
    }


    private String saveOrEdit(@RequestParam Long id,
                              @RequestParam String cipher,
                              @RequestParam String facName,
                              Model model, String choice) {

        Group group = new Group();

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

        groupService.saveGroup(group);
        List<Group> groups = groupService.allGroups();
        model.addAttribute("groups", groups);


        if (choice.equals("save")) {
            return "group/group";
        }
        if (choice.equals("edit")) {
            Group gr = groupService.getGroupById(id);
            groupService.delete(gr);

            return "redirect:/groups";
        }

        return "/";

    }


}
