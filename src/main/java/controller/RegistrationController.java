package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.user.CurrentUser;
import domain.user.User;
import service.user.PasswordEncoderService;
import service.user.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm(CurrentUser currentUser,
        RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView;
        if (currentUser == null) {
            modelAndView = new ModelAndView("registration");
        } else {
            modelAndView = new ModelAndView("redirect:/home/");
            redirectAttributes.addFlashAttribute("message", "Вы уже вошли в систему!");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView registration(User user, CurrentUser currentUser,
        RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home/");
        if (currentUser == null) {
            user.setPasswordHash(passwordEncoderService.getPasswordEncoder().encodePassword(user.getPasswordHash(), passwordEncoderService.getSaltSource().getSalt(currentUser)));
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message",
                "Вы зарегистрировались в нашей системе, теперь вы можете войти в систему!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Вы уже вошли в систему!");
        }
        return modelAndView;
    }
}
