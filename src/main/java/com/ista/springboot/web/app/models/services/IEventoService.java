package com.ista.springboot.web.app.models.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.ista.springboot.web.app.models.entity.Evento;


public interface IEventoService {
	public List<Evento> findAll ();
	public Evento save(Evento evento);
	public Evento findById(Long id);
	public void delete(Long id);
	public List<Evento> findByFechaRango(Date inicio, Date fin);
	public List<String> obtenerFechasConEventos();
	Evento findEventoSinSalidaHoy(Long idUsuario, LocalDate fecha);

}
