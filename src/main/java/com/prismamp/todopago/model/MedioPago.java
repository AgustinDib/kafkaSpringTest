/**
 * 
 */
package com.prismamp.todopago.model;

/**
 * @description POJO que modelo el objeto Medio de Pago de la DB
 *
 */
public class MedioPago {
	
	private Long id;
	
	private Integer flagHabilitado;
	
	private TipoMedioPago tipoMedioPago;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFlagHabilitado() {
		return flagHabilitado;
	}

	public void setFlagHabilitado(Integer flagHabilitado) {
		this.flagHabilitado = flagHabilitado;
	}

	public TipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(TipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	
	

}
