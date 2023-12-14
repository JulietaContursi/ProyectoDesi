package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelVuelo.tipoVuelo;
import com.desi.tp2.Model.ModelVuelo.estadoVuelo;
import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Service.ServiceAvion;
import com.desi.tp2.Service.ServiceCiudad;
import com.desi.tp2.Service.ServiceCliente;
import com.desi.tp2.Service.ServiceVuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vuelos")
    public class ControllerVuelo {


    @Autowired
    private ServiceCiudad ciudadService;
    @Autowired
    private ServiceVuelo vueloRepository;
    @Autowired
    private ServiceAvion avionRepository;
    @Autowired
    private ServiceCliente clienteRepository;


    @GetMapping("/lista")
    public ModelAndView vuelos(@RequestParam("fecha")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> fechaOpt,
                               RedirectAttributes ra) throws Exception {
        ModelAndView mav = new ModelAndView("vuelos");
        if (fechaOpt.isPresent()) {
            int restantes = 0;
        	LocalDate fecha = fechaOpt.get();
            List<ModelVuelo> vuelos;
            vuelos = vueloRepository.ordenarPorFechaHora(vueloRepository.findVuelosByFecha(Optional.of(fecha)));
            vuelos.sort(null);
            if (vuelos.isEmpty()) {
                mav.addObject("msgError", "No se encontraron vuelos para esta fecha.");
            } else {
            	mav.addObject("vuelos", vuelos);
                mav.addObject("cantidadAsientosVendidos", vueloRepository.buscarAsientoLibres());
                mav.addObject("restantes", restantes);	
            }
        } else {
        	int restantes = 0;
            List<ModelVuelo> vuelos;
            vuelos = vueloRepository.ordenarPorFechaHora(vueloRepository.buscarTodo());
            mav.addObject("vuelos", vuelos);
            mav.addObject("cantidadAsientosVendidos", vueloRepository.buscarAsientoLibres());
            mav.addObject("restantes", restantes);
        }
        return mav;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelVuelo> obtenerVueloPorId(@PathVariable(value = "id") Long idVuelo) throws Exception {
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

    @PostMapping("/nuevo")
    public ModelAndView enviarForm(ModelVuelo vuelo, RedirectAttributes ra) throws Exception {
        ModelAndView mav = new ModelAndView();
        Optional<LocalDate> fecha = Optional.ofNullable(vuelo.getFecha());
        Optional<ModelAvion> avion = Optional.ofNullable(vuelo.getAvion());
        List<ModelVuelo> vuelos = vueloRepository.findVuelosByFechaAndAvion(fecha, avion);
        // obtenemos la cantidad de asientos del avion y lo guardamos en el vuelo
        try {
            if(vuelos.isEmpty()){
            	vuelo.setAsientosDeAvion(vueloRepository.calcularCantidadAsientos(avion));
            	vueloRepository.guardar(vuelo);
                ra.addFlashAttribute("msgExito","Vuelo creado con éxito!");
                mav.setViewName("redirect:/vuelos/lista");
            }else{
                ra.addFlashAttribute("msgError", "No se puede creaer éste vuelo porque el avión ya se utiliza ese día. ");
                mav.setViewName("redirect:/vuelos/lista");
            }
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("msgError", "Error al crear el vuelo: " + e.getMessage());
        }
        return mav;
    }
    
    @PostMapping("/venderAsiento/{idVuelo}")
    public ModelAndView seleccionarAsiento(@PathVariable("idVuelo") int idVuelo) throws Exception {
        ModelAndView mav = new ModelAndView();
        ModelVuelo vuelo = vueloRepository.buscarPorId(idVuelo);

        mav.addObject("vuelo", vuelo);
        mav.addObject("asientosDisponibles", vueloRepository.buscarAsientoLibres());
        mav.addObject("listaDeClientes", clienteRepository.buscarTodo());
        mav.setViewName("redirect:/venderAsiento");
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

    @GetMapping("/eliminar/{id}")
    public ModelAndView deleteVuelo(@PathVariable Long id, RedirectAttributes ra) throws Exception {
        ModelAndView mav = new ModelAndView();
        try{
            vueloRepository.borrar(id);
            ra.addFlashAttribute("msgExito","Vuelo eliminado con éxito!");
            mav.setViewName("redirect:/vuelos/lista");
        }catch (Exception e) {
            ra.addFlashAttribute("msgError", "No se puede borrar éste vuelo si está siendo utilizado por otro registro. " );
            mav.setViewName("redirect:/vuelos/lista");
            mav.addObject("errorCode", e.getMessage()); // para agregar a un log
        }
        return mav;
    }
    
}
