package com.ista.springboot.web.app.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.web.app.models.dao.IRolDao;
import com.ista.springboot.web.app.models.entity.Rol;



@Service
public class RolServiceImpl implements IRolService {
	
	@Autowired
	private IRolDao RolDao;

	@Transactional(readOnly = true)
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		return (List<Rol>) RolDao.findAll();
	}

	@Override
	@Transactional
	public Rol save(Rol rol) {
		// TODO Auto-generated method stub
		return RolDao.save(rol);
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findById(Long id) {
		// TODO Auto-generated method stub
		return RolDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		RolDao.deleteById(id);
		
	}
	

}
