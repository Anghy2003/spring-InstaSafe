package com.ista.springboot.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

	@Column(name = "fechaingreso")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaingreso;

	@Column(name = "fechasalida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechasalida;

	// Solo un método @PrePersist permitido
	@PrePersist
	public void prePersist() {
		this.fechaingreso = new Date();
		this.fechasalida = new Date(); // o puedes dejarla null si solo se llena al salir
	}

	// Relación con la tabla usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario id_usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Guardia")
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
