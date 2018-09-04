package com.example.spring.dsl.java;

import org.apache.camel.builder.RouteBuilder;

public class FileIntegrationRouteBuilder extends RouteBuilder { 
	
	@Override
	public void configure() throws Exception {
		
		//Route 1 
 		//integration route from file system to file system 
		
		String fileSystemFromPath = "file://C:/camel-workspace/integration/in?noop=true";
		String fileSystemToPath = "file://C:/camel-workspace/integration/out";
		
		from(fileSystemFromPath).routeId("firstRoute").to(fileSystemToPath);
		
	}

}


  
   