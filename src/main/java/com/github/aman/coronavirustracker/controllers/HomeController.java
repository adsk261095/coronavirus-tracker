package com.github.aman.coronavirustracker.controllers;

import com.github.aman.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
NOTE: we are using controller which can return anything (json, page, etc)
unlike a RestController which can only return json responses
 */
@Controller
public class HomeController {
    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("locationStats", coronavirusDataService.getAllStats());
        return "home";
    }
}
