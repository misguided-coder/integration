package com.example.loadbalancer;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelEngine {

	public static void main(String s[]) throws Exception {

		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));
		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				from("timer://TimeClock?period=2s")
					.setBody().simple("Order generated at ${header.firedTime}")
					.to("direct://Orders");
									
				
				from("direct://Orders")
					.routeId("mainRoute")
					.log("Sending orders to other processors!!!!")
					.loadBalance() //Load Balancer EIP
						//.roundRobin()
						//.random()
						.weighted(false,"60,20,20")
							.to("direct://OrderQA")
							.to("direct://OrderQB")
							.to("direct://OrderQC");
								
				from("direct://OrderQA")
					.log("Processing message on  OrderQA!!!!")
					//.to("stream:err");
					.to("activemq:ProcessedOrderQ");
					
				from("direct://OrderQB")
					.log("Processing message on  OrderQB!!!!")
					//.to("stream:err");
					.to("activemq:ProcessedOrderQ");

				from("direct://OrderQC")
					.log("Processing message on  OrderQC!!!!")
					//.to("stream:err");
					.to("activemq:ProcessedOrderQ");

				
			}
		});

		camelContext.start();
		
		/*ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
	
		producerTemplate.sendBody("direct://Orders", "Five Laptops");
		producerTemplate.sendBody("direct://Orders", "Two Watches");
		producerTemplate.sendBody("direct://Orders", "Five LEDs");
		producerTemplate.sendBody("direct://Orders", "Five Laptops");
		producerTemplate.sendBody("direct://Orders", "Two Watches");
		producerTemplate.sendBody("direct://Orders", "Five LEDs");
		*/
			
		System.in.read();
		

		camelContext.stop();

	}

}
