package com.ista.springboot.web.app.controllers;

import java.sql.Date;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ista.springboot.web.app.models.entity.Evento;
import com.ista.springboot.web.app.models.services.IEventoService;





@CrossOrigin(origins = {
	    "http://spring-instasafe-441403171241.us-central1.run.app",
	    "https://spring-instasafe-441403171241.us-central1.run.app"
	})
	@RestController
	@RequestMapping("/api")
public class EventoRestController {

	@Autowired
	private IEventoService eventoService;

	//listar todos los Evento
	@GetMapping("/eventos")
	public List<Evento> findAll() {
		return eventoService.findAll();
	}
	
	//buscar un Evento por id
	@GetMapping("/eventos/{id}")
	public Evento show(@PathVariable Long id) {
		return eventoService.findById(id);	
	}
	
	//buscar por fecha
	@GetMapping("/eventos/filtrar")
	public List<Evento> eventosPorFecha(@RequestParam String fecha) {
	    LocalDate localDate = LocalDate.parse(fecha);

	    // Convertir LocalDate → java.sql.Date
	    Date inicio = Date.valueOf(localDate);
	    Date fin = Date.valueOf(localDate.plusDays(1));

	    return eventoService.findByFechaRango(inicio, fin);
	}

	
	//fechasdisponibles
	@GetMapping("/eventos/fechas-disponibles")
	public List<String> fechasConEventos() {
	    return eventoService.obtenerFechasConEventos();
	}
	
	//guardar  un Evento
	@PostMapping("/eventos")
	@ResponseStatus(HttpStatus.CREATED)
	public Evento create(@RequestBody Evento evento) {
	    Long idUsuario = evento.getId_usuario().getId();
	    LocalDate hoy = LocalDate.now();

	    // 1. Buscar evento existente sin salida para el usuario hoy
	    Evento eventoExistente = eventoService.findEventoSinSalidaHoy(idUsuario, hoy);

	    if (eventoExistente != null) {
	        // 2. Ya tenía ingreso => actualizar solo la fecha de salida
	        eventoExistente.setFechasalida(evento.getFechasalida() != null ? evento.getFechasalida() : java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
	        return eventoService.save(eventoExistente);
	    }

	    // 3. No tenía ingreso hoy => crear evento nuevo (sin salida)
	    evento.setFechaingreso(evento.getFechaingreso() != null ? evento.getFechaingreso() : java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
	    evento.setFechasalida(null); // se llena después
	    return eventoService.save(evento);
	}

	
	//editar un Evento
	@PutMapping("/eventos/{id}")
	public Evento update(@RequestBody Evento evento, @PathVariable Long id) {
		Evento eventoActual = eventoService.findById(id);
		eventoActual.setTitulo(evento.getTitulo());
		eventoActual.setDescripcion(evento.getDescripcion());
		eventoActual.setFechaingreso(evento.getFechaingreso());
		eventoActual.setFechasalida(evento.getFechasalida());
		return eventoService.save(eventoActual);
	}
	
	//eliminar un Evento
	@DeleteMapping("/eventos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		eventoService.delete(id);
	}
	
}
