package com.prismamp.todopago.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.model.CargoCuenta;
import com.prismamp.todopago.model.PromocionResponse;
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

	public List<Cargo> findByDefault(Long idCuenta, Long idMedioPago, Long idCanal, Date created, Long idBaseCalculo) {
		return findAll().stream().filter(p -> idBaseCalculo == p.getIdBaseCalculo())
				.filter(p -> idCuenta.equals(p.getIdCuenta()))
				.filter(p -> idMedioPago.equals(Long.parseLong(p.getMedioPago().getId())))
				.filter(p -> idCanal == null || idCanal.equals(p.getIdCanal()) || idCanal.equals(0))
				.filter(p -> p.getValor().getInicioVigencia().before(created))
				.filter(p -> null == p.getValor().getFinVigencia() || p.getValor().getFinVigencia().after(created))
				.collect(Collectors.toList());
	}

	public Long findIdTipoByCodigo(String codigo) {
		throw new NotImplementedException();
	}

	public Long findIdCanalByNombre(String nombre) {
		throw new NotImplementedException();
	}

	public CargoCuenta findCargoCuenta(Long cargoId, Long cuentaId, Date created) {
		throw new NotImplementedException();
	}

	public PromocionResponse findPromocion(Long idGrupoTipo, Long idPromotion) {
		throw new NotImplementedException();
	}

	public PromocionResponse findPromocionTasaDirecta(Long idGrupoTipo, Long idPromotion) {
		throw new NotImplementedException();
	}

	public Double findAcumuladorPromocionesMonto(Date created, Long idCuenta, Long idPromotion) {
		throw new NotImplementedException();
	}

	public Double findAcumuladorPromocionesMonto(Long idCuenta, Long idPromotion) {
		throw new NotImplementedException();
	}

	public Double findAcumuladorPromocionesMonto(Long idPromotion) {
		throw new NotImplementedException();
	}

	public PromocionResponse findPromocionNotTasaDirecta(Double importe, Long idPromotion) {
		throw new NotImplementedException();
	}

	public Long findIdTipoMedioPago(Long medioPago) {
		throw new NotImplementedException();
	}

	public ReglaBonificacion findReglaBonificacion(Long idPromotion) {
		throw new NotImplementedException();
	}

	public Double findVolumenReglaPromocionBonificacion(Long idPromotion) {
		throw new NotImplementedException();
	}

	public Double findVolumenReglaPromocionTasa(Long idPromotion, Double importe) {
		throw new NotImplementedException();
	}

}
