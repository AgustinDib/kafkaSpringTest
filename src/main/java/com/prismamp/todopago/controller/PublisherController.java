package com.prismamp.todopago.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prismamp.todopago.service.PublisherService;

@RestController
public class PublisherController {

	@Autowired
	PublisherService service;

	@RequestMapping(method = RequestMethod.POST, value = "message/{message}")
	public void publishMessage(@PathVariable("message") String message) {

		service.sendMessage(message);
	}
}
