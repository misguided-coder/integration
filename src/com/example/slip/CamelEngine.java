package com.example.slip;

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
					.routingSlip(header("receipentList")); //Slip Router EIP
							
				from("direct://stoneA")
					.setBody().simple("${body} - Touched Stone A")
					.log("Touching Stone A")
					.to("stream:err");
				
				from("direct://stoneB")
					.setBody().simple("${body} - Touched Stone B")
					.log("Touching Stone B")
					.to("stream:err");
	
				from("direct://stoneC")
					.setBody().simple("${body} - Touched Stone C")
					.log("Touching Stone C")
					.to("stream:err");
				
				/*from("activemq://stoneQ")
					.log("${body} - Touching Stone Q");*/
			
			}
		});

		camelContext.start();
		
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();

		producerTemplate.sendBodyAndHeader("direct://GO", "Touch all stones","receipentList","direct://stoneA,direct://stoneB,direct://stoneC");
		//producerTemplate.sendBodyAndHeader("direct://GO", "Touch all stones","receipentList","direct://stoneA,direct://stoneB");
		//producerTemplate.sendBodyAndHeader("direct://GO", "Touch all stones","receipentList","direct://stoneB,direct://stoneA");
		producerTemplate.sendBodyAndHeader("direct://GO", "Touch all stones","receipentList","direct://stoneA,activemq://stoneQ");
			
		System.in.read();
		

		camelContext.stop();

	}

}
