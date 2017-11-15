package com.prismamp.todopago.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	RestClientImpl restClient;

	public ResponseEntity<Map<String, Banco>> findAllBanco() {
		String appId = "proxy";
		String endpointUrl = "/api/BSA/proxy/globales/banco";

		CommandConfiguration conf = new CommandConfiguration();
		conf.setFallbackEnabled(true);
		conf.setExecutionTimeoutEnabled(true);
		conf.setRequestCacheEnabled(false);
		conf.setExecutionTimeoutInMilliseconds(5000);
		conf.setCommandName("findAllBancoTimeoutFallback");
		ResponseEntity fallback = new ResponseEntity("This is a fallback response", HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity response = restClient.getForEntity(appId, endpointUrl, Map.class, conf, fallback);

		return response;
	}
}
