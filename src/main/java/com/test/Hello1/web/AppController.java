package com.test.Hello1.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.test.Hello1.Service.DataService;

@RestController
@RequestMapping
public class AppController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	private final Logger logger = LogManager.getLogger();
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	
	@Autowired
	private DataService service;
	
	@RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
	public String callHello2(@PathVariable String name) throws JsonProcessingException
	{
		logger.info("Request received for name..." + name);
		String finalresponse = service.getGreeting(name);
		logger.info("Sending finalresponse  ==  " + finalresponse);
		return finalresponse;
				
	}	
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String justhello1(@PathVariable String name) throws JsonProcessingException
	{
		logger.info("Request received for name..." + name);
		
		logger.info("Sending response just from Hello1");
		return "Hi  "+name+" " + service.getGreeting();

				
	}	
}
