package com.example.simple.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelEngine { 
	
	public static void main(String s[]) throws Exception {
		
		IntegrationRouteBuilder integrationRoutes = new IntegrationRouteBuilder();
		
		//Camel Engine,Routing Engine/Mediation Engine
		CamelContext camelContext = new DefaultCamelContext();
	
		ActiveMQConnectionFactory factory = new  ActiveMQConnectionFactory("tcp://localhost:61616");
		JmsComponent jmsComponent = JmsComponent.jmsComponent(factory);
		
		//Register JMS Component and configure it here
		camelContext.addComponent("jms", jmsComponent);
		
		//Deploying routes inside engine
		camelContext.addRoutes(integrationRoutes);

		//Start Camel routing engine
		camelContext.start();
			
			Thread.sleep(1000*5);
			
		//Stop Camel routing engine
		camelContext.stop();

	}

}



  
   