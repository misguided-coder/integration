package com.example.eip;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

	public static void main(String[] args) throws Exception {
		new Main();
	}

	public Main() throws Exception {

		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		JmsComponent jmsComponent = JmsComponent.jmsComponent(factory);

		CamelContext camelContext = new DefaultCamelContext();

		camelContext.addComponent("activemq", jmsComponent);

		camelContext.addRoutes(new IntegrationRoute());

		camelContext.start();

		Thread.sleep(1000 * 5);

		// Stop Camel routing engine
		camelContext.stop();
	}

	class IntegrationRoute extends RouteBuilder {

		// CBR Demo (Content Based Router)
		public void configure() throws Exception {

			String fileSystemFromPath = "file://C:/camel-workspace/integration/in?noop=true";
		
			from(fileSystemFromPath)
				.choice()
					.when(body().contains("Weather"))
						.to("activemq:WeatherQ")
					.when(body().contains("Cricket"))
							.to("activemq:CricketQ")
					.when(body().contains("Movie"))
							.to("activemq:MoviesQ")
					.otherwise()
							.to("activemq:InfoQ");

		}

	}

}
