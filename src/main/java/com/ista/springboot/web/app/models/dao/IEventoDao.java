package com.ista.springboot.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ista.springboot.web.app.models.entity.Evento;


public interface IEventoDao extends CrudRepository<Evento, Long>{

}
