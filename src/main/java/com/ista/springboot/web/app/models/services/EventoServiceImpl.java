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
    private IEventoDao eventoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        return (List<Evento>) eventoDao.findAll();
    }

    @Override
    @Transactional
    public Evento save(Evento evento) {
        return eventoDao.save(evento);
    }

    @Override
    @Transactional(readOnly = true)
    public Evento findById(Long id) {
        return eventoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> obtenerFechasConEventos() {
        List<Date> fechas = eventoDao.obtenerFechasUnicas();
        return fechas.stream()
            .map(f -> new java.text.SimpleDateFormat("yyyy-MM-dd").format(f))
            .distinct()
            .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        eventoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> findByFechaRango(Date inicio, Date fin) {
        return eventoDao.findByFechaRango(inicio, fin);
    }

    @Override
    @Transactional(readOnly = true)
    public Evento findEventoSinSalidaHoy(Long idUsuario, LocalDate fecha) {
        // Convertimos el LocalDate recibido a java.sql.Date
        Date fechaInicio = Date.valueOf(fecha);
        Date fechaFin    = Date.valueOf(fecha.plusDays(1));
        return eventoDao
            .findFirstByIdUsuarioIdAndFechaingresoBetweenAndFechasalidaIsNull(
                idUsuario, fechaInicio, fechaFin
            )
            .orElse(null);
    }
}