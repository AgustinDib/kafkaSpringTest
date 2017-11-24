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

	@Cacheable("cargos")
	public List<Cargo> findAll() {
		throw new NotImplementedException();
	}

	@Scheduled(cron = "0 0 0 * * *")
	@CacheEvict(value = "cargos", allEntries = true)
	public List<Cargo> updateAll() {
		throw new NotImplementedException();
	}

	public List<Cargo> findByBaseCalculoTransaccion(Long tipoCuenta, Long tipoMedioPago, Long baseCalculo) {
		return findAll().stream().filter(p -> p.getIdTipoCuenta().equals(tipoCuenta))
				.filter(p -> p.getTipoMedioPago().getId().equals(tipoMedioPago))
				.filter(p -> p.getIdBaseCalculo().equals(baseCalculo)).collect(Collectors.toList());
	}

	public CargoCuenta findCargoCuenta(Long cargo, Long cuenta) {
		throw new NotImplementedException();
	}

	public ReglaBonificacion findReglaBonificacion(Long idPromotion) {
		throw new NotImplementedException();
	}

	public Double findVolumenReglaPromocionBonificacion(Long idPromotion) {
		throw new NotImplementedException();
	}

	public Double findVolumenReglaPromocionTasa(Long idPromotion) {
		throw new NotImplementedException();
	}

	public Double findAcumuladorPromocionesMonto(Long idPromotion) {
		throw new NotImplementedException();
	}
}
