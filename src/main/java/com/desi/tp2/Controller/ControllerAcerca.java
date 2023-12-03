package com.desi.tp2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerAcerca {

 
    @GetMapping("/acerca")
    ModelAndView acercade() throws Exception {

        return new ModelAndView("Acerca");
    }
}