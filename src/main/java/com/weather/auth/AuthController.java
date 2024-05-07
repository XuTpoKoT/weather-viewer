package com.weather.auth;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/sign-up")
    public String getSignUpPage(@ModelAttribute("signUpReq") SignUpRequest req, HttpSession httpSession) {
        log.info((String) httpSession.getAttribute("sessionId"));
        log.info("getSignUpPage called");
        return "sign-up";
    }

    @GetMapping("/sign-in")
    public String getSignInPage(@ModelAttribute("signInReq") SignInRequest req) {
        log.info("getSignInPage called");
        return "sign-in";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute("signUpReq") SignUpRequest req, BindingResult bindingResult,
                         Model model, HttpSession httpSession) {
        log.info("signUp called with login " + req.login());
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }

        UUID sessionId = authService.signUp(req.login(), req.password());
        httpSession.setAttribute("sessionId", sessionId);
        model.addAttribute("login", req.login());

        log.info("Registration is successful: redirecting to the home page");

        return "redirect:/";
    }

    @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute("signInReq") SignInRequest req, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, HttpSession httpSession) {
        log.info("signIn called with login " + req.login());
        if (bindingResult.hasErrors()) {
            return "sign-in";
        }

        UUID sessionId = authService.signIn(req.login(), req.password());
        httpSession.setAttribute("sessionId", sessionId);
        redirectAttributes.addAttribute("login", req.login());

        log.info("SignIn is successful: redirecting to the home page");

        return "redirect:/";
    }

    @PostMapping("/sign-out")
    public String signOut(HttpSession httpSession) {
        authService.signOut(UUID.fromString(httpSession.getAttribute("sessionId").toString()));
        httpSession.removeAttribute("sessionId");
        log.info("SignOut is successful");

        return "redirect:/";
    }
}
