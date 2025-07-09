package com.ista.springboot.web.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ista.springboot.web.app.models.entity.Usuario;


public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	Optional<Usuario> findByCedula(String cedula);

}
