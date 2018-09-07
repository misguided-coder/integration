package com.example.aggregator;

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
					
				from("direct://GO")
					.aggregate(header("Luxury"),new CarAggregationStrategy()) //Aggregator EIP
					.completionSize(10)
					.log("Displaying combined result!!!!")
					.to("stream:err");
					
			}
		});

		camelContext.start();
		
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
	
		producerTemplate.sendBodyAndHeader("direct://GO", "I am driving X6","Luxury","Yes");
		producerTemplate.sendBodyAndHeader("direct://GO", "I am driving Q7","Luxury","Yes");
		producerTemplate.sendBodyAndHeader("direct://GO", "I am driving Desire","Luxury","No");
		producerTemplate.sendBodyAndHeader("direct://GO", "I am driving Santro","Luxury","No");
		producerTemplate.sendBodyAndHeader("direct://GO", "I am driving Cruze","Luxury","Yes");
		producerTemplate.sendBodyAndHeader("direct://GO", "I am driving Nano","Luxury","No");
		
			
		System.in.read();
		

		camelContext.stop();

	}

}
