package com.whiterabbits.bpevolution.web;

import com.whiterabbits.bpevolution.dao.ApplicationRepository;
import com.whiterabbits.bpevolution.dao.BusinessProfileRepository;
import com.whiterabbits.bpevolution.entities.Application;
import com.whiterabbits.bpevolution.entities.BusinessProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value="/Bp")
public class BusinessProfileController {
    @Autowired
    private BusinessProfileRepository bpRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

    //Model pour passer des valeurs à la vue
    //RequestParam(name="page"... pour chercher un parametre page envoyé dans l'URL exemple: http://localhost:8081/Bp/Index?page=1
    //si non envoyé valeur par defaut est 0 exemple : http://localhost:8081/Bp/Index
    @RequestMapping(value="/Index")
    public String Ind(Model model, @RequestParam(name="page", defaultValue = "0")int p,
                      @RequestParam(name="motCle", defaultValue = "")String mc){

        //Page<BusinessProfile> listBp = bpRepository.findAll(new PageRequest(p,2));
        Page<BusinessProfile> listBp = bpRepository.findByCode("%"+mc+"%", new PageRequest(p,2));
        model.addAttribute("pageBusinessProfiles",listBp);

        int nbPage = listBp.getTotalPages();
        int[]tabPages = new int[nbPage];
        for (int i=0;i<nbPage;i++)tabPages[i]=i;
        model.addAttribute("pagesTab",tabPages);

        model.addAttribute("currentPage",p);

        model.addAttribute("motCle",mc);

        return "businessProfiles";
    }

    @RequestMapping(value = "/Detail")
    public String Detail(Model model, @RequestParam(name="pageDetail", defaultValue = "0")int p){
        Page<BusinessProfile> pages = bpRepository.findAllProfilesWithApp(new PageRequest(p,6, Sort.by("code").descending()));
        System.out.println("NB PAGE RETURNED : " + pages.getTotalPages());
        pages.getContent().forEach(e->{ System.out.println(e.getCode());
                                        e.getApplicationList().forEach(a->System.out.println( " " + a.getCode()));
                                        System.out.println("\n");
                                        });

        Map<BusinessProfile, List<Application>> map_Bp_App = new HashMap<>();
        Set<BusinessProfile> setKeys = new HashSet<>();

        pages.getContent().forEach(bp->{
            System.out.println(bp.getCode());
            setKeys.add(bp);
            List<Application> listApp = new ArrayList<>();
            bp.getApplicationList().forEach(app->{
                System.out.println( " " + app.getCode());
                listApp.add(app);
            });
            System.out.println("\n");
            map_Bp_App.put(bp,listApp);
        });

        int nbPages = pages.getTotalPages();
        System.out.println("------------------------"+ nbPages +"------------------------");
        int[] pageTab = new int[nbPages];
        for(int i=0;i<nbPages;i++){
            pageTab[i] = i;
        }

        model.addAttribute("pagesTab", pageTab);
        model.addAttribute("currentPage", p);
        model.addAttribute("mapBpApp",map_Bp_App);
        model.addAttribute("setKeys",setKeys);

        return "businessProfilesDetail";
    }

    @RequestMapping(value="/CreateBP", method = RequestMethod.GET)
    public String createBusinessProfile(Model model){
        model.addAttribute("businessProfile",new BusinessProfile());
        return "businessProfilesCreate";
    }
    @RequestMapping(value="/SaveBP", method = RequestMethod.POST, params = "action=save")
    public String saveBusinessProfile(BusinessProfile businessProfile){
        bpRepository.save(businessProfile);
        return "redirect:Index";
    }

    @RequestMapping(value="/SaveBP", method = RequestMethod.POST, params = "action=cancel")
    public String cancelBusinessProfile(BusinessProfile businessProfile){
        return "redirect:Index";
    }

    @RequestMapping(value="/ModifyBP", method = RequestMethod.GET)
    public String modifyBusinessProfile(Model model,@RequestParam(name="businessProfileId")Long id){
        System.out.println("businessProfile ::::: " + id );//+ " " + code + " " + label);
        BusinessProfile bp = bpRepository.findById(id).get();
        //envoi à la vue du Bp courant
        model.addAttribute("businessProfile", bp);
        List<Application> allApplicationList = applicationRepository.findAll();

        Set<Application>  allApplicationNotInCurrentBp = allApplicationList.stream()
                .filter(f->!bp.getApplicationList()
                .contains(f)).collect(Collectors.toSet());


        //envoi à la vue liste de toutes les applications non présentent dans Bp courant
        model.addAttribute("allApplicationList", allApplicationNotInCurrentBp);
        return "businessProfilesModify";
    }

    @RequestMapping(value="/addAppToBp", method = RequestMethod.GET)
    public @ResponseBody
    String addApplicationToBp(Model model,
                              @RequestParam(name="applicationId")Long idApp,
                              @RequestParam(name="businessId")Long idBp){

        BusinessProfile currentProfile = bpRepository.findById(idBp).get();
        Application currentApplication = applicationRepository.findById(idApp).get();

        currentProfile.getApplicationList().add(currentApplication);
        bpRepository.save(currentProfile);
        System.out.println("call addApplicationToBp with SUCCESS");
        System.out.println("applicationId : " + idApp);
        System.out.println("businessId : " + idBp);

        return "";

    }
}
