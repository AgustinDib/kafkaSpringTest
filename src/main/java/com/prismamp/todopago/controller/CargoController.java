package com.prismamp.todopago.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prismamp.todopago.model.Cargo;
import com.prismamp.todopago.service.CargoService;

@RestController
public class CargoController {

	@Autowired
	private CargoService service;

	@RequestMapping(method = RequestMethod.GET, value = "cargo")
	public List<Cargo> findAll() {

		return service.findAll();
	}
}
