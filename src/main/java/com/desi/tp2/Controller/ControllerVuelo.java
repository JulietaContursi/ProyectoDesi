package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Service.ServiceAvion;
import com.desi.tp2.Service.ServiceCiudad;
import com.desi.tp2.Service.ServiceVuelo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vuelos")
    public class ControllerVuelo {

    @Autowired
    private ServiceVuelo vueloRepository;
    @Autowired
    private ServiceCiudad ciudadRepository;
    @Autowired
    private ServiceAvion avionRepository;

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
    @GetMapping("/nuevo") //localhost:8080/vuelos/nuevo
    public String registrar(ModelMap modelo) throws Exception {
        ModelVuelo vuelo = new ModelVuelo();

        modelo.addAttribute("vuelo", vuelo);
        return "crearVuelo.html";
    }
    @ModelAttribute("allCiudades")
    public List<ModelCiudad> getAllCiudades() throws Exception {
        return this.ciudadRepository.buscarTodo();
    }
    @ModelAttribute("allAviones")
    public List<ModelAvion> getAllAviones() throws Exception {
        return this.avionRepository.buscarTodo();
    }

    /*@GetMapping("/nuevo")
    public ModelAndView nuevo() throws Exception {
        ModelAndView mav = new ModelAndView("crearVuelo");
        mav.addObject("vuelo", new ModelVuelo());
        mav.addObject("ciudades", ciudadRepository.buscarTodo());
        return mav;
    }
    @GetMapping("/nuevo")
    ModelAndView nuevo(){
        return new ModelAndView("crearVuelo")
                .addObject("vuelo" , new ModelVuelo());
    }*/

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

    @PutMapping("/{id}")
    public ResponseEntity<ModelVuelo> actualizarVuelo(@PathVariable(value = "id") Long idVuelo,
                                                        @RequestBody ModelVuelo vueloActualizado) throws Exception {
        Optional<ModelVuelo> vuelo = Optional.ofNullable(vueloRepository.buscarPorId(idVuelo));
        if (vuelo.isPresent()) {
            ModelVuelo vueloExistente = vuelo.get();
            vueloExistente.setCiudadOrigen(vueloActualizado.getCiudadOrigen());
            vueloExistente.setCiudadDestino(vueloActualizado.getCiudadDestino());
            vueloExistente.setTipo(vueloActualizado.getTipo());
            vueloExistente.setPrecioVuelo(vueloActualizado.getPrecioVuelo());
            vueloExistente.setFechaHora(vueloActualizado.getFechaHora());
            vueloExistente.setAvion(vueloActualizado.getAvion());
            vueloExistente.setEstado(vueloActualizado.getEstado());

            ModelVuelo vueloActualizadoGuardado = vueloRepository.guardar(vueloExistente);
            return ResponseEntity.ok(vueloActualizadoGuardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVuelo(@PathVariable(value = "id") Long idVuelo) throws Exception {
        Optional<ModelVuelo> vuelo = Optional.ofNullable(vueloRepository.buscarPorId(idVuelo));
        if (vuelo.isPresent()) {
            ciudadRepository.borrar(idVuelo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
