package com.whiterabbits.bpevolution.web;

import com.whiterabbits.bpevolution.dao.AccessRightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/Access")
public class AccessRightController {

    @Autowired
    AccessRightRepository accessRepository;
}
