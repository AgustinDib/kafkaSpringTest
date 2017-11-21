package com.prismamp.todopago.model;

public class CanalAdhesion {

	private Long id;

	private String nombre;

	private String nivelRiesgo;

	private Boolean permitePromocion;

	private Boolean permiteDevolucion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNivelRiesgo() {
		return nivelRiesgo;
	}

	public void setNivelRiesgo(String nivelRiesgo) {
		this.nivelRiesgo = nivelRiesgo;
	}

	public Boolean getPermitePromocion() {
		return permitePromocion;
	}

	public void setPermitePromocion(Boolean permitePromocion) {
		this.permitePromocion = permitePromocion;
	}

	public Boolean getPermiteDevolucion() {
		return permiteDevolucion;
	}

	public void setPermiteDevolucion(Boolean permiteDevolucion) {
		this.permiteDevolucion = permiteDevolucion;
	}
}
