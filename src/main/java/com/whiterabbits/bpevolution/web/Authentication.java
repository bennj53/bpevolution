package com.whiterabbits.bpevolution.web;

import com.whiterabbits.bpevolution.dao.RoleRepository;
import com.whiterabbits.bpevolution.dao.UserRepository;
import com.whiterabbits.bpevolution.entities.Role;
import com.whiterabbits.bpevolution.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Authentication {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value="/saveUser", method = RequestMethod.GET)
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @RequestMapping(value="/users")
    public Page<User> listAllUsers(int page, int size){
        return userRepository.findAll(new PageRequest(page,size));
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/saveRole", method = RequestMethod.GET)
    public Role saveRole(Role role){return roleRepository.save(role);}

    @RequestMapping(value="/roles")
    public Page<Role> listRoles(int page, int size){
        return roleRepository.findAll(new PageRequest(page,size));
    }


    @RequestMapping(value="/addRole")
    public User addRoleToUser(@RequestParam(name = "userLogin")String login, @RequestParam(name = "roleName")String name){
        User user = userRepository.getOne(login);
        if(user != null){
            Role role = roleRepository.getOne(name);
            if (role != null){
                user.getRoles().add(role);
                userRepository.save(user);
            }
        }
        return user;
    }

}
