package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Service.ServiceCiudad;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RestController
    @RequestMapping("/ciudades")
    public class ControllerCiudad {

        @Autowired
        private ServiceCiudad ciudadRepository;

        @GetMapping("/")
        ModelAndView index() {
            return new ModelAndView("index");
        }



        @SneakyThrows
        @GetMapping("/lista")
        public ModelAndView ciudades() {
            ModelAndView mav = new ModelAndView("ciudades");
            mav.addObject("ciudades", ciudadRepository.buscarTodo());
            return mav;
        }



        @SneakyThrows
        @GetMapping("/{id}")
        public ResponseEntity<ModelCiudad> obtenerCiudadPorId(@PathVariable(value = "id") Long idCiudad) {
            Optional<ModelCiudad> ciudad = Optional.ofNullable(ciudadRepository.buscarPorId(idCiudad));
            return ciudad.map(modelCiudad -> ResponseEntity.ok().body(modelCiudad)).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @GetMapping("/nuevo")
        ModelAndView nuevo(){
            return new ModelAndView("crearCiudad")
                    .addObject("ciudad" , new ModelCiudad());
        }

        @PostMapping("/nuevo")
        public ModelAndView crear(ModelCiudad ciudad, RedirectAttributes ra) {
            ModelAndView mav = new ModelAndView();
            try {
                ciudadRepository.saveOne(ciudad);
                ra.addFlashAttribute("msgExito","Ciudad creada con éxito!");
                mav.setViewName("redirect:/ciudades/lista");
            } catch (Exception e) {
                mav.setViewName("error");
                mav.addObject("mensaje", "Error al crear la ciudad: " + e.getMessage());
            }
            return mav;
        }

        @PostMapping("/crear")
        public ModelAndView crearCiudad(@ModelAttribute("ciudadForm") ModelCiudad nuevaCiudad) {
            ModelAndView mav = new ModelAndView("crearCiudad");
            try {
                ciudadRepository.saveOne(nuevaCiudad);
                mav.addObject("mensaje", "La ciudad se ha creado correctamente");
            } catch (Exception e) {
                mav.addObject("mensaje", "Error al crear la ciudad: " + e.getMessage());
            }
            return mav;
        }


        @SneakyThrows
        @PutMapping("/{id}")
        public ResponseEntity<ModelCiudad> actualizarCiudad(@PathVariable(value = "id") Long idCiudad,
                                                       @RequestBody ModelCiudad ciudadActualizada) {
            Optional<ModelCiudad> ciudad = Optional.ofNullable(ciudadRepository.buscarPorId(idCiudad));
            if (ciudad.isPresent()) {
                ModelCiudad ciudadExistente = ciudad.get();
                ciudadExistente.setNombre(ciudadActualizada.getNombre());
                ciudadExistente.setCodigoPostal(ciudadActualizada.getCodigoPostal());
                ModelCiudad ciudadActualizadaGuardada = ciudadRepository.saveOne(ciudadExistente);
                return ResponseEntity.ok(ciudadActualizadaGuardada);
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @SneakyThrows
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarCiudad(@PathVariable(value = "id") Long idCiudad) {
            Optional<ModelCiudad> ciudad = Optional.ofNullable(ciudadRepository.buscarPorId(idCiudad));
            if (ciudad.isPresent()) {
                ciudadRepository.deleteById(idCiudad);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
