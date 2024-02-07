package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Service.ServiceCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


        @GetMapping("/lista")
        public ModelAndView ciudades() throws Exception {
            ModelAndView mav = new ModelAndView("ciudades");
            mav.addObject("ciudades", ciudadRepository.buscarTodo());
            return mav;
        }


        @GetMapping("/{id}")
        public ResponseEntity<ModelCiudad> obtenerCiudadPorId(@PathVariable(value = "id") Long idCiudad) throws Exception {
            Optional<ModelCiudad> ciudad = Optional.ofNullable(ciudadRepository.buscarPorId(idCiudad));
            return ciudad.map(modelCiudad -> ResponseEntity.ok().body(modelCiudad)).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @GetMapping("/nuevo")
        public ModelAndView nuevo(){
            ModelAndView mav = new ModelAndView("crearCiudad");
                mav.addObject("ciudad" , new ModelCiudad());
                mav.addObject("titulo", "Crear Ciudad");
            return mav;
        }

        @PostMapping("/nuevo")
        public ModelAndView crear(ModelCiudad ciudad, RedirectAttributes ra) {
            ModelAndView mav = new ModelAndView();
            try {
                if(ciudad.getIdCiudad() != 0){
                    ra.addFlashAttribute("msgExito", "Ciudad modificada con éxito!");
                    mav.setViewName("redirect:/ciudades/lista");
                }else{
                    ra.addFlashAttribute("msgExito", "Ciudad creada con éxito!");
                    mav.setViewName("redirect:/ciudades/lista");
                }
                ciudadRepository.guardar(ciudad);
            } catch (Exception e) {
                mav.setViewName("error");
                mav.addObject("mensaje", "Error al crear la ciudad: " + e.getMessage());
            }
            return mav;
        }


        @GetMapping("/editar/{id}")
        public ModelAndView editarForm(@PathVariable long id, ModelAndView mav) throws Exception {
                ModelCiudad ciudad = ciudadRepository.buscarPorId(id);
                mav.addObject("ciudad", ciudad);
                mav.addObject("titulo","Editar Ciudad");
                mav.setViewName("crearCiudad");
            return mav;
        }

        @GetMapping("/eliminar/{id}")
        public ModelAndView deleteCity(@PathVariable Long id, RedirectAttributes ra) {
            ModelAndView mav = new ModelAndView();
            try{
                ciudadRepository.borrar(id);
                ra.addFlashAttribute("msgExito","Ciudad eliminada con éxito!");
                mav.setViewName("redirect:/ciudades/lista");
            }catch (Exception e) {
                ra.addFlashAttribute("msgError", "No se puede borrar una ciudad si está siendo utilizada por otro registro. " );
                mav.setViewName("redirect:/ciudades/lista");
                mav.addObject("errorCode", e.getMessage()); // para agregar a un log
            }
            return mav;
        }

    }
