/**
 * 
 */
package com.prismamp.todopago.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;

import com.prismamp.todopago.model.PlazoLiberacion;

/**
 * @description Clase que se encarga de interactuar con la DB para las consultas relacionadas con Cashout
 *
 */
public class CashoutRepository {
	
	
	public List<PlazoLiberacion> findByCuentaAndTipoDeMedioDePagoAndCanal(Long cuenta, Long tipoMedioPago, Date created, Long canal) {
		return findAll().stream().filter(p -> p.getIdCuenta().equals(cuenta))
				.filter(p -> p.getMedioPago().getTipoMedioPago().getId().equals(tipoMedioPago))
				.filter(p -> p.getCanal().getId().equals(canal))
				.filter(p -> p.getMedioPago().getFlagHabilitado() > 0)
				.filter(p -> p.getFechaAlta().before(created))
				.filter(p -> p.getFechaBaja().after(created) || p.getFechaBaja()==null)
				.collect(Collectors.toList());
	}

	
	public List<PlazoLiberacion> findByCuentaAndTipoDeMedioDePago(Long cuenta, Long tipoMedioPago, Date created) {
		return findAll().stream().filter(p -> p.getIdCuenta().equals(cuenta))
				.filter(p -> p.getMedioPago().getTipoMedioPago().getId().equals(tipoMedioPago))
				.filter(p -> p.getMedioPago().getFlagHabilitado() > 0)
				.filter(p -> p.getFechaAlta().before(created))
				.filter(p -> p.getFechaBaja().after(created) || p.getFechaBaja()==null)
				.collect(Collectors.toList());
	}
	
	
	public List<PlazoLiberacion> findByCanal(Long cuenta, Long tipoMedioPago, Date created, Long canal) {
		return findAll().stream().filter(p -> p.getIdCuenta().equals(cuenta))
				.filter(p -> p.getMedioPago().getTipoMedioPago().getId().equals(tipoMedioPago))
				.filter(p -> p.getCanal().getId().equals(canal))
				.filter(p -> p.getMedioPago().getFlagHabilitado() > 0)
				.filter(p -> p.getFechaAlta().before(created))
				.filter(p -> p.getFechaBaja().after(created) || p.getFechaBaja()==null)
				.collect(Collectors.toList());
	}
	
	
	public List<PlazoLiberacion> isTipoMedioPagoValid(Long tipoMedioPago) {
		return findAll().stream().filter(p -> p.getMedioPago().getTipoMedioPago().getId().equals(tipoMedioPago))
				.collect(Collectors.toList());
	}
	
	
	public List<PlazoLiberacion> findByActividadCuentaAndGrupoDeRubroAndTipoDeMedioDePago(Long cuenta, Long tipoMedioPago, Date created, Long canal) {
		return findAll().stream().filter(p -> p.getIdCuenta().equals(cuenta))
				.filter(p -> p.getMedioPago().getTipoMedioPago().getId().equals(tipoMedioPago))
				.filter(p -> p.getCanal().getId().equals(canal))
				.filter(p -> p.getMedioPago().getFlagHabilitado() > 0)
				.filter(p -> p.getFechaAlta().before(created))
				.filter(p -> p.getFechaBaja().after(created) || p.getFechaBaja()==null)
				.collect(Collectors.toList());
	}
		
	
	
	
	/** 
	 * @lastModification: Dec 5, 2017 - 12:19:51 PM
	 * @returnType Collection<PlazoLiberacion>
	 */
	private Collection<PlazoLiberacion> findAll() {
		throw new NotImplementedException();
	}

}
