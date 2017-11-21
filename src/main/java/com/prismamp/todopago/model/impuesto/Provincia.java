package com.prismamp.todopago.model.impuesto;

public class Provincia {

	private Long id;

	private String codigo;

	private String nombre;

	private String codigoAurus;

	private String codigoContable;

	private Long ultimoCertificadoIIBB;

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

	public String getCodigoAurus() {
		return codigoAurus;
	}

	public void setCodigoAurus(String codigoAurus) {
		this.codigoAurus = codigoAurus;
	}

	public String getCodigoContable() {
		return codigoContable;
	}

	public void setCodigoContable(String codigoContable) {
		this.codigoContable = codigoContable;
	}

	public Long getUltimoCertificadoIIBB() {
		return ultimoCertificadoIIBB;
	}

	public void setUltimoCertificadoIIBB(Long ultimoCertificadoIIBB) {
		this.ultimoCertificadoIIBB = ultimoCertificadoIIBB;
	}
}
