package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Repository.RepoCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/ciudades")
    public class ControllerCiudad {

        @Autowired
        private RepoCiudad ciudadRepository;

        @PostMapping("/cargaciudades")
        public void cargaCiudades(){
            ModelCiudad ciudad1 = new ModelCiudad();
            ModelCiudad ciudad2 = new ModelCiudad();
            ModelCiudad ciudad3 = new ModelCiudad();
            ciudad1.setCodigoPostal(5000);
            ciudad1.setNombre("Cordoba");
            ciudadRepository.save(ciudad1);
            ciudad2.setCodigoPostal(1605);
            ciudad2.setNombre("Munro");
            ciudadRepository.save(ciudad2);
            ciudad3.setCodigoPostal(4716);
            ciudad3.setNombre("Villa Dolores");
            ciudadRepository.save(ciudad3);

        }
    @GetMapping("/")
    public String obtenerCiudades(Model model) {
        List<ModelCiudad> ciudades = ciudadRepository.findAll();
        model.addAttribute("ciudades",ciudades);
        return "ciudades";
    }


        /*@GetMapping("/")
        public List<ModelCiudad> obtenerCiudades() {
            return ciudadRepository.findAll();
        }*/

        @GetMapping("/{id}")
        public ResponseEntity<ModelCiudad> obtenerCiudadPorId(@PathVariable(value = "id") Long idCiudad) {
            Optional<ModelCiudad> ciudad = ciudadRepository.findById(idCiudad);
            return ciudad.map(modelCiudad -> ResponseEntity.ok().body(modelCiudad)).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping("/")
        public ModelCiudad crearCiudad(@RequestBody ModelCiudad nuevaCiudad) {
            return ciudadRepository.save(nuevaCiudad);
        }

        @PutMapping("/{id}")
        public ResponseEntity<ModelCiudad> actualizarCiudad(@PathVariable(value = "id") Long idCiudad,
                                                       @RequestBody ModelCiudad ciudadActualizada) {
            Optional<ModelCiudad> ciudad = ciudadRepository.findById(idCiudad);
            if (ciudad.isPresent()) {
                ModelCiudad ciudadExistente = ciudad.get();
                ciudadExistente.setNombre(ciudadActualizada.getNombre());
                ciudadExistente.setCodigoPostal(ciudadActualizada.getCodigoPostal());
                ModelCiudad ciudadActualizadaGuardada = ciudadRepository.save(ciudadExistente);
                return ResponseEntity.ok(ciudadActualizadaGuardada);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarCiudad(@PathVariable(value = "id") Long idCiudad) {
            Optional<ModelCiudad> ciudad = ciudadRepository.findById(idCiudad);
            if (ciudad.isPresent()) {
                ciudadRepository.delete(ciudad.get());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
