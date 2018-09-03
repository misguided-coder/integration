package com.example.simple.processor;

import org.apache.camel.builder.RouteBuilder;

public class SimpleFileRouteBuilder extends RouteBuilder { 
	
	@Override
	public void configure() throws Exception {
		
		//Route 1 
 		//integration route from file system to file system 
		
		String fileSystemFromPath = "file://C:/camel-workspace/integration/in?noop=true";
		String fileSystemToPath = "file://C:/camel-workspace/integration/out";
		
		LogProcessor logProcessor = new LogProcessor();
		
		//Endpoint			//Pipeline processor	//Endpoint
		from(fileSystemFromPath).process(logProcessor).to(fileSystemToPath);
		
	}

}


  
   