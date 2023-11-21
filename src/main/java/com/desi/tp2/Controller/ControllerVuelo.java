package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Repository.RepoVuelo;
import com.desi.tp2.Service.ServiceVuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/vuelos")
    public class ControllerVuelo {

    @Autowired
    private ServiceVuelo vueloRepository;

    @GetMapping("/todos")
    public String busquedaVuelo(Model model){
        try {
            List<ModelVuelo> vuelos = this.vueloRepository.buscarTodo();
            model.addAttribute("vuelos", vuelos);
            return "vuelos.html";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error.html";
        }
    }
/*    @GetMapping("/lista")
    public ModelAndView vuelos() {
        ModelAndView mav = new ModelAndView("vuelos");
        mav.addObject("vuelos", this.vueloRepository.findAll());
        return mav;
    }

    @GetMapping("/obtenervuelos")
    public List<ModelVuelo> obtenervuelos() {
        return vueloRepository.findAll();
    }*/

    /*@PostMapping("/nuevo")
    public ModelVuelo crearVuelo(@RequestBody ModelVuelo nuevoVuelo) {
        return vueloRepository.save(nuevoVuelo);
    }*/

    @PostMapping("/nuevo")
    public String crearVuelo(Model modelo) {
        ModelVuelo vuelo = new ModelVuelo();
        modelo.addAttribute("vuelo", vuelo);
        return "crearvuelo";
    }
}
