package com.springbootwithmvc.controller;

import com.springbootwithmvc.model.ReleaseTO;
import com.springbootwithmvc.service.ReleaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/releases")
public class ReleaseController {
    @Autowired
    private ReleaseService releaseService;

    @GetMapping("/list")
    public String findAllReleases(Model model){
        log.info("Inside the ReleaseController.findAllReleases");
        List<ReleaseTO> releaseTOS = null;

        try{
            releaseTOS = releaseService.findAllReleases();
        } catch (Exception ex){
            log.error("Exception while getting release details");
            model.addAttribute("errorMessage", "Release details not found");
        }

        model.addAttribute("releases", releaseTOS);
        return "releases";
    }
}
