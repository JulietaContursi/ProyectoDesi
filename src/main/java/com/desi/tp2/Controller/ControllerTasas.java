package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Model.ModelComponentePrecio;
import com.desi.tp2.Service.ServiceComponentePrecio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasas")
public class ControllerTasas {

    @Autowired
    private ServiceComponentePrecio tasasRepo;

    @GetMapping("/lista")
    public ModelAndView tasas() throws Exception {
        ModelAndView mav = new ModelAndView("tasas");
        ModelComponentePrecio registro = tasasRepo.buscarPorId(1);
        mav.addObject("registro", registro);
        return mav;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ModelComponentePrecio> obtenerTasaPorId(@PathVariable(value = "id") Long idComponentePrecio) throws Exception {
        Optional<ModelComponentePrecio> tasa = Optional.ofNullable(tasasRepo.buscarPorId(idComponentePrecio));
        return tasa.map(modelComponentePrecio -> ResponseEntity.ok().body(modelComponentePrecio)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nueva")
    ModelAndView nueva(){
        return new ModelAndView("tasas")
                .addObject("tasa" , new ModelComponentePrecio());
    }

    @PostMapping("/nueva")
    public ModelAndView crear(ModelComponentePrecio tasa, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            tasasRepo.guardar(tasa);
            ra.addFlashAttribute("msgExito","Tasa creada con éxito!");
            mav.setViewName("redirect:/tasas/lista");
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("mensaje", "Error al crear la Tasa: " + e.getMessage());
        }
        return mav;
    }
    @PutMapping("/{id}")
    public ResponseEntity<ModelComponentePrecio> actualizartasas(@PathVariable(value = "id") Long idComponentePrecio,
                                                                  @RequestBody ModelComponentePrecio tasaActualizada) throws Exception {
        Optional<ModelComponentePrecio> tasa = Optional.ofNullable(tasasRepo.buscarPorId(idComponentePrecio));
        if (tasa.isPresent()) {
            ModelComponentePrecio tasaExistente = tasa.get();
            tasaExistente.setNombreCP(tasaActualizada.getNombreCP());
            tasaExistente.setPrecioCP(tasaActualizada.getPrecioCP());
            tasaExistente.setIVA(tasaActualizada.getIVA());
            tasaExistente.setTasaAeroportuariaNacional(tasaActualizada.getTasaAeroportuariaNacional());
            tasaExistente.setTasaAeroportuariaInternacional(tasaActualizada.getTasaAeroportuariaInternacional());
            tasaExistente.setCotización(tasaActualizada.getCotización());
            ModelComponentePrecio CPActualizadaGuardada = tasasRepo.guardar(tasaExistente);
            return ResponseEntity.ok(CPActualizadaGuardada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
