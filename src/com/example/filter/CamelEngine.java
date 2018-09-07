package com.example.filter;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelEngine {

	public static void main(String s[]) throws Exception {

		new CamelEngine();
	}

	public CamelEngine() throws Exception {

		CamelContext camelContext = new DefaultCamelContext();

		ActiveMQComponent component = new ActiveMQComponent();
		component.setBrokerURL("tcp://localhost:61616");

		camelContext.addComponent("activemq", component);
		
		camelContext.addRoutes(new IntegrationRouteBuilder());

		camelContext.start();

		System.in.read();

		camelContext.stop();

	}

	class IntegrationRouteBuilder extends RouteBuilder {

		public void configure() throws Exception {

			String fileSystemFromPath = "file://C:/camel-workspace/integration/in/splitter?noop=true";
			String messageLogFiltered = "file://C:/camel-workspace/integration/?noop=true&FileExist=Append";
			
			from(fileSystemFromPath)
				//Splitter EIP
				.split().tokenize("\n")
				.filter(body().contains("No")) //Filtered message log
						//.setBody().simple("Filtered Out ----- ${body}")
						.to(messageLogFiltered)	
				.filter(body().contains("Yes")) //Luxury Cars only
					.to("activemq:queue:LuxuryCarsQ");

		}

	}
}
