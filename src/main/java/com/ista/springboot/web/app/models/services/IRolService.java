package com.ista.springboot.web.app.models.services;

import java.util.List;
import com.ista.springboot.web.app.models.entity.Rol;


public interface IRolService {
	public List<Rol> findAll ();
	public Rol save(Rol rol);
	public Rol findById(Long id);
	public void delete(Long id);

}
