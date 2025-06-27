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

import com.ista.springboot.web.app.models.entity.Usuario;
import com.ista.springboot.web.app.models.services.IUsuarioService;



@CrossOrigin(origins = {"http://localhost:4200"})
@RestController	
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;

	//listar todos los Usuarios
	@GetMapping("/usuarios")
	public List<Usuario> findAll() {
		return usuarioService.findAll();
	}
	
	//buscar un Usuario por id
	@GetMapping("/usuarios/{id}")
	public Usuario show(@PathVariable Long id) {
		return usuarioService.findById(id);	
	}
	
	//guardar  un Usuario
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);		
	}
	
	//editar un Usuario
	@PutMapping("/usuarios/{id}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario usuarioActual = usuarioService.findById(id);
		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setApellido(usuario.getApellido());		
		usuarioActual.setCorreo(usuario.getCorreo());
		usuarioActual.setBiometrico(usuario.getBiometrico());
		usuarioActual.setFechanacimiento(usuario.getFechanacimiento());
		usuarioActual.setGenero(usuario.getGenero());
		usuarioActual.setIdresponsable(usuario.getIdresponsable());	
		usuarioActual.setContrasena(usuario.getContrasena());
		return usuarioService.save(usuarioActual);
	}
	
	//eliminar un Usuario
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		usuarioService.delete(id);
	}

}
