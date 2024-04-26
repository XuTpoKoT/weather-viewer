package com.weather.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Log
public class MainController {
    @GetMapping
    public String getMainPage(Model model) {
        log.info("getMainPage called");
        boolean isAuthorised = false;
        model.addAttribute(isAuthorised);
        return "home";
    }
}
