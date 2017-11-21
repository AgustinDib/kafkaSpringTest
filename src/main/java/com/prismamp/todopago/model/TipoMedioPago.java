package com.prismamp.todopago.model;

public class TipoMedioPago {

	public Long id;

	private String codigo;

	private String nombre;

	private Boolean permiteAnulacion;

	private Boolean permiteDevolucion;

	private Integer plazoDevolucion;

	private Boolean operaCuotas;

	private Boolean permiteContracargo;

	private Boolean permitidoBilletera;

	public TipoMedioPago() {
	}

	public TipoMedioPago(Long id, String codigo, String nombre, Boolean permiteAnulacion, Boolean permiteDevolucion,
			Integer plazoDevolucion, Boolean operaCuotas, Boolean permiteContracargo, Boolean permitidoBilletera) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.permiteAnulacion = permiteAnulacion;
		this.permiteDevolucion = permiteDevolucion;
		this.plazoDevolucion = plazoDevolucion;
		this.operaCuotas = operaCuotas;
		this.permiteContracargo = permiteContracargo;
		this.permitidoBilletera = permitidoBilletera;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getPermiteAnulacion() {
		return permiteAnulacion;
	}

	public void setPermiteAnulacion(Boolean permiteAnulacion) {
		this.permiteAnulacion = permiteAnulacion;
	}

	public Boolean getPermiteDevolucion() {
		return permiteDevolucion;
	}

	public void setPermiteDevolucion(Boolean permiteDevolucion) {
		this.permiteDevolucion = permiteDevolucion;
	}

	public Integer getPlazoDevolucion() {
		return plazoDevolucion;
	}

	public void setPlazoDevolucion(Integer plazoDevolucion) {
		this.plazoDevolucion = plazoDevolucion;
	}

	public Boolean getOperaCuotas() {
		return operaCuotas;
	}

	public void setOperaCuotas(Boolean operaCuotas) {
		this.operaCuotas = operaCuotas;
	}

	public Boolean getPermiteContracargo() {
		return permiteContracargo;
	}

	public void setPermiteContracargo(Boolean permiteContracargo) {
		this.permiteContracargo = permiteContracargo;
	}

	public Boolean getPermitidoBilletera() {
		return permitidoBilletera;
	}

	public void setPermitidoBilletera(Boolean permitidoBilletera) {
		this.permitidoBilletera = permitidoBilletera;
	}
}
