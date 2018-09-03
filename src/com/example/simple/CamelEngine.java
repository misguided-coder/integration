package com.example.simple;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelEngine { 
	
	public static void main(String s[]) throws Exception {
		
		SimpleFileRouteBuilder integrationRoutes = new SimpleFileRouteBuilder();
		
		//Camel Engine,Routing Engine/Mediation Engine
		CamelContext camelContext = new DefaultCamelContext();

		//Deploying routes inside engine
		camelContext.addRoutes(integrationRoutes);

		//Start Camel routing engine
		camelContext.start();
			
			Thread.sleep(1000*5);
			
		//Stop Camel routing engine
		camelContext.stop();

	}

}



  
   