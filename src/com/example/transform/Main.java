package com.example.transform;

import org.apache.camel.ProducerTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/example/transform/routes.xml");
		
		ProducerTemplate producerTemplate = applicationContext.getBean(ProducerTemplate.class);
		producerTemplate.sendBody("direct://NewsContent1","Cricket is fun");
		producerTemplate.sendBody("direct://NewsContent2","Weather is cool");
		producerTemplate.sendBody("direct://NewsContent3","Life is cool");
		
		System.in.read();
		System.out.println("Done!!!!!");
	}

}
