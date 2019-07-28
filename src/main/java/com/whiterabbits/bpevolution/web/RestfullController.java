package com.whiterabbits.bpevolution.web;

import com.whiterabbits.bpevolution.dao.BusinessProfileRepository;
import com.whiterabbits.bpevolution.entities.BusinessProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ctrlRest")
public class RestfullController {

    @Autowired
    BusinessProfileRepository bpRepository;

    @GetMapping(value="/{id}", produces = "application/json")
    public BusinessProfile getBPRest(@PathVariable Long id) {
        return bpRepository.findById(id).get();
    }

}
