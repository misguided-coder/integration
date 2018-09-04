package com.example.simple.jms.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {

		// Spring Container is started
		new ClassPathXmlApplicationContext("com/example/simple/jms/spring/routes.xml");
		System.out.println("Spring IoC Container is started!!!!");
		System.in.read();
		System.out.println("Done!!!!!");
	}

}
