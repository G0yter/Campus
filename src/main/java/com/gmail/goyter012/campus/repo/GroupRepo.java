package com.gmail.goyter012.campus.repo;

import com.gmail.goyter012.campus.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepo extends CrudRepository<Group, Long> {
    List<Group> findAllByCipher(String cipher);
}
