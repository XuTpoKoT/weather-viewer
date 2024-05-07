package com.weather.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class MainController {
    @GetMapping
    public String getMainPage(HttpSession session) {
        log.info("getMainPage called with session id " + session.getAttribute("sessionId"));
        return "home";
    }
}
