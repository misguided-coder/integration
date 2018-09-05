package com.example.timer;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class SimpleDemo {

	public static void main(String s[]) throws Exception {
		new SimpleDemo();
	}

	public SimpleDemo() throws Exception {

		CamelContext camelContext = new DefaultCamelContext();

		camelContext.addRoutes(new IntegrationRouteBuilder());

		camelContext.start();

		System.in.read();

		camelContext.stop();

	}

	class IntegrationRouteBuilder extends RouteBuilder {

		public void configure() throws Exception {
			
			from("timer://TimeClock?period=5000&repeatCount=10")
				.log("Clock is ticking!!!")
				.setBody().simple("Event Message - ${header.firedTime}")
				.to("activemq:EventQ");	
			
		}

	}
}
