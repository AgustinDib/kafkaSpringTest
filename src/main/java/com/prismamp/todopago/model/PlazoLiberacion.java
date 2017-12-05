/**
 * 
 */
package com.prismamp.todopago.model;

import java.util.Date;

/**
 * @description POJO que modelo el objeto Plazo Liberacion de la DB
 *
 */
public class PlazoLiberacion {
	
	private Long id;

	private CanalAdhesion canal;
	
	private TipoMedioPago tipoMedioPago;
	
	private MedioPago medioPago;
	
	private Long idCuenta;

	private Long idTipoCuenta;
	
	private Long idGrupoRubro;

	private int plazoLiberacion;

	private int plazoLiberacionCuotas;
	
	private Date fechaBaja;
	
	private Date fechaAlta;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CanalAdhesion getCanal() {
		return canal;
	}

	public void setCanal(CanalAdhesion canal) {
		this.canal = canal;
	}

	public TipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(TipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
	
	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Long getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(Long idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public Long getIdGrupoRubro() {
		return idGrupoRubro;
	}

	public void setIdGrupoRubro(Long idGrupoRubro) {
		this.idGrupoRubro = idGrupoRubro;
	}

	public int getPlazoLiberacion() {
		return plazoLiberacion;
	}

	public void setPlazoLiberacion(int plazoLiberacion) {
		this.plazoLiberacion = plazoLiberacion;
	}

	public int getPlazoLiberacionCuotas() {
		return plazoLiberacionCuotas;
	}

	public void setPlazoLiberacionCuotas(int plazoLiberacionCuotas) {
		this.plazoLiberacionCuotas = plazoLiberacionCuotas;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	

}
