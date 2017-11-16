package com.prismamp.todopago.controller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private ProxyService service;

	@RequestMapping(method = RequestMethod.GET, value = "proxy/banco")
	public ResponseEntity<Map<String, Banco>> findAllBank() {

		return service.findAllBank();
	}

	@RequestMapping(method = RequestMethod.GET, value = "proxy/calculateAsync")
	public ResponseEntity<Long> calculateAsync() throws InterruptedException {

		return calculateAsyncTime();
	}

	private ResponseEntity<Long> calculateAsyncTime() throws InterruptedException {
		long start = System.currentTimeMillis();

		CompletableFuture<ResponseEntity<Map<String, Banco>>> total1 = service.asyncFindAllBank();
		CompletableFuture<ResponseEntity<Map<String, Banco>>> total2 = service.asyncFindAllBank();

		CompletableFuture.allOf(total1, total2).join();

		return new ResponseEntity<Long>(System.currentTimeMillis() - start, HttpStatus.OK);
	}
}
