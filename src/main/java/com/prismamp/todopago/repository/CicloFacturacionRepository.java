package com.prismamp.todopago.repository;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.prismamp.todopago.model.CicloFacturacion;

@Repository
public class CicloFacturacionRepository {

	/**
	 * Devuelve todos los ciclos de facturación existentes y los deja en el cache
	 * "ciclosFacturacion".
	 * 
	 * @return List de todos los ciclos de facturación.
	 */
	@Cacheable("ciclosFacturacion")
	public List<CicloFacturacion> findAll() {
		throw new NotImplementedException();
	}

	/**
	 * Devuelve todos los ciclos de facturación existentes y actualiza el cache
	 * "ciclosFacturacion".
	 * 
	 * @return List de todos los ciclos de facturación.
	 */
	@Scheduled(cron = "0 0 0 * * *")
	@CacheEvict(value = "ciclosFacturacion", allEntries = true)
	public List<CicloFacturacion> updateAll() {
		throw new NotImplementedException();
	}
}
