package com.whiterabbits.bpevolution.web;


import com.whiterabbits.bpevolution.dao.ApplicationRepository;
import com.whiterabbits.bpevolution.entities.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/App")
public class ApplicationController {
    @Autowired
    ApplicationRepository appRepository;

    @RequestMapping(value="/Index")
    public String Index(Model model, @RequestParam(value = "page", defaultValue = "0")int p,
                        @RequestParam(value = "motCle", defaultValue = "")String mc){
        //Page<Application> pages = appRepository.findAll(new PageRequest(p,2));
        Page<Application> pages = appRepository.findByCode("%"+mc+"%", new PageRequest(p,2));
        model.addAttribute("pagesApp",pages);

        int nbPage = pages.getTotalPages();
        int[]tabPages = new int[nbPage];
        for (int i=0;i<nbPage;i++)tabPages[i]=i;
        model.addAttribute("pagesTab",tabPages);

        model.addAttribute("motCle",mc);

        model.addAttribute("currentPage", p);

        return "application";
    }
}
