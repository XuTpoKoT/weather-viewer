package com.weatherviewer.common;

import com.weatherviewer.auth.BadCredentialsException;
import com.weatherviewer.auth.SignUpFailedException;
import com.weatherviewer.locations.GetLocationsException;
import com.weatherviewer.weatherapi.GetForecastException;
import com.weatherviewer.weatherapi.GetWeatherException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = {RuntimeException.class})
    public String unknownError(RuntimeException ex, Model model) {
        log.error("Unknown error", ex);
        model.addAttribute("error", "Unknown error");
        return "error";
    }

    @ExceptionHandler(value = {GetLocationsException.class, GetForecastException.class, GetWeatherException.class})
    public String weatherApiError(RuntimeException ex, Model model) {
        log.error(ex.getMessage(), ex);
        model.addAttribute("error", ex.getMessage());
        return "home";
    }

    @ExceptionHandler(value = {SignUpFailedException.class})
    public String signUpFailedException(RuntimeException ex, Model model) {
        log.info(ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public String badCredentialsException(RedirectAttributes redirectAttributes) {
        log.info("bad credentials");
        redirectAttributes.addAttribute("error", "bad credentials");
        return "redirect:/sign-in";
    }
}
