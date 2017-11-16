package com.prismamp.todopago.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.configuration.CommandConfiguration;
import com.prismamp.todopago.model.Banco;
import com.prismamp.todopago.restclient.impl.RestClientImpl;

/**
 * Servicio que hace uso de RestClient de ms-seed.
 * 
 * @author Agustin Dib
 *
 */
@Service
public class ProxyService {

	@Autowired
	private RestClientImpl restClient;

	private String app = "proxy";
	private String endpoint = "/api/BSA/proxy/globales/banco";

	public ResponseEntity<Map<String, Banco>> findAllBank() {
		CommandConfiguration conf = new CommandConfiguration();
		conf.setFallbackEnabled(true);
		conf.setExecutionTimeoutEnabled(true);
		conf.setRequestCacheEnabled(false);
		conf.setExecutionTimeoutInMilliseconds(5000);
		conf.setCommandName("findAllBankTimeoutFallback");

		ResponseEntity fallback = new ResponseEntity("Fallback response", HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity<Map<String, Banco>> response = restClient.getForEntity(app, endpoint, Map.class, conf, fallback);

		return response;
	}

	@Async
	public CompletableFuture<ResponseEntity<Map<String, Banco>>> asyncFindAllBank() throws InterruptedException {

		CommandConfiguration conf = new CommandConfiguration();
		conf.setFallbackEnabled(true);
		conf.setExecutionTimeoutEnabled(true);
		conf.setRequestCacheEnabled(false);
		conf.setExecutionTimeoutInMilliseconds(5000);
		conf.setCommandName("findAllBankTimeoutFallback");

		ResponseEntity fallback = new ResponseEntity("Fallback response", HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity<Map<String, Banco>> response = restClient.getForEntity(app, endpoint, Map.class, conf, fallback);

		Thread.sleep(5000L);
		return CompletableFuture.completedFuture(response);
	}

}
