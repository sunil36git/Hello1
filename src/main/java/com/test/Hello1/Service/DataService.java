package com.test.Hello1.Service;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class DataService {

	@Value("${HELLO2_service_uri}")
	String hello2_service_uri;
	
	@Value("${HELLO_MSG}")
	String hello_msg;

	private final Logger logger = LogManager.getLogger();

	RestTemplate restTemplate = new RestTemplate();


	/**
	 * To reply with greeting MSG.
	 * 
	 * @param msg
	 *            A variable of type String.
	 * @return String
	 */
	public String getGreeting() {
		logger.info("Sending greeting from Service.getGreeting method.");
		return hello_msg;
	}
	
	/**
	 * To reply with greeting MSG.
	 * 
	 * @param msg
	 *            A variable of type String.
	 * @return String
	 * @throws UnknownHostException 
	 */
	public String getGreeting(String name) throws UnknownHostException {
		
		logger.info("Sending greeting from Service.getGreeting method.");
		String hello2response = callHello2(name);
		
		logger.info("Response reeseived from Hello2 = "+ hello2response);
		
		return "Hi  "+name+" " + "  ======>>>  (" + hello_msg + "< IP : "+InetAddress.getLocalHost()+ " > ======>>> "+ hello2response;
	}
	private String callHello2(String name)
	{
	    final String uri = hello2_service_uri+name;

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);

	    System.out.println(result);
	    return result;
	}
}
