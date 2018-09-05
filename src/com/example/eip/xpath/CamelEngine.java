package com.example.eip.xpath;

import static org.apache.activemq.camel.component.ActiveMQComponent.*;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelEngine {

	Logger logger = LoggerFactory.getLogger(CamelEngine.class);

	public static void main(String s[]) throws Exception {
		new CamelEngine();
	}

	public CamelEngine() throws Exception {

		//JndiContext jndiContext = new JndiContext();
		//jndiContext.bind("orderProcessor", new OrderProcessor());

		CamelContext camelContext = new DefaultCamelContext();
		
		//ActiveMQComponent component = new ActiveMQComponent();
		//component.setBrokerURL("tcp://localhost:61616");

		camelContext.addComponent("activemq",activeMQComponent("tcp://localhost:61616"));
		
		camelContext.addRoutes(new IntegrationRouteBuilder());

		camelContext.start();

		Thread.sleep(1000 * 2);

		camelContext.stop();

	}

	class IntegrationRouteBuilder extends RouteBuilder {

		public void configure() throws Exception {

			String fileSystemFromPath = "file://in/order?noop=true";
			
			/*from(fileSystemFromPath)
				.choice()
					.when(body().convertToString().contains("books"))
							.to("activemq:queue:BooksOrderQ")
					.when(body().convertToString().contains("pizzaz"))
								.to("activemq:queue:PizzaOrderQ")
			*/
			
			from(fileSystemFromPath)
			.choice() //CBR should be used here
				.when(xpath("/order/@type = 'books'")) //Condition 1
						.to("activemq:queue:BooksOrderQ")
				.when(xpath("/order/@type = 'pizzaz'")) //Condition 2
							.to("activemq:queue:PizzaOrderQ")
			.end();
			
			from("activemq:BooksOrderQ")
				.log("Processing Books orders!!!!")
				//.bean(BooksOrderProcessor.class)
				.bean(OrderProcessor.class,"processBooks")
				.to("log:${body}")
				.log("${body}");

			from("activemq:PizzaOrderQ")
			.log("Processing Pizza orders!!!!")
			//.to("bean:orderProcessor?method=processOrder"); //Camel Component URI
			//.bean(OrderProcessor.class);
			.bean(OrderProcessor.class,"processOrder")
			.log("${body}");
			
		}
		

	}
}
