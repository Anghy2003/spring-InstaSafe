package com.ista.springboot.web.app.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ista.springboot.web.app.models.entity.Usuario;
import com.ista.springboot.web.app.models.entity.Rol;
import com.ista.springboot.web.app.models.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"}) // 💡 Ajusta según tu frontend
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    // 🟢 Listar todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    // 🔍 Buscar un usuario por ID
    @GetMapping("/usuarios/{id}")
    public Usuario show(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    // 🆕 Crear usuario vía Multipart/form-data desde Flutter
    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(
        @RequestParam("cedula") String cedula,
        @RequestParam("nombre") String nombre,
        @RequestParam("apellido") String apellido,
        @RequestParam("correo") String correo,
        @RequestParam("genero") String genero,
        @RequestParam("idresponsable") Long idResponsable,
        @RequestParam("fechanacimiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaNacimiento,
        @RequestParam("contrasena") String contrasena,
        @RequestParam("id_rol") Long idRol,
        @RequestParam("biometrico") MultipartFile biometrico
    ) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setCedula(cedula);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setGenero(genero);
        usuario.setIdresponsable(idResponsable);
        usuario.setFechanacimiento(fechaNacimiento);
        usuario.setContrasena(contrasena);
        usuario.setBiometrico(biometrico.getBytes());

        // Crea el Rol con solo el ID si no usas relación directa
        Rol rol = new Rol();
        rol.setId(idRol);
        usuario.setId_rol(rol);

        return usuarioService.save(usuario);
    }

    // ✏️ Editar un usuario
    @PutMapping("/usuarios/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
        Usuario usuarioActual = usuarioService.findById(id);
        usuarioActual.setNombre(usuario.getNombre());
        usuarioActual.setApellido(usuario.getApellido());
        usuarioActual.setCorreo(usuario.getCorreo());
        usuarioActual.setBiometrico(usuario.getBiometrico());
        usuarioActual.setFechanacimiento(usuario.getFechanacimiento());
        usuarioActual.setGenero(usuario.getGenero());
        usuarioActual.setIdresponsable(usuario.getIdresponsable());
        usuarioActual.setContrasena(usuario.getContrasena());
        return usuarioService.save(usuarioActual);
    }

    // ❌ Eliminar usuario
    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}