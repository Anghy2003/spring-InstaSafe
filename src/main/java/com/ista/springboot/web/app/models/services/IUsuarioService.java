package com.ista.springboot.web.app.models.services;

import java.util.List;


import com.ista.springboot.web.app.models.entity.Usuario;


public interface IUsuarioService {
	public List<Usuario> findAll ();
	public Usuario save(Usuario usuario);
	public Usuario findById(Long id);
	public void delete(Long id);
	public Usuario findByCedula(String cedula);
	public Usuario findByCorreo(String correo);
	public Usuario actualizarPlantillaFacial(Long id, String plantillaFacial);
	public Usuario findByCorreoAndContrasena(String correo, String contrasena);
	public Usuario actualizarFotoGoogle(String correo, String fotoGoogle);
	
}