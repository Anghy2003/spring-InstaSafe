package com.ista.springboot.web.app.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    
    private final RestTemplate driveRest;
    
    public UsuarioRestController(RestTemplateBuilder builder) {
        this.driveRest = builder.build();
    }

    // --- CRUD de Usuarios ---
    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(
        @RequestParam("cedula") String cedula,
        @RequestParam("nombre") String nombre,
        @RequestParam("apellido") String apellido,
        @RequestParam("correo") String correo,
        @RequestParam("genero") String genero,
        @RequestParam(value = "idresponsable", required = false) Long idResponsable,
        @RequestParam("fechanacimiento") 
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaNacimiento,
        @RequestParam("contrasena") String contrasena,
        @RequestParam("id_rol") Long idRol,
        @RequestParam(value = "foto", required = false) String foto,
        @RequestParam(value = "plantillaFacial", required = false) String plantillaFacial,
        @RequestParam(value = "token", required = false) String token,
        @RequestParam(value = "estado", defaultValue = "true") boolean estado
    ) {
        Usuario usu = new Usuario();
        usu.setCedula(cedula);
        usu.setNombre(nombre);
        usu.setApellido(apellido);
        usu.setCorreo(correo);
        usu.setGenero(genero);
        if (idResponsable != null) {
            usu.setIdresponsable(idResponsable);
        }
        usu.setFechanacimiento(fechaNacimiento);
        usu.setContrasena(contrasena);
        usu.setFoto(foto);
        usu.setPlantillaFacial(plantillaFacial);
        usu.setToken(token);      // guardamos el accessToken
        usu.setEstado(estado);

        Rol rol = new Rol();
        rol.setId(idRol);
        usu.setId_rol(rol);

        return usuarioService.save(usu);
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

    @GetMapping("/usuarios/correo/{correo}")
    public Usuario showByCorreo(@PathVariable String correo) {
        return usuarioService.findByCorreo(correo);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {
        Usuario actual = usuarioService.findById(id);
        if (actual == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        if (usuario.getNombre() != null) actual.setNombre(usuario.getNombre());
        if (usuario.getApellido() != null) actual.setApellido(usuario.getApellido());
        if (usuario.getCorreo() != null) actual.setCorreo(usuario.getCorreo());
        if (usuario.getFoto() != null) actual.setFoto(usuario.getFoto());
        if (usuario.getFechanacimiento() != null) 
            actual.setFechanacimiento(usuario.getFechanacimiento());
        if (usuario.getGenero() != null) actual.setGenero(usuario.getGenero());
        if (usuario.getIdresponsable() != null) 
            actual.setIdresponsable(usuario.getIdresponsable());
        if (usuario.getContrasena() != null) 
            actual.setContrasena(usuario.getContrasena());
        if (usuario.getToken() != null) actual.setToken(usuario.getToken());

        if (usuario.getId_rol() != null 
            && usuario.getId_rol().getId() != null) {
            Rol rol = rolService.findById(usuario.getId_rol().getId());
            if (rol != null) {
                actual.setId_rol(rol);
            } else {
                throw new RuntimeException("Rol no encontrado");
            }
        }

        return usuarioService.save(actual);
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
            .filter(u -> u.getPlantillaFacial() != null 
                      && !u.getPlantillaFacial().isEmpty())
            .map(u -> {
                Usuario tmp = new Usuario();
                tmp.setCedula(u.getCedula());
                tmp.setPlantillaFacial(u.getPlantillaFacial());
                return tmp;
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

    @PostMapping("/usuarios/visitantes")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crearVisitante(
        @RequestParam("nombre") String nombre,
        @RequestParam("apellido") String apellido,
        @RequestParam("id_rol") Long idRol,
        @RequestParam("foto") String foto,
        @RequestParam("plantillaFacial") String plantillaFacial
    ) {
        Rol rol = rolService.findById(idRol);
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setFoto(foto);
        u.setPlantillaFacial(plantillaFacial);
        u.setId_rol(rol);
        return usuarioService.save(u);
    }

    // --- NUEVO ENDPOINT PARA DESCARGAR FOTO DE DRIVE ---
    @GetMapping("/usuarios/{id}/foto")
    public ResponseEntity<byte[]> descargarFotoDrive(@PathVariable Long id) throws IOException {
        Usuario u = usuarioService.findById(id);
        if (u == null || u.getToken() == null || u.getFoto() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 1) Extraer fileId de la URL de Drive:
        Pattern p = Pattern.compile("/d/([a-zA-Z0-9_-]+)");
        Matcher m = p.matcher(u.getFoto());
        if (!m.find()) {
            return ResponseEntity.badRequest().build();
        }
        String fileId = m.group(1);

        // 2) Preparar llamada a Drive REST
        String driveUrl = "https://www.googleapis.com/drive/v3/files/"
                        + fileId
                        + "?alt=media";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(u.getToken());
        HttpEntity<Void> req = new HttpEntity<>(headers);

        // 3) Ejecutar y devolver bytes
        ResponseEntity<byte[]> resp = driveRest.exchange(
            driveUrl,
            HttpMethod.GET,
            req,
            byte[].class
        );

        // 4) Construir respuesta con tipo de contenido
        MediaType contentType = resp.getHeaders()
                                    .getContentType() != null
                                  ? resp.getHeaders().getContentType()
                                  : MediaType.APPLICATION_OCTET_STREAM;
        HttpHeaders out = new HttpHeaders();
        out.setContentType(contentType);
        return new ResponseEntity<>(resp.getBody(), out, HttpStatus.OK);
    }
}
