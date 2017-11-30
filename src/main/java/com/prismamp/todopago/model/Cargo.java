package com.prismamp.todopago.model;

public class Cargo {

	private Long id;

	private TipoCargo tipoCargo;

	private TipoMedioPago tipoMedioPago;

	private Long idCuenta;

	private Long idTipoCuenta;

	private Long idBaseCalculo;

	private CanalAdhesion canalAdhesion;

	private Valor valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoCargo getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(TipoCargo tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public TipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(TipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
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

	public Long getIdBaseCalculo() {
		return idBaseCalculo;
	}

	public void setIdBaseCalculo(Long idBaseCalculo) {
		this.idBaseCalculo = idBaseCalculo;
	}

	public CanalAdhesion getCanalAdhesion() {
		return canalAdhesion;
	}

	public void setCanalAdhesion(CanalAdhesion canalAdhesion) {
		this.canalAdhesion = canalAdhesion;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}
}
