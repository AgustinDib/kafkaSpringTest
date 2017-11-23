package com.prismamp.todopago.model;

public class CargoTransaccion {

	private Long idCargo;

	private Long idTransaccion;

	private Double montoCalculado;

	private Double valorAplicado;

	private Long idTipoAplicacion;

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public Long getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Double getMontoCalculado() {
		return montoCalculado;
	}

	public void setMontoCalculado(Double montoCalculado) {
		this.montoCalculado = montoCalculado;
	}

	public Double getValorAplicado() {
		return valorAplicado;
	}

	public void setValorAplicado(Double valorAplicado) {
		this.valorAplicado = valorAplicado;
	}

	public Long getIdTipoAplicacion() {
		return idTipoAplicacion;
	}

	public void setIdTipoAplicacion(Long idTipoAplicacion) {
		this.idTipoAplicacion = idTipoAplicacion;
	}
}
