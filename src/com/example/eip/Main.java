package com.example.eip;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
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

		camelContext.addRoutes(new LoopRoute());

		camelContext.start();

		ProducerTemplate template=camelContext.createProducerTemplate();
		//template.sendBodyAndHeader("direct:GO","Go Home", "count", 10);
				
		
		Thread.sleep(1000 * 5);

		// Stop Camel routing engine
		camelContext.stop();
	}


class LoopRoute extends RouteBuilder{
	
	public void configure() throws Exception {
	
		from("direct:GO")
			.loop(4)
			//.loop(header("count"))
			.process(new MessageProcessor());
		
		
	}

}
	
	class IntegrationRoute extends RouteBuilder {

		// CBR Demo (Content Based Router)
		public void configure() throws Exception {

			String fileSystemFromPath = "file://C:/camel-workspace/integration/in?noop=true";
			
			//Consumer Endpoint EIP
			from(fileSystemFromPath)
				.process(new MessageProcessor())
				.log("Body Content =============  ${body}")
				.log("Body Content =============  ${headers}")
						
				//CBR EIP
				.choice()
					.when(body().convertToString().contains("Weather"))
						.to("activemq:WeatherQ")
					.when(body().convertToString().contains("Cricket"))
							.to("activemq:CricketQ")
					.when(body().convertToString().contains("Movie"))
							.to("activemq:MoviesQ")
					.otherwise()
							.to("activemq:InfoQ");

		}

	}

}
