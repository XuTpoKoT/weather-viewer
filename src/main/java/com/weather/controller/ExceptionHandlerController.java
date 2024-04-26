package com.weather.controller;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@Log
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String badCredentials(RuntimeException ex, Model model) {
        log.info(ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
