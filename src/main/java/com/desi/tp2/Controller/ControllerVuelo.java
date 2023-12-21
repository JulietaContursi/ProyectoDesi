package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelVuelo.tipoVuelo;
import com.desi.tp2.Model.ModelVuelo.estadoVuelo;
import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Service.ServiceAvion;
import com.desi.tp2.Service.ServiceCiudad;
import com.desi.tp2.Service.ServiceVuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


	@GetMapping("/lista")
	public ModelAndView vuelos(
			@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> fechaOpt) throws Exception {
		ModelAndView mav = new ModelAndView("vuelos");
		int restantes = 0;
		if (fechaOpt.isPresent()) {
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
				mav.addObject("fecha", fecha);
			}
		} else {
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
		return vuelo.map(modelVuelo -> ResponseEntity.ok().body(modelVuelo))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/nuevo")
	ModelAndView nuevoForm() throws Exception {

		return new ModelAndView("crearVuelo").addObject("vuelo", new ModelVuelo())
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
		try {
			if (vuelos.isEmpty()) {
				vuelo.setAsientosDeAvion(vueloRepository.calcularCantidadAsientos(avion));
				if (vuelo.getIdVuelo() != 0) { //mensaje al crear un vuelo
					ra.addFlashAttribute("msgExito", "Vuelo Modificado con éxito!");
					mav.setViewName("redirect:/vuelos/lista");
				} else {					//mensaje al modificar un vuelo
					ra.addFlashAttribute("msgExito", "Vuelo creado con éxito!");
					mav.setViewName("redirect:/vuelos/lista");
				}
				vueloRepository.guardar(vuelo);
			} else {
				ra.addFlashAttribute("msgError",
						"No se puede crear éste vuelo porque el avión ya se utiliza ese día. ");
				mav.setViewName("redirect:/vuelos/lista");
			}
		} catch (Exception e) {
			mav.setViewName("error");
			mav.addObject("msgError", "Error al crear el vuelo: " + e.getMessage());
		}
		return mav;
	}

	@GetMapping("/editar/{id}")
	public String editarForm(@PathVariable long id, Model mav) throws Exception {
		ModelVuelo vuelo = vueloRepository.buscarPorId(id);

		mav.addAttribute("vuelo", vuelo );
		mav.addAttribute("listaDeCiudades",ciudadService.buscarTodo());
		mav.addAttribute("listaDeAviones", avionRepository.buscarTodo());
		mav.addAttribute("estadosDeVuelos", estadoVuelo.values());
		mav.addAttribute("tiposDeVuelos", tipoVuelo.values());
		return "crearVuelo";
	}

	@GetMapping("/eliminar/{id}")
	public ModelAndView deleteVuelo(@PathVariable Long id, RedirectAttributes ra) {
		ModelAndView mav = new ModelAndView();
		try {
			vueloRepository.borrar(id);
			ra.addFlashAttribute("msgExito", "Vuelo eliminado con éxito!");
			mav.setViewName("redirect:/vuelos/lista");
		} catch (Exception e) {
			ra.addFlashAttribute("msgError",
					"No se puede borrar éste vuelo si está siendo utilizado por otro registro. ");
			mav.setViewName("redirect:/vuelos/lista");
			mav.addObject("errorCode", e.getMessage()); // para agregar a un log
		}
		return mav;
	}

}
