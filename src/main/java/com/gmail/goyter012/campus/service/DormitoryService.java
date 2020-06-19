package com.gmail.goyter012.campus.service;

import com.gmail.goyter012.campus.model.Dormitory;
import com.gmail.goyter012.campus.repo.DormitoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DormitoryService {


    private DormitoryRepo dormitoryRepo;

    @Autowired
    public void setDormitoryRepo(DormitoryRepo dormitoryRepo) {
        this.dormitoryRepo = dormitoryRepo;
    }


    public List<Dormitory> allDormitories() {
        return (List<Dormitory>) dormitoryRepo.findAll();
    }

    public Dormitory getDormById(Long id) {
        return dormitoryRepo.findById(id).get();

    }

    public void delete(Dormitory dormitory) {
        dormitoryRepo.delete(dormitory);

    }

    public Dormitory saveDormitory(Dormitory dormitory) {
        dormitoryRepo.save(dormitory);
        return dormitory;
    }

    public List<Dormitory> getAllDormitoriesByNumber(String number) {
        List<Dormitory> dormitories = new ArrayList<>();
        try {
            dormitories.addAll(dormitoryRepo.findAllByDormNumber(Integer.valueOf(number)));
        } catch (NumberFormatException e) {
        }
        return dormitories;
    }


}
