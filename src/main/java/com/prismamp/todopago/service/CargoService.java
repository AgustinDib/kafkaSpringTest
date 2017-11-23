package com.prismamp.todopago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.calculator.CargoCalculator;
import com.prismamp.todopago.exceptions.BusinessException;
import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.model.CargoCuenta;
import com.prismamp.todopago.model.CargoTransaccion;
import com.prismamp.todopago.model.Transaccion;
import com.prismamp.todopago.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;

	@Autowired
	private CargoCalculator calculator;

	public Transaccion calculateCargos(Transaccion transaccion) {
		List<Cargo> cargos = repository.findByBaseCalculoTransaccion(transaccion.getIdTipoCuenta(),
				transaccion.getIdTipoMedioPago(), transaccion.getIdBaseCalculo());

		try {
			for (Cargo cargo : cargos) {
				CargoTransaccion cargoTransaccion = null;

				if (!calculator.isCostoFinanciero(cargo)) {
					CargoCuenta cargoCuenta = repository.findCargoCuenta(cargo.getId(), transaccion.getCuentaId());

					cargoTransaccion = calculator.calculateRelacionVigente(cargoCuenta, cargo, cargoTransaccion);
					cargoTransaccion = calculator.calculateMonto(cargoTransaccion, cargo, transaccion.getImporte());
				} else {

				}

				transaccion.addCargo(cargoTransaccion);
			}
		} catch (BusinessException ex) {

		}
		return transaccion;
	}

	public List<Cargo> findAll() {
		return repository.findAll();
	}
}
