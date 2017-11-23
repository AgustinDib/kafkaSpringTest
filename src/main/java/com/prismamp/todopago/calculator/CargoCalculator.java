package com.prismamp.todopago.calculator;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.prismamp.todopago.exceptions.BusinessException;
import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.model.CargoCuenta;
import com.prismamp.todopago.model.CargoTransaccion;
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

		if (null != cargoCuenta && null != cargo) {
			if (null == cargoTransaccion) {
				cargoTransaccion = new CargoTransaccion();
			}
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

			if (null == cargoTransaccion) {
				cargoTransaccion = new CargoTransaccion();
			}

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

	private boolean isRelacionVigente(CargoCuenta cargoCuenta) {
		Date inicioVigencia = cargoCuenta.getInicioVigencia();
		Date finVigencia = cargoCuenta.getFinVigencia();
		Date now = new Date();

		return null != inicioVigencia && null != finVigencia && inicioVigencia.before(now) && finVigencia.after(now);
	}
}
