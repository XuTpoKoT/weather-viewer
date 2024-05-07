package com.weather.controller;

import com.weather.auth.SignUpFailedException;
import com.weather.error.DBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = {RuntimeException.class})
    public String unknownError(RuntimeException ex, Model model) {
        log.error("Unknown error", ex);
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(value = {DBException.class})
    public String dbException(RuntimeException ex, Model model) {
        log.error("DBException", ex);
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(value = {SignUpFailedException.class})
    public String signUpFailedException(RuntimeException ex, Model model) {
        log.info(ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
