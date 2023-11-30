package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelCP;
import com.desi.tp2.Service.ServiceTasa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RestController
@RequestMapping("/tasas")
public class ControllerTasas {

    @Autowired
    private ServiceTasa tasasRepo;

    @GetMapping("/lista")
    public ModelAndView tasas() throws Exception {
        ModelAndView mav = new ModelAndView("tasas");
        mav.addObject("tasas", tasasRepo.buscarTodo());
        return mav;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ModelCP> obtenerTasaPorId(@PathVariable(value = "id") Long idComponentePrecio) throws Exception {
        Optional<ModelCP> tasa = Optional.ofNullable(tasasRepo.buscarPorId(idComponentePrecio));
        return tasa.map(modelComponentePrecio -> ResponseEntity.ok().body(modelComponentePrecio)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable(value = "id") Long idComponentePrecio) throws Exception {
        ModelCP tasa = tasasRepo.buscarPorId(idComponentePrecio);
        return new ModelAndView("editarTasas")
                .addObject("tasa", tasa);
    }
    /*
    @PostMapping("/nueva")
    public ModelAndView crear(ModelCP tasa, RedirectAttributes ra) {
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
    }*/

    @PostMapping("/editar/{id}")
    public ModelAndView actualizart(@PathVariable Long id,
                                @ModelAttribute("tasa") ModelCP tasaActualizada,
                                Model model, RedirectAttributes ra) throws Exception {
            ModelCP tasaExistente = tasasRepo.buscarPorId(id);
            tasaExistente.setNombreCP(tasaActualizada.getNombreCP());
            tasaExistente.setPrecioCP(tasaActualizada.getPrecioCP());
            tasaExistente.setIVA(tasaActualizada.getIVA());
            tasaExistente.setTasaAN(tasaActualizada.getTasaAN());
            tasaExistente.setTasaAI(tasaActualizada.getTasaAI());
            tasaExistente.setCotizacion(tasaActualizada.getCotizacion());
            ModelAndView mav = new ModelAndView("tasas");


        try {
            tasasRepo.actualizar(tasaExistente, id);

            mav.addObject("tasas", tasasRepo.buscarTodo());
            ra.addFlashAttribute("msgExito","Tasa modificada con éxito!");
            mav.setViewName("redirect:/tasas/lista");
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("mensaje", "Error al crear la Tasa: " + e.getMessage());
        }
        return mav;
    }


}
