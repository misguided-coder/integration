package com.example.timer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {

		// Spring Container is started
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/example/timer/routes.xml");
		System.out.println("Spring IoC Container is started!!!!");
		
		//CamelContext camelContext = applicationContext.getBean(CamelContext.class);
		//ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		
		//ProducerTemplate producerTemplate = applicationContext.getBean(ProducerTemplate.class);
		//producerTemplate.sendBody("direct://News","Today is fun day");
		//producerTemplate.sendBody("direct://News","Weather is cool");
		
		
		System.in.read();
		System.out.println("Done!!!!!");
	}

}
