package com.ista.springboot.web.app.models.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import com.ista.springboot.web.app.models.entity.Evento;

public interface IEventoService {
    List<Evento> findAll();
    Evento save(Evento evento);
    Evento findById(Long id);
    void delete(Long id);
    List<Evento> findByFechaRango(Date inicio, Date fin);
    List<String> obtenerFechasConEventos();
    Evento findEventoSinSalidaHoy(Long idUsuario, LocalDate fecha);
}
