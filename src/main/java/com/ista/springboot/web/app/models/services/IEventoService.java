package com.ista.springboot.web.app.models.services;

import java.util.List;

import com.ista.springboot.web.app.models.entity.Evento;


public interface IEventoService {
	public List<Evento> findAll ();
	public Evento save(Evento evento);
	public Evento findById(Long id);
	public void delete(Long id);

}
