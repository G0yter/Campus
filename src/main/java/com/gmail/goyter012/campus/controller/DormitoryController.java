package com.gmail.goyter012.campus.controller;

import com.gmail.goyter012.campus.model.Dormitory;
import com.gmail.goyter012.campus.service.DormitoryService;
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
public class DormitoryController {

    @Value("${upload.path}")
    private String uploadPath;

    private DormitoryService dormitoryService;

    @Autowired
    public void setDormitoryService(DormitoryService dormitoryService) {
        this.dormitoryService = dormitoryService;
    }


    @GetMapping("/dormitories")
    public String dormitoryList(Model model) {
        model.addAttribute("dormitories", dormitoryService.allDormitories());
        return "dormitory/dormitory";
    }

    @GetMapping("filterDormitories")
    public String filter(@RequestParam String filterDormitories, Model model) {
        model.addAttribute("dormitories", dormitoryService.getAllDormitoriesByNumber(filterDormitories));
        return "dormitory/dormitory";
    }

    @GetMapping("/getAllDormitories")
    public String getAllDormitories(Model model){
        model.addAttribute("dormitories", dormitoryService.allDormitories());
        return "dormitory/dormitory";
    }


    @GetMapping("/editDormitory/{id}")
    public String dormEditForm(@PathVariable Long id, Model model) {
        Dormitory dormitory = dormitoryService.getDormById(id);
        model.addAttribute("dormitory", dormitory);
        return "dormitory/dormEdit";
    }


    @PostMapping("/updateDormitory")
    public String editDormitory(@RequestParam Long id,
                                @RequestParam String dNum,
                                @RequestParam String dAddr,
                                @RequestParam String phone,
                                @RequestParam String conName, Model model,
                                @RequestParam("editDormitoryFile") MultipartFile file) throws IOException {
        return saveOrEdit(file, id, dNum, dAddr, phone, conName, model, "edit");
    }


    @PostMapping("/dormitories")
    public String addDormitory(@RequestParam String dNum,
                               @RequestParam String dAddr,
                               @RequestParam String phone,
                               @RequestParam String conName, Model model,
                               @RequestParam("dormitoryFile") MultipartFile file) throws IOException {
        return saveOrEdit(file, null, dNum, dAddr, phone, conName, model, "save");
    }


    @GetMapping("/delDormitory/{id}")
    public String StudDelForm(@PathVariable Long id, Model model) {
        Dormitory dormitory = dormitoryService.getDormById(id);
        model.addAttribute("dormitory", dormitory);
        return "dormitory/dormDel";
    }

    @PostMapping("/deleteDormitory")
    public String studDel(@RequestParam Long id) {
        Dormitory dormitory = dormitoryService.getDormById(id);
        dormitoryService.delete(dormitory);

        return "redirect:/dormitories";
    }


    private String saveOrEdit(MultipartFile file,
                              @RequestParam Long id,
                              @RequestParam String dNum,
                              @RequestParam String dAddr,
                              @RequestParam String phone,
                              @RequestParam String conName, Model model, String choice) throws IOException {


        Dormitory dormitory;
        if(id != null) {
            dormitory = dormitoryService.getDormById(id) != null ? dormitoryService.getDormById(id) : new Dormitory();
        } else dormitory = new Dormitory();

        dormitory.setAddress(dAddr);
        dormitory.setCommFullName(conName);
        dormitory.setPhone(phone);

        if (dAddr.equals("") || phone.equals("") || conName.equals("")) {
            return "errors/errorInput";
        }

        try {
            dormitory.setDormNumber(Integer.valueOf(dNum));

        } catch (NumberFormatException e) {
            return "errors/errorInput";
        }


        if(file != null && !file.getOriginalFilename().isEmpty()){

            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdirs();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            dormitory.setFilename(resultFilename);

        }



        dormitoryService.saveDormitory(dormitory);
        List<Dormitory> dormitories = dormitoryService.allDormitories();
        model.addAttribute("dormitories", dormitories);

        if (choice.equals("save")) return "dormitory/dormitory";
        else if (choice.equals("edit")) return "redirect:/dormitories";

        return "/";

    }


}
