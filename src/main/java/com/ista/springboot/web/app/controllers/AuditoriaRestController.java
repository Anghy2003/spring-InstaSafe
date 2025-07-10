package com.ista.springboot.web.app.controllers;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ista.springboot.web.app.models.entity.Auditoria;
import com.ista.springboot.web.app.models.services.IAuditoriaService;



@CrossOrigin(origins = {
	    "http://spring-instasafe-441403171241.us-central1.run.app",
	    "https://spring-instasafe-441403171241.us-central1.run.app"
	})
	@RestController
	@RequestMapping("/api")
public class AuditoriaRestController {
	
	@Autowired
	private IAuditoriaService AuditoriaService;

	//listar todos los Auditoria
	@GetMapping("/auditorias")
	public List<Auditoria> findAll() {
		return AuditoriaService.findAll();
	}
	
	//buscar un Auditoria por id
	@GetMapping("/auditorias/{id}")
	public Auditoria show(@PathVariable Long id) {
		return AuditoriaService.findById(id);	
	}
	
	//guardar  un Auditoria
	@PostMapping("/auditorias")
	@ResponseStatus(HttpStatus.CREATED)
	public Auditoria create(@RequestBody Auditoria auditoria) {
		return AuditoriaService.save(auditoria);		
	}
	
	//editar un Auditoria
	@PutMapping("/auditorias/{id}")
	public Auditoria update(@RequestBody Auditoria auditoria, @PathVariable Long id) {
		Auditoria auditoriaActual = AuditoriaService.findById(id);		
		auditoriaActual.setDescripcion(auditoria.getDescripcion());
		auditoriaActual.setEvento(auditoria.getEvento());
		auditoriaActual.setFecha(auditoria.getFecha());
		return AuditoriaService.save(auditoriaActual);
	}
	
	//eliminar un auditoria
	@DeleteMapping("/auditorias/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		AuditoriaService.delete(id);
	}

}
