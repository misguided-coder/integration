package com.example.eip.direct;

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

		camelContext.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));
		camelContext.addRoutes(new IntegrationRouteBuilder());

		camelContext.start();

		
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate(); 
		producerTemplate.sendBodyAndHeader("direct://GO","Life is cool","Info","Yes");
		producerTemplate.sendBodyAndHeader("direct://GO","Life is beautiful","Info","Yes");
		producerTemplate.sendBodyAndHeader("direct://GO","Life is to enjoy","Info","No");
		producerTemplate.sendBodyAndHeader("direct://GO","Life is superb","Info","No");
		
		System.in.read();

		camelContext.stop();

	}

	class IntegrationRouteBuilder extends RouteBuilder {

		public void configure() throws Exception {
			// URI -- componentName:systemName:scheme?options
			from("direct://GO")
				.to("activemq:SomeDataQ");
		}

	}
}
