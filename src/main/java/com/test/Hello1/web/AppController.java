package com.test.Hello1.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@RequestMapping
public class AppController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	private final Logger logger = LogManager.getLogger();
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


	@RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
	public String sayHi(@PathVariable String name) throws JsonProcessingException
	{
		logger.info("Request received for name..." + name);
		logger.info("Response sent for name..." + name);
		return "Hello bbb.... "+name;

	}
	
}
