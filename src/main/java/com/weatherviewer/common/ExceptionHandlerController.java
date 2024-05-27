package com.weatherviewer.common;

import com.weatherviewer.auth.SignUpFailedException;
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
        model.addAttribute("error", "Unknown error");
        return "error";
    }

    @ExceptionHandler(value = {SignUpFailedException.class})
    public String signUpFailedException(RuntimeException ex, Model model) {
        log.info(ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
