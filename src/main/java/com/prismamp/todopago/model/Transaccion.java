package com.prismamp.todopago.model;

import java.util.ArrayList;
import java.util.List;

public class Transaccion {

	private Long id;

	private Long cuentaId;

	private Long idTipoCuenta;

	private Long idTipoMedioPago;

	private Long idBaseCalculo;

	private List<CargoTransaccion> cargos;

	public Transaccion() {
		this.cargos = new ArrayList<CargoTransaccion>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public Long getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(Long idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public Long getIdTipoMedioPago() {
		return idTipoMedioPago;
	}

	public void setIdTipoMedioPago(Long idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}

	public Long getIdBaseCalculo() {
		return idBaseCalculo;
	}

	public void setIdBaseCalculo(Long idBaseCalculo) {
		this.idBaseCalculo = idBaseCalculo;
	}

	public List<CargoTransaccion> getCargos() {
		return cargos;
	}

	public void setCargos(List<CargoTransaccion> cargos) {
		this.cargos = cargos;
	}

	public void addCargo(CargoTransaccion cargo) {
		this.cargos.add(cargo);
	}
}
