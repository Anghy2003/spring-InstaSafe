package com.ista.springboot.web.app.models.services;
<<<<<<< HEAD
=======

>>>>>>> 6628776c0b9cbb333aca2fb5f50754f2b62c62c2

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.web.app.models.dao.IUsuarioDao;
import com.ista.springboot.web.app.models.entity.Usuario;
<<<<<<< HEAD
=======


>>>>>>> 6628776c0b9cbb333aca2fb5f50754f2b62c62c2

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByCedula(String cedula) {
        return usuarioDao.findByCedula(cedula).orElse(null);
    }

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
<<<<<<< HEAD
=======
	

>>>>>>> 6628776c0b9cbb333aca2fb5f50754f2b62c62c2
}



