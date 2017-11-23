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

	public CargoTransaccion calculateRelacionVigente(CargoCuenta cargoCuenta, Cargo cargo) {
		if (null != cargoCuenta && null != cargo) {
			CargoTransaccion result = new CargoTransaccion();

			if (isRelacionVigente(cargoCuenta)) {
				result.setValorAplicado(cargoCuenta.getValor());
				result.setIdTipoAplicacion(cargoCuenta.getIdTipoAplicacion());
			} else {
				Valor valor = cargo.getValor();

				if (null != valor) {
					result.setValorAplicado(valor.getValor());
					result.setIdTipoAplicacion(valor.getIdTipoAplicacion());
				} else {
					throw new BusinessException("No se puede calcular el Valor a partir del Cargo.");
				}
			}

			return result;
		} else {
			throw new BusinessException("No se puede calcular si el CargoCuenta tiene relación vigente.");
		}
	}

	private boolean isRelacionVigente(CargoCuenta cargoCuenta) {
		Date inicioVigencia = cargoCuenta.getInicioVigencia();
		Date finVigencia = cargoCuenta.getFinVigencia();
		Date now = new Date();

		return null != inicioVigencia && null != finVigencia && inicioVigencia.before(now) && finVigencia.after(now);
	}
}
