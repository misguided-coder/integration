package com.example.sql;

import org.apache.camel.ProducerTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {

	public static void main(String[] args) throws Exception{
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/example/sql/appCxt.xml");		
		
		
		ProducerTemplate producerTemplate = applicationContext.getBean(ProducerTemplate.class);
		User user=new User();
		user.setId(1);
		user.setFirstName("Ritesh");
		user.setLastName("Tyagi");
		user.setUserName("riteshtyagi");
		user.setEmail("hi2tyagi@yahoo.com");
		user.setAddress("Delhi");
		
		
		producerTemplate.sendBody("direct:start",user);
		
		
		//producerTemplate.sendBody("direct:start","Delhi");
	    //producerTemplate.sendBody("direct:start",new ArrayList(Arrays.asList("Delhi","Tyagi")));
	    System.out.println("Done!");
	    
	    Thread.sleep(1000*8);
	    System.exit(0);
		//System.out.println("Done!");

	}
}
