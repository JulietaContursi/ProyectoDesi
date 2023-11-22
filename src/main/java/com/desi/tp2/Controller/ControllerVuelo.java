package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Service.ServiceVuelo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/vuelos")
    public class ControllerVuelo {

    @Autowired
    private ServiceVuelo vueloRepository;

    @GetMapping("/lista")
    public ModelAndView vuelos() throws Exception {
            ModelAndView mav = new ModelAndView("vuelos");
            mav.addObject("vuelos", vueloRepository.buscarTodo());
         return mav;
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<ModelVuelo> obtenerVueloPorId(@PathVariable(value = "id") Long idVuelo) {
        Optional<ModelVuelo> vuelo = Optional.ofNullable(vueloRepository.buscarPorId(idVuelo));
        return vuelo.map(modelVuelo -> ResponseEntity.ok().body(modelVuelo)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nuevo")
    ModelAndView nuevo(){
        return new ModelAndView("crearVuelo")
                .addObject("vuelo" , new ModelVuelo());
    }


    @PostMapping("/nuevo")
    public ModelAndView crear(ModelVuelo vuelo, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            vueloRepository.guardar(vuelo);
            ra.addFlashAttribute("msgExito","Vuelo creado con éxito!");
            mav.setViewName("redirect:/vuelos/lista");
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("mensaje", "Error al crear el vuelo: " + e.getMessage());
        }
        return mav;
    }
}
