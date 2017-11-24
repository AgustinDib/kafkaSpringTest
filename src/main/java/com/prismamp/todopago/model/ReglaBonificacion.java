package com.prismamp.todopago.model;

public class ReglaBonificacion {

	private Boolean tasaDirecta;

	private Double bonificacionCFVendedor;

	private TasaMedioPago tasaMedioPago;

	private Double tasaDirectaIngresada;

	public Boolean isTasaDirecta() {
		return tasaDirecta;
	}

	public void setTasaDirecta(Boolean tasaDirecta) {
		this.tasaDirecta = tasaDirecta;
	}

	public Double getBonificacionCFVendedor() {
		return bonificacionCFVendedor;
	}

	public void setBonificacionCFVendedor(Double bonificacionCFVendedor) {
		this.bonificacionCFVendedor = bonificacionCFVendedor;
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
