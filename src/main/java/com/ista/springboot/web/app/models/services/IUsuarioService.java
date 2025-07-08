package com.ista.springboot.web.app.models.services;

import java.util.List;


import com.ista.springboot.web.app.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll ();
	public Usuario save(Usuario usuario);
	public Usuario findById(Long id);
	public void delete(Long id);
	public boolean compararRostro(byte[] imagenEntrada, Long idUsuario);


}
