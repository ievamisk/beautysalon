package com.beautysalon.BeautySalon.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value="/registerView", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        return modelAndView;
    }
//    @RequestMapping("/registerView")
//    public String register(Model model, @RequestParam(value="register", required=false, defaultValue = "index") String name) {
//        model.addAttribute("register", name);
//        return "register";
//    }

}