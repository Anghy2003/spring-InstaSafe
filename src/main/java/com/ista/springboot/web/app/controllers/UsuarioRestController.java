package com.ista.springboot.web.app.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ista.springboot.web.app.models.entity.Rol;
import com.ista.springboot.web.app.models.entity.Usuario;
import com.ista.springboot.web.app.models.services.IUsuarioService;
import com.ista.springboot.web.app.models.services.RolServiceImpl;

@CrossOrigin(origins = {
    "http://spring-instasafe-441403171241.us-central1.run.app",
    "https://spring-instasafe-441403171241.us-central1.run.app",
    "http://localhost:4200"
})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	

    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private RolServiceImpl rolService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(
        @RequestParam("cedula") String cedula,
        @RequestParam("nombre") String nombre,
        @RequestParam("apellido") String apellido,
        @RequestParam("correo") String correo,
        @RequestParam("genero") String genero,
        @RequestParam(value = "idresponsable", required = false) Long idResponsable,
        @RequestParam("fechanacimiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaNacimiento,
        @RequestParam("contrasena") String contrasena,
        @RequestParam("id_rol") Long idRol,
        @RequestParam(value = "foto", required = false) String foto,
        @RequestParam(value = "plantillaFacial", required = false) String plantillaFacial
    ) {
        Usuario usuario = new Usuario();
        usuario.setCedula(cedula);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setGenero(genero);

        if (idResponsable != null) {
            usuario.setIdresponsable(idResponsable);
        }

        usuario.setFechanacimiento(fechaNacimiento);
        usuario.setContrasena(contrasena);
        usuario.setFoto(foto);
        usuario.setPlantillaFacial(plantillaFacial);

        Rol rol = new Rol();
        rol.setId(idRol);
        usuario.setId_rol(rol);

        return usuarioService.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public Usuario show(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @GetMapping("/usuarios")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/usuarios/cedula/{cedula}")
    public Usuario getByCedula(@PathVariable String cedula) {
        return usuarioService.findByCedula(cedula);
    }

    // ✅ Nuevo método para buscar por correo
    @GetMapping("/usuarios/correo/{correo}")
    public Usuario showByCorreo(@PathVariable String correo) {
        return usuarioService.findByCorreo(correo);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
        Usuario usuarioActual = usuarioService.findById(id);

        if (usuarioActual == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Solo se actualizan los campos no nulos
        if (usuario.getNombre() != null) usuarioActual.setNombre(usuario.getNombre());
        if (usuario.getApellido() != null) usuarioActual.setApellido(usuario.getApellido());
        if (usuario.getCorreo() != null) usuarioActual.setCorreo(usuario.getCorreo());
        if (usuario.getFoto() != null) usuarioActual.setFoto(usuario.getFoto());
        if (usuario.getFechanacimiento() != null) usuarioActual.setFechanacimiento(usuario.getFechanacimiento());
        if (usuario.getGenero() != null) usuarioActual.setGenero(usuario.getGenero());
        if (usuario.getIdresponsable() != null) usuarioActual.setIdresponsable(usuario.getIdresponsable());
        if (usuario.getContrasena() != null) usuarioActual.setContrasena(usuario.getContrasena());

        // Actualizar rol si se proporciona
        if (usuario.getId_rol() != null && usuario.getId_rol().getId() != null) {
            Rol rol = rolService.findById(usuario.getId_rol().getId());
            if (rol != null) {
                usuarioActual.setId_rol(rol);
            } else {
                throw new RuntimeException("Rol no encontrado");
            }
        }

        return usuarioService.save(usuarioActual);
    }


    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @GetMapping("/usuarios/plantillas")
    public List<Usuario> obtenerSoloCedulaYPlantilla() {
        return usuarioService.findAll()
            .stream()
            .filter(u -> u.getPlantillaFacial() != null && !u.getPlantillaFacial().isEmpty())
            .map(u -> {
                Usuario usuario = new Usuario();
                usuario.setCedula(u.getCedula());
                usuario.setPlantillaFacial(u.getPlantillaFacial());
                return usuario;
            })
            .toList();
    }
    
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(
        @RequestParam String correo,
        @RequestParam String contrasena
    ) {
        Usuario user = usuarioService.findByCorreoAndContrasena(correo, contrasena);
        if (user != null) {
          return ResponseEntity.ok(user);
        } else {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }  
   
    @PutMapping("/usuarios/foto-google")
    public ResponseEntity<Usuario> updateFotoGoogle(
        @RequestParam String correo,
        @RequestParam String fotoGoogle
    ) {
      Usuario u = usuarioService.actualizarFotoGoogle(correo, fotoGoogle);
      return ResponseEntity.ok(u);
    }
    
}