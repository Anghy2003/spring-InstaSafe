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

import com.ista.springboot.web.app.models.entity.Rol;
import com.ista.springboot.web.app.models.services.IRolService;

@CrossOrigin(origins = {"http://spring-instasafe-441403171241.us-central1.run.app"})
@RestController	
@RequestMapping("/api")
public class RolRestController {

	@Autowired
	private IRolService rolService;

	//listar todos los Rol
	@GetMapping("/roles")
	public List<Rol> findAll() {
		return rolService.findAll();
	}
	
	//buscar un Rol por id
	@GetMapping("/roles/{id}")
	public Rol show(@PathVariable Long id) {
		return rolService.findById(id);	
	}
	
	//guardar  un Rol
	@PostMapping("/roles")
	@ResponseStatus(HttpStatus.CREATED)
	public Rol create(@RequestBody Rol rol) {
		return rolService.save(rol);		
	}
	
	//editar un Rol
	@PutMapping("/roles/{id}")
	public Rol update(@RequestBody Rol rol, @PathVariable Long id) {
		Rol rolActual = rolService.findById(id);		
		rolActual.setNombre(rol.getNombre());
	
		return rolService.save(rolActual);
	}
	
	//eliminar un Rol
	@DeleteMapping("/roles/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		rolService.delete(id);
	}
}
