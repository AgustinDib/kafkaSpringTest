package com.prismamp.todopago.calculator;

import org.springframework.stereotype.Service;

import com.prismamp.todopago.exceptions.BusinessException;
import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.model.CargoCuenta;
import com.prismamp.todopago.model.CargoTransaccion;
import com.prismamp.todopago.model.PromocionResponse;

@Service
public class CargoCalculator {

	public Boolean isCostoFinanciero(Cargo cargo) {
		if (null != cargo && null != cargo.getTipoCargo()) {
			return "COSTO_FIN_V".equals(cargo.getTipoCargo().getCodigo());
		} else {
			throw new BusinessException("No se puede determinar si el Cargo es de Costo Financiero.");
		}
	}

	public CargoTransaccion calculateMonto(CargoTransaccion cargoTransaccion, Cargo cargo, Double importe) {
		if (null != cargo && null != cargo.getValor()) {

			cargoTransaccion = checkCargoTransaccion(cargoTransaccion);

			if ("AP_PORCENTAJE".equalsIgnoreCase(cargoTransaccion.getCodigoAplicacion())) {
				cargoTransaccion.setMontoCalculado(importe * (cargo.getValor().getValor() / 100));
			} else if ("AP_FIJO".equalsIgnoreCase(cargoTransaccion.getCodigoAplicacion())) {
				cargoTransaccion.setMontoCalculado(cargo.getValor().getValor());
			} else {
				cargoTransaccion.setMontoCalculado(0d);
			}

		} else {
			throw new BusinessException("No se puede calcular el monto ya que el código de aplicación no es válido.");
		}
		return cargoTransaccion;
	}

	public CargoTransaccion calculateMonto(CargoTransaccion cargoTransaccion, Double importe, PromocionResponse promocion) {
		if (null == promocion || null == promocion.getTasaDirecta()) {
			throw new BusinessException("No se puede calcular el monto sin tasa directa.");
		}
		cargoTransaccion = checkCargoTransaccion(cargoTransaccion);

		Double montoCalculado = importe * (promocion.getTasaDirecta() / 100)
				* (100 - promocion.getBonificacionCFVendedor() / 100);
		cargoTransaccion.setMontoCalculado(montoCalculado);

		return cargoTransaccion;
	}

	public CargoTransaccion calculateMontoTasaDirecta(CargoTransaccion cargoTransaccion, Double importe, Double tasaDirecta) {
		cargoTransaccion = checkCargoTransaccion(cargoTransaccion);

		Double montoCalculado = importe * (tasaDirecta / 100);
		cargoTransaccion.setMontoCalculado(montoCalculado);

		return cargoTransaccion;
	}

	public PromocionResponse setCodigoTipoPromocion(PromocionResponse promocion) {
		if (null != promocion && null == promocion.getIdPromocion()) {
			promocion.setCodigo("PROMO_CTAS");
		}
		return promocion;
	}

	public CargoTransaccion calculateCostoFinanciero(CargoTransaccion cargoTransaccion, Double importe, Double tasa, Double bonificacion, Double monto) {

		if (importe < 0 || tasa < 0 || bonificacion < 0) {
			throw new BusinessException("No se puede calcular el costo financiero con valores negativos.");
		}
		if (null == monto) {
			monto = 0d;
		}
		cargoTransaccion = checkCargoTransaccion(cargoTransaccion);

		Double montoCalculado = importe * tasa * ((100 - bonificacion) / 100);
		cargoTransaccion.setMontoCalculado(montoCalculado + monto);

		return cargoTransaccion;
	}

	public CargoTransaccion calculateCostoFinanciero(CargoTransaccion cargoTransaccion, Double importe, Double tasa, Double monto) {
		if (importe < 0 || tasa < 0) {
			throw new BusinessException("No se puede calcular el costo financiero con valores negativos.");
		}
		if (null == monto) {
			monto = 0d;
		}
		cargoTransaccion = checkCargoTransaccion(cargoTransaccion);

		Double montoCalculado = importe * (tasa / 100);
		cargoTransaccion.setMontoCalculado(montoCalculado + monto);

		return cargoTransaccion;
	}

	public CargoTransaccion calculateValorAplicado(CargoTransaccion cargoTransaccion, PromocionResponse promocion) {
		if (null == promocion) {
			throw new BusinessException("No se puede calcular el valor aplicado para una promoción nula.");
		}
		cargoTransaccion = checkCargoTransaccion(cargoTransaccion);

		if (null == promocion.getBonificacionCFVendedor()) {
			cargoTransaccion.setValorAplicado(0d);
		} else {
			Double bonificacion = promocion.getBonificacionCFVendedor();
			if (bonificacion < 0 || bonificacion > 100) {
				throw new BusinessException("No se puede calcular el valor aplicado para una bonificacion menor que cero o mayor que 100.");
			}
			cargoTransaccion.setValorAplicado(100 - bonificacion);
		}
		return cargoTransaccion;
	}

	public CargoTransaccion mapCargo(Cargo cargo, String idTransaccion) {
		if (null == cargo) {
			throw new BusinessException("No se puede mapear un cargo nulo.");
		}
		CargoTransaccion result = new CargoTransaccion();
		result.setIdCargo(cargo.getId());
		result.setValorAplicado(cargo.getValor().getValor());
		result.setIdTipoAplicacion(cargo.getValor().getIdTipoAplicacion());
		result.setCodigoAplicacion(cargo.getTipoAplicacion().getCodigo());
		result.setCodigoTipoCargo(cargo.getTipoCargo().getCodigo());
		result.setIdTransaccion(idTransaccion);

		return result;
	}

	public CargoTransaccion mapCargoCuenta(CargoCuenta cargoCuenta, CargoTransaccion cargoTransaccion) {
		if (null == cargoCuenta) {
			return cargoTransaccion;
		} else {
			cargoTransaccion.setValorAplicado(cargoCuenta.getValor());
			cargoTransaccion.setIdTipoAplicacion(cargoCuenta.getIdTipoAplicacion());
			cargoTransaccion.setCodigoAplicacion(cargoCuenta.getCodigo());

			return cargoTransaccion;
		}
	}

	private CargoTransaccion checkCargoTransaccion(CargoTransaccion cargoTransaccion) {
		if (null == cargoTransaccion) {
			cargoTransaccion = new CargoTransaccion();
		}
		return cargoTransaccion;
	}

}
