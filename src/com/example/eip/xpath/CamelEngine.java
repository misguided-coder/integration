package com.example.eip.xpath;

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

		Thread.sleep(1000 * 2);

		camelContext.stop();

	}

	class IntegrationRouteBuilder extends RouteBuilder {

		public void configure() throws Exception {

			String fileSystemFromPath = "file://C:/camel-workspace/integration/in/order?noop=true";
			
			/*from(fileSystemFromPath)
				.choice()
					.when(body().convertToString().contains("books"))
							.to("activemq:queue:BooksOrderQ")
					.when(body().convertToString().contains("pizzaz"))
								.to("activemq:queue:PizzaOrderQ")
			*/
			
			from(fileSystemFromPath)
			.choice()
				.when(xpath("/order/@type = 'books'"))
						.to("activemq:queue:BooksOrderQ")
				.when(xpath("/order/@type = 'pizzaz'"))
							.to("activemq:queue:PizzaOrderQ");
			
		}
		

	}
}
