package com.beautysalon.BeautySalon.Controllers;
import com.beautysalon.BeautySalon.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value="/registerView", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("RegisterPage");
        return modelAndView;
    }

}

//    @RequestMapping(value = "/registerView", method = RequestMethod.GET, produces = "text/html")
//    public ModelAndView registerView() {
//        return new ModelAndView("./../Views/RegisterPage.html");
//    }
//}