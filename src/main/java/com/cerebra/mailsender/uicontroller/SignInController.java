package com.cerebra.mailsender.uicontroller;


import com.cerebra.mailsender.dto.JwtAuthResponseDto;
import com.cerebra.mailsender.dto.SignIn;
import com.cerebra.mailsender.dto.SignUp;
import com.cerebra.mailsender.exception.ApiExceptions;
import com.cerebra.mailsender.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
@AllArgsConstructor
public class SignInController {

    private final UserService userService;

    @GetMapping("/signin")
    public String showSignInForm() {
        return "signin";
    }

    @PostMapping("/signin")
    public String performSignIn(SignIn signin, Model model, HttpServletRequest request) {
        try {
            JwtAuthResponseDto response = userService.signIn(signin);
            model.addAttribute("token", response.getAccessToken());
            request.getSession().setAttribute("token", response.getAccessToken());
            return "redirect:/ui/hello";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "signin";
        }
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String performSignUp(SignUp signUp, Model model) {
        try {
            String result = userService.signUp(signUp);
            model.addAttribute("message", result);
            return "redirect:/ui/signin"; // Redirect to the sign-in page after successful registration
        } catch (ApiExceptions e) {
            model.addAttribute("error", e.getMessage());
            return "signup"; // Stay on the sign-up page and show the error
        }
    }
}
