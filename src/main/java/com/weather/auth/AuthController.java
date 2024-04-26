package com.weather.auth;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Log
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model, @ModelAttribute("signUpReq") SignUpRequest req, HttpSession httpSession) {
        log.info((String) httpSession.getAttribute("sessionId"));
        log.info("getSignUpPage called");
        boolean isAuthorised = false;
        model.addAttribute(isAuthorised);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute("signUpReq") SignUpRequest req, BindingResult bindingResult,
                         HttpSession httpSession) {
        log.info("signUp called with login " + req.login());
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }

        UUID sessionId = authService.signUp(req.login(), req.password());
        httpSession.setAttribute("sessionId", sessionId);

        log.info("Registration is successful: redirecting to the home page");

        return "redirect:/";
    }
}
