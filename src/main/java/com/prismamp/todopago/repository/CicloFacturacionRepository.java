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

	@Cacheable("ciclosFacturacion")
	public List<CicloFacturacion> findAll() {
		throw new NotImplementedException();
	}

	@Scheduled(cron = "0 0 0 * * *")
	@CacheEvict(value = "ciclosFacturacion", allEntries = true)
	public List<CicloFacturacion> updateAll() {
		throw new NotImplementedException();
	}
}
