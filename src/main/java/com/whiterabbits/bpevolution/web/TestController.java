package com.whiterabbits.bpevolution.web;

import com.whiterabbits.bpevolution.dao.BusinessProfileRepository;
import com.whiterabbits.bpevolution.entities.BusinessProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/Test")
public class TestController {

    @Autowired
    BusinessProfileRepository bpRepository;

    @RequestMapping(value="/TestPage", method = RequestMethod.GET)
    public String testPage(Model model){
        model.addAttribute("businessProfile", new BusinessProfile());
        return "test";
    }

    @RequestMapping(value="/testSave", method = RequestMethod.POST)
    public String testSave(BusinessProfile businessProfile){
        bpRepository.save(businessProfile);
        return "testPage";
    }

    @RequestMapping(value="/addAppToBp", method = RequestMethod.GET)
    public @ResponseBody
    String addApplicationToBp(Model model,
                                     @RequestParam(name="applicationId")Long idApp,
                                     @RequestParam(name="businessId")Long idBp){
       /* BusinessProfile currentProfile = bpRepository.findById(idBp).get();
        Application currentApplication = applicationRepository.findById(idApp).get();

        currentProfile.getApplicationList().add(currentApplication);
        bpRepository.save(currentProfile);*/
        System.out.println("call addApplicationToBp with SUCCESS");
        System.out.println("applicationId : " + idApp);
        System.out.println("businessId : " + idBp);

        return "";

    }
}
