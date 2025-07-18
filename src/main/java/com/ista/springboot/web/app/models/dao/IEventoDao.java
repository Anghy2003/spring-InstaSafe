package com.ista.springboot.web.app.models.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ista.springboot.web.app.models.entity.Evento;

public interface IEventoDao extends CrudRepository<Evento, Long> {

    /** Busca eventos en un rango de fechaIngreso */
    @Query("SELECT e FROM Evento e " +
           "WHERE e.fechaingreso BETWEEN :inicio AND :fin")
    List<Evento> findByFechaRango(
        @Param("inicio") Date inicio,
        @Param("fin")    Date fin
    );

    /** Devuelve fechas únicas (solo día) en que hay eventos */
    @Query("SELECT DISTINCT FUNCTION('DATE', e.fechaingreso) FROM Evento e")
    List<Date> obtenerFechasUnicas();

    /**
     * Busca el evento SIN fechaSalida para un usuario en el día dado.
     * Retorna Optional.empty() si no hay ninguno.
     */
    @Query("SELECT e FROM Evento e " +
           "WHERE e.id_usuario.id     = :idUsuario " +
           "  AND e.fechaingreso      >= :inicio " +
           "  AND e.fechaingreso      <  :fin " +
           "  AND e.fechasalida       IS NULL")
    Optional<Evento> findEventoSinSalidaHoy(
        @Param("idUsuario") Long idUsuario,
        @Param("inicio")    Date inicio,
        @Param("fin")       Date fin
    );
}