package com.ista.springboot.web.app.controllers;

//import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import com.ista.springboot.web.app.models.entity.Usuario;
import com.ista.springboot.web.app.models.services.IUsuarioService;

@CrossOrigin(origins = {
	    "http://spring-instasafe-441403171241.us-central1.run.app",
	    "https://spring-instasafe-441403171241.us-central1.run.app"
	})
	@RestController
	@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
    private IUsuarioService usuarioService;
	
	
		@PostMapping("/usuarios")
		@ResponseStatus(HttpStatus.CREATED)
		public Usuario create(@RequestBody Usuario usuario) {
			return usuarioService.save(usuario);		
		}

//	@PostMapping("/usuarios")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Usuario create(
//	    @RequestParam("cedula") String cedula,
//	    @RequestParam("nombre") String nombre,
//	    @RequestParam("apellido") String apellido,
//	    @RequestParam("correo") String correo,
//	    @RequestParam("genero") String genero,
//	    @RequestParam(value = "idresponsable", required = false) Long idResponsable,  
//	    @RequestParam("fechanacimiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaNacimiento,
//	    @RequestParam("contrasena") String contrasena,
//	    @RequestParam("id_rol") Long idRol,
//	    @RequestParam(value = "foto", required = false) String foto,
//	    @RequestParam(value = "plantillaFacial", required = false) String plantillaFacial
//	) {
//	    Usuario usuario = new Usuario();
//	    usuario.setCedula(cedula);
//	    usuario.setNombre(nombre);
//	    usuario.setApellido(apellido);
//	    usuario.setCorreo(correo);
//	    usuario.setGenero(genero);
//
//	    // Solo setear idresponsable si vino en la petición
//	    if (idResponsable != null) {
//	        usuario.setIdresponsable(idResponsable);
//	    }
//
//	    usuario.setFechanacimiento(fechaNacimiento);
//	    usuario.setContrasena(contrasena);
//	    usuario.setFoto(foto);
//	    usuario.setPlantillaFacial(plantillaFacial);
//
//	    Rol rol = new Rol();
//	    rol.setId(idRol);
//	    usuario.setId_rol(rol);
//
//	    return usuarioService.save(usuario);
//	}



    
    @GetMapping("/usuarios/{id}")
    public Usuario show(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    
    @GetMapping("/usuarios")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    
    @GetMapping("/usuarios/cedula/{cedula}")
    public Usuario showByCedula(@PathVariable String cedula) {
        return usuarioService.findByCedula(cedula);
    }

    
    
    
    @PutMapping("/usuarios/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
        Usuario usuarioActual = usuarioService.findById(id);

        usuarioActual.setNombre(usuario.getNombre());
        usuarioActual.setApellido(usuario.getApellido());
        usuarioActual.setCorreo(usuario.getCorreo());
        usuarioActual.setFoto(usuario.getFoto());
        usuarioActual.setFechanacimiento(usuario.getFechanacimiento());
        usuarioActual.setGenero(usuario.getGenero());
        usuarioActual.setIdresponsable(usuario.getIdresponsable());
        usuarioActual.setContrasena(usuario.getContrasena());

        return usuarioService.save(usuarioActual);
    }

    
    @PutMapping("/usuarios/{id}/foto")
    public Usuario updateFoto(@PathVariable Long id, @RequestParam("foto") String foto) {
        Usuario usuarioActual = usuarioService.findById(id);
        usuarioActual.setFoto(foto);
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
                usuario.setCedula(u.getCedula()); // 👈 Solo cedula
                usuario.setPlantillaFacial(u.getPlantillaFacial()); // 👈 Solo plantilla
                return usuario;
            })
            .toList();
    }

}