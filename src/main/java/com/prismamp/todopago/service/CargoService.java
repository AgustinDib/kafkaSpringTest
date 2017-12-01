package com.prismamp.todopago.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.calculator.CargoCalculator;
import com.prismamp.todopago.exceptions.BusinessException;
import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.model.CargoCuenta;
import com.prismamp.todopago.model.CargoRequest;
import com.prismamp.todopago.model.CargoTransaccion;
import com.prismamp.todopago.model.ReglaBonificacion;
import com.prismamp.todopago.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;

	@Autowired
	private CargoCalculator calculator;

	public List<CargoTransaccion> calculateCargos(CargoRequest request) {
		if (null == request) {
			throw new BusinessException("");
		}

		List<CargoTransaccion> cargosTransaccion = new ArrayList<CargoTransaccion>();

		Long idTipoMedioPago = repository.findIdTipoMedioPago(request.getIdMedioPago());

		List<Cargo> cargos = repository.findByBaseCalculoTransaccion(request.getIdBaseCalculo(), request.getIdCuenta(),
				idTipoMedioPago, request.getIdCanal(), request.getCreated());

		for (Cargo cargo : cargos) {
			CargoTransaccion cargoTransaccion = new CargoTransaccion();
			CargoCuenta cargoCuenta = repository.findCargoCuenta(cargo.getId(), request.getIdCuenta());

			if (!calculator.isCostoFinanciero(cargo)) {
				cargoTransaccion = calculator.calculateRelacionVigente(cargoCuenta, cargo, cargoTransaccion);
				cargoTransaccion = calculator.calculateMonto(cargoTransaccion, cargo, request.getImporte());
			} else {
				Long idPromotion = request.getIdPromotion();

				ReglaBonificacion regla = repository.findReglaBonificacion(idPromotion);
				Double monto = repository.findAcumuladorPromocionesMonto(idPromotion);

				if (!regla.isTasaDirecta()) {
					cargoTransaccion = getCostoFinanciero(request.getImporte(), cargoTransaccion, idPromotion, regla, monto);
				} else {
					cargoTransaccion = getCostoFinancieroTasaDirecta(request.getImporte(), cargoTransaccion, idPromotion, regla,
							monto);
				}
				if (null != cargoCuenta) {
					cargoTransaccion.setIdTipoAplicacion(cargoCuenta.getIdTipoAplicacion());
				}
				cargoTransaccion = calculator.calculateValorAplicado(cargoTransaccion, regla);
			}

			cargoTransaccion.setIdCargo(cargo.getId());
			cargoTransaccion.setIdTransaccion(request.getIdTransaccion());
			cargosTransaccion.add(cargoTransaccion);
		}
		return cargosTransaccion;
	}

	private CargoTransaccion getCostoFinancieroTasaDirecta(Double importe, CargoTransaccion cargoTransaccion,
			Long idPromotion, ReglaBonificacion regla, Double monto) {
		Double tasa;

		if (null == regla.getTasaDirectaIngresada()) {
			tasa = repository.findVolumenReglaPromocionTasa(idPromotion);
		} else {
			tasa = regla.getTasaDirectaIngresada();
		}
		cargoTransaccion = calculator.calculateCostoFinanciero(cargoTransaccion, importe, tasa, monto);
		return cargoTransaccion;
	}

	private CargoTransaccion getCostoFinanciero(Double importe, CargoTransaccion cargoTransaccion, Long idPromotion,
			ReglaBonificacion regla, Double monto) {
		Double tasa;
		Double bonificacion;
		tasa = regla.getTasaMedioPago().getTasaDirecta();

		if (null == regla.getBonificacionCFVendedor()) {
			bonificacion = repository.findVolumenReglaPromocionBonificacion(idPromotion);
		} else {
			bonificacion = regla.getBonificacionCFVendedor();
		}
		cargoTransaccion = calculator.calculateCostoFinanciero(cargoTransaccion, importe, tasa, bonificacion, monto);
		return cargoTransaccion;
	}

	public List<Cargo> findAll() {
		return repository.findAll();
	}
}
