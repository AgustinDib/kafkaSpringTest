package com.prismamp.todopago.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prismamp.todopago.model.Banco;
import com.prismamp.todopago.service.ProxyService;

/**
 * Clase con la única finalidad de testear la conexión a microservicios provista
 * por ms-seed.
 * 
 * @author Agustin Dib
 *
 */
@RestController
public class ProxyController {

	@Autowired
	ProxyService service;

	@RequestMapping(method = RequestMethod.GET, value = "proxy/banco")
	public ResponseEntity<Map<String, Banco>> findAllBanco() {

		return service.findAllBanco();
	}
}
