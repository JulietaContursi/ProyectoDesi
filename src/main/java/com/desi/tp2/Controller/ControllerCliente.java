package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelCliente;
import com.desi.tp2.Service.ServiceCliente;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ControllerCliente {

    @Autowired
    private ServiceCliente clienteRepository;

    @SneakyThrows
    @GetMapping("/lista")
    public ModelAndView clientes(@Param("dni") Optional<Integer> dni, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView("clientes");
        List<ModelCliente> clientes = clienteRepository.buscarTodo(dni);
        if (clientes.isEmpty()) {
            mav.addObject("mensaje", "No se encontraron clientes con el DNI buscado.");
        } else {
            mav.addObject("clientes", clientes);
        }
        return mav;
    }

    @SneakyThrows
    @GetMapping("/{id}") //buscan un cliente por su id en el clienteRepository y devuelven una ResponseEntity con diferentes códigos de estado dependiendo de si se encontró o no el cliente.
    public ResponseEntity<ModelCliente> obtenerClientePorId(@PathVariable(value = "id") Long idCliente) {
        Optional<ModelCliente> cliente = Optional.ofNullable(clienteRepository.buscarPorId(idCliente));
        //Se crea un Optional que envuelve el resultado de la búsqueda de un cliente por su id en el clienteRepository. Si el cliente existe, se guarda en el Optional; de lo contrario, el Optional estará vacío.
        return cliente.map(modelCliente -> ResponseEntity.ok().body(modelCliente)).orElseGet(() -> ResponseEntity.notFound().build());
        //Se utiliza el método map() de Optional para realizar una operación si el Optional contiene un valor. En este caso, si el Optional tiene un cliente, se crea una ResponseEntity con el código de estado OK y el cuerpo del mensaje es el modelo del cliente encontrado.
    }
    @GetMapping("/nuevo")
    ModelAndView nuevo(){
        return new ModelAndView("crearCliente")
                .addObject("cliente" , new ModelCliente());
    }

    @PostMapping("/nuevo")
    public ModelAndView crear(ModelCliente cliente, RedirectAttributes ra) {
        ModelAndView mav = new ModelAndView();
        try {
            clienteRepository.guardar(cliente);
            ra.addFlashAttribute("msgExito","Cliente creado con éxito!");
            mav.setViewName("redirect:/clientes/lista");
        } catch (Exception e) {
            mav.setViewName("error");
            mav.addObject("mensaje", "Error al crear el cliente: " + e.getMessage());
        }
        return mav;
    }
    @PutMapping("/{id}")
    public ResponseEntity<ModelCliente> actualizarCliente(@PathVariable(value = "id") Long idCliente,
                                                        @RequestBody ModelCliente clienteActualizado) throws Exception {
        Optional<ModelCliente> cliente = Optional.ofNullable(clienteRepository.buscarPorId(idCliente));
        if (cliente.isPresent()) {
            ModelCliente clienteExistente = cliente.get();
            clienteExistente.setApellidoNombre(clienteActualizado.getApellidoNombre());
            clienteExistente.setApellidoNombre(clienteActualizado.getApellidoNombre());
            clienteExistente.setDomicilio(clienteActualizado.getDomicilio());
            clienteExistente.setEmail(clienteExistente.getEmail());
            clienteExistente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
            ModelCliente clienteActualizadoGuardado = clienteRepository.guardar(clienteExistente);
            return ResponseEntity.ok(clienteActualizadoGuardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable(value = "id") Long idCliente) {
        Optional<ModelCliente> cliente = Optional.ofNullable(clienteRepository.buscarPorId(idCliente));
        if (cliente.isPresent()) {
            clienteRepository.borrar(idCliente);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

