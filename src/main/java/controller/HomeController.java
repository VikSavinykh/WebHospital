package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.user.CurrentUser;
import service.user.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDefaultPage(){
        return "redirect:/home/";
    }

    @RequestMapping(value = "/home/", method = RequestMethod.GET)
    public ModelAndView getHomePage(CurrentUser currentUser, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("message", modelMap.get("message"));
        return modelAndView;
    }

}
