package com.prismamp.todopago.model;

public class ReglaBonificacion {

	private Boolean tasaDirecta;

	private TasaMedioPago tasaMedioPago;

	private Double tasaDirectaIngresada;

	public Boolean isTasaDirecta() {
		return tasaDirecta;
	}

	public void setTasaDirecta(Boolean tasaDirecta) {
		this.tasaDirecta = tasaDirecta;
	}

	public TasaMedioPago getTasaMedioPago() {
		return tasaMedioPago;
	}

	public void setTasaMedioPago(TasaMedioPago tasaMedioPago) {
		this.tasaMedioPago = tasaMedioPago;
	}

	public Double getTasaDirectaIngresada() {
		return tasaDirectaIngresada;
	}

	public void setTasaDirectaIngresada(Double tasaDirectaIngresada) {
		this.tasaDirectaIngresada = tasaDirectaIngresada;
	}
}
