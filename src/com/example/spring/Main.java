package com.example.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		
		//Spring Container is started
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/example/spring/appCxt.xml");		
		
		System.out.println("Spring IoC Container is started!!!!");
				
		Thread.sleep(5000);
		
		//Spring Container is shutdown
		applicationContext.close();
		
	}
	
}
