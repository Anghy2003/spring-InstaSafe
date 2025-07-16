package com.ista.springboot.web.app.models.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.web.app.models.dao.IEventoDao;
import com.ista.springboot.web.app.models.entity.Evento;




@Service
public class EventoServiceImpl implements IEventoService {
	
	@Autowired
	private IEventoDao EventoDao;

	@Transactional(readOnly = true)
	public List<Evento> findAll() {
		// TODO Auto-generated method stub
		return (List<Evento>) EventoDao.findAll();
	}

	@Override
	@Transactional
	public Evento save(Evento evento) {
		// TODO Auto-generated method stub
		return EventoDao.save(evento);
	}

	@Override
	@Transactional(readOnly = true)
	public Evento findById(Long id) {
		// TODO Auto-generated method stub
		return EventoDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> obtenerFechasConEventos() {
	    List<Date> fechas = EventoDao.obtenerFechasUnicas();

	    return fechas.stream()
	        .map(fecha -> new java.text.SimpleDateFormat("yyyy-MM-dd").format(fecha))
	        .distinct()
	        .toList(); 
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		EventoDao.deleteById(id);
		
	}

	@Override
	public List<Evento> findByFechaRango(Date inicio, Date fin) {
	    return EventoDao.findByFechaRango(inicio, fin);
	}

	@Override
	public Evento findEventoSinSalidaHoy(Long idUsuario, LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}

}
