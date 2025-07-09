package com.ista.springboot.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;

    private String foto; 

    private String genero;
    private Long idresponsable;
    private Date fechanacimiento;
    private String contrasena;

    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;

    @PrePersist
    public void prePersist() {
        fecharegistro = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Rol id_rol;

    // 🧩 Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Long getIdresponsable() { return idresponsable; }
    public void setIdresponsable(Long idresponsable) { this.idresponsable = idresponsable; }

    public Date getFechanacimiento() { return fechanacimiento; }
    public void setFechanacimiento(Date fechanacimiento) { this.fechanacimiento = fechanacimiento; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Date getFecharegistro() { return fecharegistro; }
    public void setFecharegistro(Date fecharegistro) { this.fecharegistro = fecharegistro; }

    public Rol getId_rol() { return id_rol; }
    public void setId_rol(Rol id_rol) { this.id_rol = id_rol; }
}