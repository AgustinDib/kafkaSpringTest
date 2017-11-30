package com.prismamp.todopago.model;

import java.util.ArrayList;
import java.util.List;

public class Transaccion {

	private String id;

	private Long cuentaId;

	private Long idTipoMedioPago;

	private Long idBaseCalculo;

	private Double importe;

	private List<CargoTransaccion> cargos;

	private Long idPromotion;

	public Transaccion() {
		this.cargos = new ArrayList<CargoTransaccion>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
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

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
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

	public Long getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(Long idPromotion) {
		this.idPromotion = idPromotion;
	}
}
