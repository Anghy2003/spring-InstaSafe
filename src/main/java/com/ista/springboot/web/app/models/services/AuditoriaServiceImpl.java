package com.ista.springboot.web.app.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.web.app.models.dao.IAuditoriaDao;
import com.ista.springboot.web.app.models.entity.Auditoria;




@Service
public class AuditoriaServiceImpl implements IAuditoriaService {
	
	@Autowired
	private IAuditoriaDao AuditoriaDao;

	@Transactional(readOnly = true)
	public List<Auditoria> findAll() {
		// TODO Auto-generated method stub
		return (List<Auditoria>) AuditoriaDao.findAll();
	}

	@Override
	@Transactional
	public Auditoria save(Auditoria auditoria) {
		// TODO Auto-generated method stub
		return AuditoriaDao.save(auditoria);
	}

	@Override
	@Transactional(readOnly = true)
	public Auditoria findById(Long id) {
		// TODO Auto-generated method stub
		return AuditoriaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		AuditoriaDao.deleteById(id);
		
	}

}
