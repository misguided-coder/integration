package com.example.timer;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelEngine {

	public static void main(String s[]) throws Exception {
		new CamelEngine();
	}

	public CamelEngine() throws Exception {

		CamelContext camelContext = new DefaultCamelContext();

		camelContext.addRoutes(new IntegrationRouteBuilder());

		camelContext.start();

		System.in.read();

		camelContext.stop();

	}

	class IntegrationRouteBuilder extends RouteBuilder {

		public void configure() throws Exception {
			
			from("timer://TimeClock?period=5000&repeatCount=2")
				.log("Clock is ticking!!!")
				//.log("${body}");
				.log("Message was generated at -  ${header.firedTime}");
				
				//.log("Message Body ---- ${body} ----- ${headers}");
			
			
		}

	}
}
