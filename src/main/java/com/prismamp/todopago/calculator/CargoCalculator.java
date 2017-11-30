package com.prismamp.todopago.calculator;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.prismamp.todopago.exceptions.BusinessException;
import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.model.CargoCuenta;
import com.prismamp.todopago.model.CargoTransaccion;
import com.prismamp.todopago.model.ReglaBonificacion;
import com.prismamp.todopago.model.Valor;

@Service
public class CargoCalculator {

	public Boolean isCostoFinanciero(Cargo cargo) {
		if (null != cargo && null != cargo.getTipoCargo()) {
			return "COSTO_FIN_V".equals(cargo.getTipoCargo().getCodigo());
		} else {
			throw new BusinessException("No se puede determinar si el Cargo es de Costo Financiero.");
		}
	}

	public CargoTransaccion calculateRelacionVigente(CargoCuenta cargoCuenta, Cargo cargo,
			CargoTransaccion cargoTransaccion) {

		if (null != cargo) {
			cargoTransaccion = checkCargoTransaccion(cargoTransaccion);
			if (isRelacionVigente(cargoCuenta)) {
				cargoTransaccion.setValorAplicado(cargoCuenta.getValor());
				cargoTransaccion.setIdTipoAplicacion(cargoCuenta.getIdTipoAplicacion());
			} else {
				Valor valor = cargo.getValor();

				if (null != valor) {
					cargoTransaccion.setValorAplicado(valor.getValor());
					cargoTransaccion.setIdTipoAplicacion(valor.getTipo().getId());
				} else {
					throw new BusinessException("No se puede calcular el Valor a partir del Cargo.");
				}
			}

			return cargoTransaccion;
		} else {
			throw new BusinessException("No se puede calcular si el CargoCuenta tiene relación vigente.");
		}
	}

	public CargoTransaccion calculateMonto(CargoTransaccion cargoTransaccion, Cargo cargo, Double importe) {
		if (null != cargo && null != cargo.getValor()) {

			cargoTransaccion = checkCargoTransaccion(cargoTransaccion);

			if ("AP_PORCENTAJE".equalsIgnoreCase(cargo.getValor().getTipo().getCodigo())) {
				cargoTransaccion.setMontoCalculado(importe * (cargo.getValor().getValor() / 100));
			} else if ("AP_FIJO".equalsIgnoreCase(cargo.getValor().getTipo().getCodigo())) {
				cargoTransaccion.setMontoCalculado(cargo.getValor().getValor());
			} else {
				throw new BusinessException("No se puede calcular el monto, el código de aplicación no es válido.");
			}

		} else {
			throw new BusinessException("No se puede calcular el monto ya que el código de aplicación no es válido.");
		}
		return cargoTransaccion;
	}

	public CargoTransaccion calculateCostoFinanciero(CargoTransaccion cargoTransaccion, Double importe, Double tasa,
			Double bonificacion, Double monto) {

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

	public CargoTransaccion calculateValorAplicado(CargoTransaccion cargoTransaccion, ReglaBonificacion regla) {
		if (null == regla) {
			throw new BusinessException("No se puede calcular el valor aplicado para una Regla de Bonificación nula.");
		}
		cargoTransaccion = checkCargoTransaccion(cargoTransaccion);

		if (null == regla.getBonificacionCFVendedor()) {
			cargoTransaccion.setValorAplicado(0d);
		} else {
			// TODO Tiene sentido validar el rango de valores para "BonificacionCFVendedor"?
			Double bonificacion = regla.getBonificacionCFVendedor();
			if (bonificacion < 0 || bonificacion > 100) {
				throw new BusinessException("No se puede calcular el valor aplicado para una bonificacion menor que cero o mayor que 100.");
			}
			cargoTransaccion.setValorAplicado(100 - bonificacion);
		}
		return cargoTransaccion;
	}

	private CargoTransaccion checkCargoTransaccion(CargoTransaccion cargoTransaccion) {
		if (null == cargoTransaccion) {
			cargoTransaccion = new CargoTransaccion();
		}
		return cargoTransaccion;
	}

	private boolean isRelacionVigente(CargoCuenta cargoCuenta) {
		if (null == cargoCuenta) {
			return false;
		}
		Date inicioVigencia = cargoCuenta.getInicioVigencia();
		Date finVigencia = cargoCuenta.getFinVigencia();
		Date now = new Date();

		return null != inicioVigencia && null != finVigencia && inicioVigencia.before(now) && finVigencia.after(now);
	}
}
