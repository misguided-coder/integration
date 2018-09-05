package com.example.transform;

import org.apache.camel.ProducerTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/example/transform/routes.xml");
		
		ProducerTemplate producerTemplate = applicationContext.getBean(ProducerTemplate.class);
		producerTemplate.sendBody("direct://NewsContent","Cricket is fun");
		producerTemplate.sendBody("direct://NewsContent","Weather is cool");
		producerTemplate.sendBody("direct://NewsContent","Life is cool");
		
		System.in.read();
		System.out.println("Done!!!!!");
	}

}
