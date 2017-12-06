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
import com.prismamp.todopago.model.PromocionResponse;
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
		Long idCanal = repository.findIdCanalByNombre(request.getCanal());
		Long idBaseCalculo = findIdBaseCalculo(request.getFacilitiesPayments());

		List<Cargo> defaultCargos = repository.findByDefault(request.getIdCuenta(),
				request.getIdMedioPago(), idCanal, request.getCreated(), idBaseCalculo);

		List<CargoTransaccion> cargosTransaccion = new ArrayList<>();

		for (Cargo cargo : defaultCargos) {
			CargoTransaccion cargoTransaccion = calculator.mapCargo(cargo, request.getIdTransaccion());
			CargoCuenta cargoCuenta = repository.findCargoCuenta(cargo.getId(), request.getIdCuenta(), request.getCreated());
			cargoTransaccion = calculator.mapCargoCuenta(cargoCuenta, cargoTransaccion);

			if (!calculator.isCostoFinanciero(cargo)) {
				cargoTransaccion = calculator.calculateMonto(cargoTransaccion, cargo, request.getImporte());
			} else {
				Long idPromotion = request.getIdPromotion();

				ReglaBonificacion regla = repository.findReglaBonificacion(idPromotion);
				PromocionResponse promocion;

				if (!regla.isTasaDirecta()) {
					promocion = repository.findPromocion(25L, idPromotion);
				} else {
					promocion = repository.findPromocionTasaDirecta(25L, idPromotion);
				}
				promocion = calculator.setCodigoTipoPromocion(promocion);

				if (null == idPromotion || 0 == idPromotion || 100 == promocion.getBonificacionCFVendedor()) {
					cargoTransaccion.setMontoCalculado(0d);
					cargoTransaccion.setValorAplicado(0d);
				} else if (!"PROMO_CTAS".equals(promocion.getCodigo())) {

					if (!regla.isTasaDirecta()) {
						Double montoTotal = getMontoTotal(promocion.getCodigo(), request);
						PromocionResponse promocionNotTasaDirecta = repository.findPromocionNotTasaDirecta(montoTotal, idPromotion);

						promocion.setBonificacionCFVendedor(promocionNotTasaDirecta.getBonificacionCFVendedor());
						promocion.setTasaDirecta(promocionNotTasaDirecta.getTasaDirecta());

						cargoTransaccion = calculator.calculateMonto(cargoTransaccion, request.getImporte(), promocion);
						cargoTransaccion = calculator.calculateValorAplicado(cargoTransaccion, promocion);
					} else {
						if (null == regla.getTasaDirectaIngresada()) {
							Double tasaDirecta = repository.findVolumenReglaPromocionTasa(idPromotion, request.getImporte());
							promocion.setTasaDirecta(tasaDirecta);
						}

						cargoTransaccion = calculator.calculateMontoTasaDirecta(cargoTransaccion, request.getImporte(), promocion.getTasaDirecta());
						cargoTransaccion.setValorAplicado(promocion.getTasaDirecta());
					}
				}
			}
			cargosTransaccion.add(cargoTransaccion);
		}
		return cargosTransaccion;
	}

	private Double getMontoTotal(String codigo, CargoRequest request) {
		Double result;

		switch (codigo) {
		case "PROMO_VTA_MES_CTA":
			result = repository.findAcumuladorPromocionesMonto(request.getCreated(), request.getIdCuenta(), request.getIdPromotion());
			break;
		case "PROMO_VTA_TOTAL_CTA":
			result = repository.findAcumuladorPromocionesMonto(request.getIdCuenta(), request.getIdPromotion());
			break;
		case "PROMO_VTA_TOTAL":
			result = repository.findAcumuladorPromocionesMonto(request.getIdPromotion());
			break;
		default:
			throw new BusinessException("No se puede reconocer el código del tipo de promoción.");
		}
		return result;
	}

	private Long findIdBaseCalculo(Long facilitiesPayments) {
		String codigo;
		if (1 == facilitiesPayments) {
			codigo = "BC_TX_PAGO";
		} else {
			codigo = "BC_TX_CUOTAS";
		}
		return repository.findIdTipoByCodigo(codigo);
	}
}
