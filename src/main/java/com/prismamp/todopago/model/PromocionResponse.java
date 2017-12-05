package com.prismamp.todopago.model;

public class PromocionResponse {

	private Double bonificacionCFVendedor;

	private Double tasaDirecta;

	private String codigo;

	private Long idPromocion;

	public Double getBonificacionCFVendedor() {
		return bonificacionCFVendedor;
	}

	public void setBonificacionCFVendedor(Double bonificacionCFVendedor) {
		this.bonificacionCFVendedor = bonificacionCFVendedor;
	}

	public Double getTasaDirecta() {
		return tasaDirecta;
	}

	public void setTasaDirecta(Double tasaDirecta) {
		this.tasaDirecta = tasaDirecta;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(Long idPromocion) {
		this.idPromocion = idPromocion;
	}

}
