package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelTicket;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Model.ModelVuelo.estadoVuelo;
import com.desi.tp2.Model.ModelVuelo.tipoVuelo;
import com.desi.tp2.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Tickets")
    public class ControllerTicket {


    @Autowired
    private ServiceCiudad ciudadService;
    @Autowired
    private ServiceVuelo vueloRepository;
    @Autowired
    private ServiceAvion avionRepository;
    @Autowired
    private ServiceCliente clienteRepository;
    /*
    @Autowired
    private ServiceTicket ticketRepository;

    @GetMapping("/")
    public String mostrarTickets(Model model) throws Exception {
        List<ModelTicket> tickets = ticketRepository.buscarTodo();
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/lista")
    public ModelAndView tickets() throws Exception {
            ModelAndView mav = new ModelAndView("tickets");
            mav.addObject("tickets", ticketRepository.buscarTodo());
         return mav;
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<ModelVuelo> obtenerVueloPorId(@PathVariable(value = "id") Long idVuelo) {
        Optional<ModelVuelo> vuelo = Optional.ofNullable(vueloRepository.buscarPorId(idVuelo));
        return vuelo.map(modelVuelo -> ResponseEntity.ok().body(modelVuelo)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nuevo")
    ModelAndView nuevoForm() throws Exception {

        return new ModelAndView("crearVuelo")
                .addObject("vuelo", new ModelVuelo())
                .addObject("listaDeCiudades", ciudadService.buscarTodo())
                .addObject("tiposDeVuelos", tipoVuelo.values())
                .addObject("estadosDeVuelos", estadoVuelo.values())
                .addObject("listaDeAviones", avionRepository.buscarTodo());
    }

    /*@GetMapping("/nuevo")
    public String registrarVueloForm(Model modelo) throws Exception {
        ModelVuelo vuelo = new ModelVuelo();

        modelo.addAttribute("vuelo", vuelo);
        modelo.addAttribute("listaDeCiudades", ciudadService.buscarTodo());
        modelo.addAttribute("listaDeAviones", avionRepository.buscarTodo());
        return "crearVuelo";
    }*/

    @PostMapping("/nuevo")
    public ModelAndView enviarForm(ModelVuelo vuelo, RedirectAttributes ra) {
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
            vueloExistente.setFecha(vueloActualizado.getFecha());
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
            ciudadService.borrar(idVuelo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
