package com.example.spring.dsl.sp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {

		// Spring Container is started
		new ClassPathXmlApplicationContext("com/example/spring/dsl/sp/routes.xml");
		System.out.println("Spring IoC Container is started!!!!");
		System.in.read();
		System.out.println("Done!!!!!");
	}

}
