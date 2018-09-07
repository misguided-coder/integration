package com.example.cxf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {

	public static void main(String[] args) throws Exception{
		
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/example/cxf/appCxt.xml");		
		System.out.println("Done!");
	    
	    Thread.sleep(1000*8);
	    System.exit(0);
		//System.out.println("Done!");

	}
}
