package com.ista.springboot.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ista.springboot.web.app.models.entity.Auditoria;

public interface IAuditoriaDao extends CrudRepository<Auditoria, Long>{

}
