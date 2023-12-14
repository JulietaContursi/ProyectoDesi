package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Service.ServiceAvion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/aviones")
public class ControllerAvion {

    @Autowired
    private ServiceAvion avionRepository;


    @GetMapping("/lista")
    public ModelAndView aviones() throws Exception {
        ModelAndView mav = new ModelAndView("aviones");
        List<ModelAvion> aviones = null;
        mav.addObject("aviones", avionRepository.buscarTodo());
        mav.addObject("cantidadAsientos", avionRepository.buscarTodosConCantidadAsientos());
        return mav;
    }


    @GetMapping("/{id}") //buscan un avión por su id en el avionRepository y devuelven una ResponseEntity con diferentes códigos de estado dependiendo de si se encontró o no el avión.
    public ResponseEntity<ModelAvion> obtenerAvionPorId(@PathVariable(value = "id") Long idAvion) throws Exception {
        Optional<ModelAvion> avion = Optional.ofNullable(avionRepository.buscarPorId(idAvion));
        return avion.map(modelAvion -> ResponseEntity.ok().body(modelAvion)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/nuevo")
    ModelAndView nuevo(){
        return new ModelAndView("crearAvion")
                .addObject("avion" , new ModelAvion());
    }

    @PostMapping("/nuevo")
    public ModelAndView crear(ModelAvion avion, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            avionRepository.guardar(avion);
            ra.addFlashAttribute("msgExito","Avion creado con éxito!");
            mav.setViewName("redirect:/aviones/lista");
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("mensaje", "Error al crear el avion: " + e.getMessage());
        }
        return mav;
    }
    @PutMapping("/{id}")
    public ResponseEntity<ModelAvion> actualizarAvion(@PathVariable(value = "id") Long idAvion,
                                                        @RequestBody ModelAvion avionActualizado) throws Exception {
        Optional<ModelAvion> avion = Optional.ofNullable(avionRepository.buscarPorId(idAvion));
        if (avion.isPresent()) {
            ModelAvion avionExistente = avion.get();
            avionExistente.setNombre(avionActualizado.getNombre());
            avionExistente.setFilas(avionActualizado.getFilas());
            avionExistente.setAsientosXFila(avionActualizado.getAsientosXFila());
            avionExistente.setAerolinea(avionActualizado.getAerolinea());
            ModelAvion avionActualizadoGuardado = avionRepository.guardar(avionExistente);
            return ResponseEntity.ok(avionActualizadoGuardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAvion(@PathVariable(value = "id") Long idAvion) throws Exception {
        Optional<ModelAvion> avion = Optional.ofNullable(avionRepository.buscarPorId(idAvion));
        if (avion.isPresent()) {
            avionRepository.borrar(idAvion);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

