package com.prismamp.todopago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
	
	public List<Cargo> findAll() {
		return repository.findAll();
	}
}
