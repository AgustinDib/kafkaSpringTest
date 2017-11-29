package com.prismamp.todopago.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.model.CargoCuenta;
import com.prismamp.todopago.model.ReglaBonificacion;

@Repository
public class CargoRepository {

	/**
	 * Devuelve todos los cargos existentes y los deja en el cache "cargos".
	 * 
	 * @return List de todos los cargos.
	 */
	@Cacheable("cargos")
	public List<Cargo> findAll() {
		throw new NotImplementedException();
	}

	/**
	 * Devuelve todos los cargos existentes y los actualiza el cache "cargos".
	 * 
	 * @return List de todos los cargos.
	 */
	@Scheduled(cron = "0 0 0 * * *")
	@CacheEvict(value = "cargos", allEntries = true)
	public List<Cargo> updateAll() {
		throw new NotImplementedException();
	}

	/**
	 * Devuelve todos los cargos según la base de cálculo de la transacción.
	 * 
	 * @param tipoCuenta.
	 * @param tipoMedioPago.
	 * @param baseCalculo.
	 * @return List de cargos filtrados.
	 */
	public List<Cargo> findByBaseCalculoTransaccion(Long tipoCuenta, Long tipoMedioPago, Long baseCalculo) {
		return findAll().stream().filter(p -> p.getIdTipoCuenta().equals(tipoCuenta))
				.filter(p -> p.getTipoMedioPago().getId().equals(tipoMedioPago))
				.filter(p -> p.getIdBaseCalculo().equals(baseCalculo)).collect(Collectors.toList());
	}

	/**
	 * Devuelve un CargoCuenta según el id del cargo y el id de la cuenta.
	 * 
	 * @param cargo.
	 * @param cuenta.
	 * @return CargoCuenta.
	 */
	public CargoCuenta findCargoCuenta(Long cargo, Long cuenta) {
		throw new NotImplementedException();
	}

	/**
	 * Devuelve una ReglaBonificacion según el id de la promoción.
	 * 
	 * @param idPromotion.
	 * @return ReglaBonificacion.
	 */
	public ReglaBonificacion findReglaBonificacion(Long idPromotion) {
		throw new NotImplementedException();
	}

	/**
	 * Devuelve el valor de la bonificación en la tabla Volumen_Regla_Promocion
	 * según el id de la promoción.
	 * 
	 * @param idPromotion.
	 * @return Double.
	 */
	public Double findVolumenReglaPromocionBonificacion(Long idPromotion) {
		throw new NotImplementedException();
	}

	/**
	 * Devuelve el valor de la tasa en la tabla Volumen_Regla_Promocion según el id
	 * de la promoción.
	 * 
	 * @param idPromotion.
	 * @return Double.
	 */
	public Double findVolumenReglaPromocionTasa(Long idPromotion) {
		throw new NotImplementedException();
	}

	/**
	 * Devuelve el valor del monto en la tabla Acumulador_Promociones según el id de
	 * la promoción.
	 * 
	 * @param idPromotion.
	 * @return Double.
	 */
	public Double findAcumuladorPromocionesMonto(Long idPromotion) {
		throw new NotImplementedException();
	}
}
