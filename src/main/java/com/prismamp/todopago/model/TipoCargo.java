package com.prismamp.todopago.model;

public class TipoCargo {

	private Long id;

	private String codigo;

	private String descripcion;

	private String signo;

	private Boolean configuraPanel;

	private Boolean aplicaIva;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public Boolean getConfiguraPanel() {
		return configuraPanel;
	}

	public void setConfiguraPanel(Boolean configuraPanel) {
		this.configuraPanel = configuraPanel;
	}

	public Boolean getAplicaIva() {
		return aplicaIva;
	}

	public void setAplicaIva(Boolean aplicaIva) {
		this.aplicaIva = aplicaIva;
	}
}
