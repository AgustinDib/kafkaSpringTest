package com.prismamp.todopago.model.impuesto;

public class Impuesto {

	private Long id;
	
	private String descripcion;
	
	private Boolean todasProvincias;
	
	private Provincia provincia;
	
	private String codigo;
	
	private Long idTipoAplicacion;
	
	private Long idMotivoAjusteNegativo;
	
	private Long idMotivoAjustePositivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getTodasProvincias() {
		return todasProvincias;
	}

	public void setTodasProvincias(Boolean todasProvincias) {
		this.todasProvincias = todasProvincias;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getIdTipoAplicacion() {
		return idTipoAplicacion;
	}

	public void setIdTipoAplicacion(Long idTipoAplicacion) {
		this.idTipoAplicacion = idTipoAplicacion;
	}

	public Long getIdMotivoAjusteNegativo() {
		return idMotivoAjusteNegativo;
	}

	public void setIdMotivoAjusteNegativo(Long idMotivoAjusteNegativo) {
		this.idMotivoAjusteNegativo = idMotivoAjusteNegativo;
	}

	public Long getIdMotivoAjustePositivo() {
		return idMotivoAjustePositivo;
	}

	public void setIdMotivoAjustePositivo(Long idMotivoAjustePositivo) {
		this.idMotivoAjustePositivo = idMotivoAjustePositivo;
	}
}
