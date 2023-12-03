package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Service.ServiceAvion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@RestController
@RequestMapping("/aviones")
public class ControllerAvion {

    @Autowired
    private ServiceAvion avionRepository;


    @GetMapping("/lista")
    public ModelAndView aviones() throws Exception {
        ModelAndView mav = new ModelAndView("aviones");
        mav.addObject("aviones", avionRepository.buscarTodo());
        return mav;
    }


    @GetMapping("/{id}") //buscan un avión por su id en el avionRepository y devuelven una ResponseEntity con diferentes códigos de estado dependiendo de si se encontró o no el avión.
    public ResponseEntity<ModelAvion> obtenerAvionPorId(@PathVariable(value = "id") Long idAvion) throws Exception {
        Optional<ModelAvion> avion = Optional.ofNullable(avionRepository.buscarPorId(idAvion));
        //Se crea un Optional que envuelve el resultado de la búsqueda de un avión por su id en el avionRepository. Si el avión existe, se guarda en el Optional; de lo contrario, el Optional estará vacío.
        return avion.map(modelAvion -> ResponseEntity.ok().body(modelAvion)).orElseGet(() -> ResponseEntity.notFound().build());
        //Se utiliza el método map() de Optional para realizar una operación si el Optional contiene un valor. En este caso, si el Optional tiene un avión, se crea una ResponseEntity con el código de estado OK y el cuerpo del mensaje es el modelo del avión encontrado.
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

