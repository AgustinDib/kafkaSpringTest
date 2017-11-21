package com.prismamp.todopago.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.prismamp.todopago.model.Cargo;

@Repository
public class CargoRepository {

	@Cacheable("cargos")
	public List<Cargo> findAll() {
		return createCargos();
	}

	@Scheduled(cron = "0 0 0 * * *")
	@CacheEvict(value = "cargos", allEntries = true)
	public List<Cargo> updateAll() {
		return createCargos();
	}

	private List<Cargo> createCargos() {
		return Arrays.asList(new Cargo(), new Cargo());
	}
}
