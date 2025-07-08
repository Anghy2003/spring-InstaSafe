package com.ista.springboot.web.app.controllers;



import com.ista.springboot.web.app.models.entity.Usuario;
import com.ista.springboot.web.app.models.services.FaceRecognitionService;
import com.ista.springboot.web.app.models.services.IUsuarioService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verificacion")
public class FaceVerificationController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @PostMapping("/verificar")
    public boolean verificarPersona(@RequestBody byte[] imagenActual) {
        List<Usuario> usuarios = usuarioService.findAll();

        for (Usuario usuario : usuarios) {
            if (usuario.getBiometrico() != null) {
                boolean coincide = faceRecognitionService.compararRostros(usuario.getBiometrico(), imagenActual);
                if (coincide) {
                    return true; // Encontró una coincidencia
                }
            }
        }

        return false; // Ninguna coincidencia
    }
}
