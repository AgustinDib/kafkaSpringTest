package com.prismamp.todopago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.calculator.CargoCalculator;
import com.prismamp.todopago.exceptions.BusinessException;
import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.model.CargoCuenta;
import com.prismamp.todopago.model.CargoTransaccion;
import com.prismamp.todopago.model.ReglaBonificacion;
import com.prismamp.todopago.model.Transaccion;
import com.prismamp.todopago.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;

	@Autowired
	private CargoCalculator calculator;

	public Transaccion calculateCargos(Transaccion transaccion) {
		if (null == transaccion) {
			throw new BusinessException("");
		}
		List<Cargo> cargos = repository.findByBaseCalculoTransaccion(transaccion.getIdTipoCuenta(),
				transaccion.getIdTipoMedioPago(), transaccion.getIdBaseCalculo());

		for (Cargo cargo : cargos) {
			CargoTransaccion cargoTransaccion = new CargoTransaccion();
			CargoCuenta cargoCuenta = repository.findCargoCuenta(cargo.getId(), transaccion.getCuentaId());

			if (!calculator.isCostoFinanciero(cargo)) {
				cargoTransaccion = calculator.calculateRelacionVigente(cargoCuenta, cargo, cargoTransaccion);
				cargoTransaccion = calculator.calculateMonto(cargoTransaccion, cargo, transaccion.getImporte());
			} else {
				Long idPromotion = transaccion.getIdPromotion();

				ReglaBonificacion regla = repository.findReglaBonificacion(idPromotion);
				Double monto = repository.findAcumuladorPromocionesMonto(idPromotion);

				if (!regla.isTasaDirecta()) {
					cargoTransaccion = getCostoFinanciero(transaccion, cargoTransaccion, idPromotion, regla, monto);
				} else {
					cargoTransaccion = getCostoFinancieroTasaDirecta(transaccion, cargoTransaccion, idPromotion, regla, monto);
				}
				cargoTransaccion.setIdTipoAplicacion(cargoCuenta.getIdTipoAplicacion());
				cargoTransaccion = calculator.calculateValorAplicado(cargoTransaccion, regla);
			}

			cargoTransaccion.setIdCargo(cargo.getId());
			cargoTransaccion.setIdTransaccion(transaccion.getId());
			transaccion.addCargo(cargoTransaccion);
		}
		return transaccion;
	}

	private CargoTransaccion getCostoFinancieroTasaDirecta(Transaccion transaccion, CargoTransaccion cargoTransaccion,
			Long idPromotion, ReglaBonificacion regla, Double monto) {
		Double tasa;
		if (null == regla.getTasaDirectaIngresada()) {
			tasa = repository.findVolumenReglaPromocionTasa(idPromotion);
		} else {
			tasa = regla.getTasaDirectaIngresada();
		}
		cargoTransaccion = calculator.calculateCostoFinanciero(cargoTransaccion, transaccion.getImporte(), tasa, monto);
		return cargoTransaccion;
	}

	private CargoTransaccion getCostoFinanciero(Transaccion transaccion, CargoTransaccion cargoTransaccion,
			Long idPromotion, ReglaBonificacion regla, Double monto) {
		Double tasa;
		Double bonificacion;
		tasa = regla.getTasaMedioPago().getTasaDirecta();

		if (null == regla.getBonificacionCFVendedor()) {
			bonificacion = repository.findVolumenReglaPromocionBonificacion(idPromotion);
		} else {
			bonificacion = regla.getBonificacionCFVendedor();
		}
		cargoTransaccion = calculator.calculateCostoFinanciero(cargoTransaccion, transaccion.getImporte(), tasa, bonificacion, monto);
		return cargoTransaccion;
	}

	public List<Cargo> findAll() {
		return repository.findAll();
	}
}
