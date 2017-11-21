package com.prismamp.todopago.model.impuesto;

import java.math.BigDecimal;
import java.util.Date;

import com.prismamp.todopago.model.Tipo;

public class ImpuestoTipo {

	private Long id;

	private Tipo tipo;

	private Long idTipoAplicacion;

	private Long idBaseCalculo;

	private BigDecimal alicuota;

	private BigDecimal minimoNoImponible;

	private Boolean estado;

	private Impuesto impuesto;

	private Date vigenciaInicio;

	private Date vigenciaFin;

	private Long cantidadNoImponible;

	private Long idTipoPeriodo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Long getIdTipoAplicacion() {
		return idTipoAplicacion;
	}

	public void setIdTipoAplicacion(Long idTipoAplicacion) {
		this.idTipoAplicacion = idTipoAplicacion;
	}

	public Long getIdBaseCalculo() {
		return idBaseCalculo;
	}

	public void setIdBaseCalculo(Long idBaseCalculo) {
		this.idBaseCalculo = idBaseCalculo;
	}

	public BigDecimal getAlicuota() {
		return alicuota;
	}

	public void setAlicuota(BigDecimal alicuota) {
		this.alicuota = alicuota;
	}

	public BigDecimal getMinimoNoImponible() {
		return minimoNoImponible;
	}

	public void setMinimoNoImponible(BigDecimal minimoNoImponible) {
		this.minimoNoImponible = minimoNoImponible;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Impuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}

	public Date getVigenciaInicio() {
		return vigenciaInicio;
	}

	public void setVigenciaInicio(Date vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}

	public Date getVigenciaFin() {
		return vigenciaFin;
	}

	public void setVigenciaFin(Date vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}

	public Long getCantidadNoImponible() {
		return cantidadNoImponible;
	}

	public void setCantidadNoImponible(Long cantidadNoImponible) {
		this.cantidadNoImponible = cantidadNoImponible;
	}

	public Long getIdTipoPeriodo() {
		return idTipoPeriodo;
	}

	public void setIdTipoPeriodo(Long idTipoPeriodo) {
		this.idTipoPeriodo = idTipoPeriodo;
	}
}
