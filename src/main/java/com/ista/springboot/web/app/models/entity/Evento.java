package com.ista.springboot.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String lugar;

    @Column(name = "fechaingreso", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaingreso;

    @Column(name = "fechasalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasalida;

    @PrePersist
    public void prePersist() {
        this.fechaingreso = new Date(); // solo se asigna al crear
        this.fechasalida = null;        // se deja vacía
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario id_usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guardia")
    private Usuario id_guardia;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }
    
    public void setFechaingreso(Date fechaingreso) {
    	this.fechaingreso = fechaingreso;
    }

    // sin setter para evitar modificación
    // público solo si necesitas visualizarla desde otros componentes

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Usuario getId_guardia() {
        return id_guardia;
    }

    public void setId_guardia(Usuario id_guardia) {
        this.id_guardia = id_guardia;
    }
}