package com.cerebra.mailsender.uicontroller;


import com.cerebra.mailsender.dto.JwtAuthResponseDto;
import com.cerebra.mailsender.dto.MailDto;
import com.cerebra.mailsender.dto.SignIn;
import com.cerebra.mailsender.dto.SignUp;
import com.cerebra.mailsender.exception.ApiExceptions;
import com.cerebra.mailsender.mapper.MailMapper;
import com.cerebra.mailsender.model.Mail;
import com.cerebra.mailsender.service.MailService;
import com.cerebra.mailsender.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ui")
@AllArgsConstructor
public class UiController {

    private final UserService userService;
    private final MailService mailService;
    private final MailMapper mailMapper;

    @GetMapping("")
    public String showSignInForm() {
        return "signin";
    }

    @PostMapping("/signin")
    public String performSignIn(SignIn signin, Model model, HttpServletResponse response) {
        try {
            JwtAuthResponseDto jwtResponse = userService.signIn(signin);
            Cookie authCookie = new Cookie("AUTH-TOKEN", jwtResponse.getAccessToken());
            authCookie.setHttpOnly(true);
            authCookie.setPath("/");
            response.addCookie(authCookie);
            return "redirect:/ui/mails";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "signin";
        }
    }

//    @GetMapping("/mails")
//    public String mailList(Model model) throws JsonProcessingException {
//        model.addAttribute("mails",mailService.getAllMails());
//        return "mails";
//    }
    @PostMapping("/add-mail")
    public String handleAddMail(@ModelAttribute MailDto mailDto, Model model) {
        mailService.saveMail(mailMapper.unMap(mailDto));
        return "redirect:/ui/mails";
    }
    @GetMapping("/add-mail")
    public String showAddMailForm(Model model) {
        MailDto mailDto = new MailDto();
        model.addAttribute("mailDto", mailDto);
        return "add-mail";
    }
    @GetMapping("/mail-detail/{id}")
    public String mailDetail(@PathVariable Long id, Model model) throws JsonProcessingException {
        Mail mail = mailService.getMailWithLinksById(id);
        MailDto mailDto = mailMapper.map(mail);
        model.addAttribute("mail", mailDto);
        return "mail-detail";
    }
    @PostMapping("/send-mail/{id}")
    public String sendMail(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            String result = mailService.sendEmail(id);
            redirectAttributes.addFlashAttribute("successMessage", "Email sent successfully: " + result);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error sending email: " + e.getMessage());
        }
        return "redirect:/ui/mails";
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
            return "redirect:/ui";
        } catch (ApiExceptions e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @PostMapping("/delete-mail/{id}")
    public String deleteMail(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            mailService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Mail deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting mail: " + e.getMessage());
        }
        return "redirect:/ui/mails";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie authCookie = new Cookie("AUTH-TOKEN", null);
        authCookie.setMaxAge(0);
        authCookie.setPath("/");
        response.addCookie(authCookie);
        return "redirect:/ui";
    }
}
