package com.ista.springboot.web.app.models.services;

import java.util.List;


import com.ista.springboot.web.app.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll ();
	public Usuario save(Usuario usuario);
	public Usuario findById(Long id);
	public Usuario findByCedula(String cedula);
	public void delete(Long id);
<<<<<<< HEAD
=======
	


>>>>>>> 6628776c0b9cbb333aca2fb5f50754f2b62c62c2
}
