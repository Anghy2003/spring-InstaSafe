package com.ista.springboot.web.app.models.services;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.web.app.models.dao.IUsuarioDao;
import com.ista.springboot.web.app.models.entity.Usuario;
import software.amazon.awssdk.services.rekognition.RekognitionClient;


@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao UsuarioDao;

	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) UsuarioDao.findAll();
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return UsuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return UsuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		UsuarioDao.deleteById(id);
		
	}
	
	@Override
	public boolean compararRostro(byte[] imagenEntrada, Long idUsuario) {
		try {
			// Obtener el usuario y su imagen biométrica guardada
			Usuario usuario = UsuarioDao.findById(idUsuario).orElse(null);
			if (usuario == null || usuario.getBiometrico() == null) {
				return false;
			}

			SdkBytes fuente = SdkBytes.fromByteArray(usuario.getBiometrico());
			SdkBytes comparacion = SdkBytes.fromByteArray(imagenEntrada);

			Image sourceImage = Image.builder().bytes(fuente).build();
			Image targetImage = Image.builder().bytes(comparacion).build();

			CompareFacesRequest request = CompareFacesRequest.builder()
				.sourceImage(sourceImage)
				.targetImage(targetImage)
				.similarityThreshold(90F)
				.build();

			try (RekognitionClient rekognitionClient = RekognitionClient.create()) {
				CompareFacesResponse response = rekognitionClient.compareFaces(request);

				// Si encuentra coincidencia superior al threshold, retorna true
				return response.faceMatches().stream().anyMatch(match -> match.similarity() >= 90F);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
