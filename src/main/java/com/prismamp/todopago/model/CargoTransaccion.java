package com.prismamp.todopago.model;

public class CargoTransaccion {

	private Long idCargo;

	private Double montoCalculado;

	private Double valorAplicado;

	private Long idTipoAplicacion;

	private String codigoAplicacion;

	private String codigoTipoCargo;

	private String idTransaccion;

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
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

	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}

	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}

	public String getCodigoTipoCargo() {
		return codigoTipoCargo;
	}

	public void setCodigoTipoCargo(String codigoTipoCargo) {
		this.codigoTipoCargo = codigoTipoCargo;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

}
