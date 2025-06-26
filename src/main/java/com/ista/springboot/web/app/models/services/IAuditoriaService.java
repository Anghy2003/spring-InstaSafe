package com.ista.springboot.web.app.models.services;

import java.util.List;

import com.ista.springboot.web.app.models.entity.Auditoria;


public interface IAuditoriaService {
	public List<Auditoria> findAll ();
	public Auditoria save(Auditoria auditoria);
	public Auditoria findById(Long id);
	public void delete(Long id);

}
