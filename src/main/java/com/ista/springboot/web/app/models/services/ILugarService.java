package com.ista.springboot.web.app.models.services;

import java.util.List;

import com.ista.springboot.web.app.models.entity.Lugar;


public interface ILugarService {
	public List<Lugar> findAll ();
	public Lugar save(Lugar lugar);
	public Lugar findById(Long id);
	public void delete(Long id);

}
