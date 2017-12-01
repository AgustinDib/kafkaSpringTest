package com.prismamp.todopago.model;

import java.util.Date;

public class CargoRequest {

	private Long idBaseCalculo;

	private Long idCuenta;

	private Long idMedioPago;

	private Long idCanal;

	private Double importe;

	private String idTransaccion;

	private Long idPromotion;

	private Date created;

	public Long getIdBaseCalculo() {
		return idBaseCalculo;
	}

	public void setIdBaseCalculo(Long idBaseCalculo) {
		this.idBaseCalculo = idBaseCalculo;
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Long getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(Long idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public Long getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(Long idCanal) {
		this.idCanal = idCanal;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Long getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(Long idPromotion) {
		this.idPromotion = idPromotion;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
