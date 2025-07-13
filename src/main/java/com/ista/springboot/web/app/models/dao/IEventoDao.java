package com.ista.springboot.web.app.models.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ista.springboot.web.app.models.entity.Evento;

public interface IEventoDao extends CrudRepository<Evento, Long> {

    @Query("SELECT e FROM Evento e WHERE e.fechaingreso BETWEEN :inicio AND :fin")
    List<Evento> findByFechaRango(@Param("inicio") Date inicio, @Param("fin") Date fin);
    
    @Query("SELECT DISTINCT DATE(e.fechaingreso) FROM Evento e")
    List<Date> obtenerFechasUnicas();
}