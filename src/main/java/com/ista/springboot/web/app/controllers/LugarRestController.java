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

import com.ista.springboot.web.app.models.entity.Lugar;
import com.ista.springboot.web.app.models.services.ILugarService;

@CrossOrigin(origins = {
	    "http://spring-instasafe-441403171241.us-central1.run.app",
	    "https://spring-instasafe-441403171241.us-central1.run.app",
	    "http://localhost:4200"
	})
	@RestController
	@RequestMapping("/api")
public class LugarRestController {

	@Autowired
	private ILugarService LugarService;

	//listar todos los Lugares
	@GetMapping("/lugares")
	public List<Lugar> findAll() {
		return LugarService.findAll();
	}
	
	//buscar un lugar por id
	@GetMapping("/lugares/{id}")
	public Lugar show(@PathVariable Long id) {
		return LugarService.findById(id);	
	}
	
	//guardar  un lugar
	@PostMapping("/lugares")
	@ResponseStatus(HttpStatus.CREATED)
	public Lugar create(@RequestBody Lugar lugares) {
		return LugarService.save(lugares);		
	}
	
	//editar un lugar
	@PutMapping("/lugares/{id}")
	public Lugar update(@RequestBody Lugar lugar, @PathVariable Long id) {
		Lugar lugarActual = LugarService.findById(id);		
		lugarActual.setNombre(lugar.getNombre());
		lugarActual.setFoto(lugar.getFoto());
		return LugarService.save(lugarActual);
	}
	
	//eliminar un lugar
	@DeleteMapping("/lugares/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		LugarService.delete(id);
	}
}
