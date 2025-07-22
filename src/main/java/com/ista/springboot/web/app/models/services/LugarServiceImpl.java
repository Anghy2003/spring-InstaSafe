package com.ista.springboot.web.app.models.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.web.app.models.dao.ILugarDao;
import com.ista.springboot.web.app.models.entity.Lugar;



@Service
public class LugarServiceImpl implements ILugarService {
	
	@Autowired
	private ILugarDao LugarDao;

	@Transactional(readOnly = true)
	public List<Lugar> findAll() {
		// TODO Auto-generated method stub
		return (List<Lugar>) LugarDao.findAll();
	}

	@Override
	@Transactional
	public Lugar save(Lugar lugar) {
		// TODO Auto-generated method stub
		return LugarDao.save(lugar);
	}

	@Override
	@Transactional(readOnly = true)
	public Lugar findById(Long id) {
		// TODO Auto-generated method stub
		return LugarDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		LugarDao.deleteById(id);
		
	}
	

}
