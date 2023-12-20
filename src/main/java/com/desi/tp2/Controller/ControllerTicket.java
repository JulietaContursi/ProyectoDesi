package com.desi.tp2.Controller;
import com.desi.tp2.Model.ModelTicket;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
    public class ControllerTicket {


    @Autowired
    private ServiceCiudad ciudadService; //innecesario
    @Autowired
    private ServiceVuelo vueloRepository;
    @Autowired
    private ServiceAsiento asientoRepository;
    @Autowired
    private ServiceCliente clienteRepository;
    @Autowired
    private ServiceTicket ticketRepository;


    @GetMapping("/lista")
    public ModelAndView mostrarTickets() throws Exception {
        ModelAndView mav = new ModelAndView("Tickets");
        mav.addObject("tickets", ticketRepository.buscarTodo());
        return mav;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ModelTicket> obtenerTicketPorId(@PathVariable(value = "id") Long idTicket) throws Exception {
        Optional<ModelTicket> ticket = Optional.ofNullable(ticketRepository.buscarPorId(idTicket));
        return ticket.map(modelTicket -> ResponseEntity.ok().body(modelTicket)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/crearTicket/{idVuelo}")
    public ModelAndView seleccionarAsiento(@PathVariable("idVuelo") int idVuelo, ModelAndView mav) throws Exception {

        mav.addObject("vuelo", vueloRepository.buscarPorId(idVuelo));
        mav.addObject("asientosDisponibles", vueloRepository.buscarAsientoLibres());
        mav.addObject("listaDeClientes", clienteRepository.buscarTodo());
        return mav;
    }

    @PostMapping("/nuevo")
    public ModelAndView enviarForm(ModelTicket ticket, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            ticketRepository.guardar(ticket);
            ra.addFlashAttribute("msgExito","Ticket creado con éxito!");
            mav.setViewName("redirect:/tickets/lista");
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("mensaje", "Error al crear el ticket: " + e.getMessage());
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
