package com.example.exception;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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

		ProducerTemplate template=camelContext.createProducerTemplate();
		template.sendBody("direct://GO","Go Home");
		template.sendBody("direct://GOHome","Go Go Home");
		
		System.in.read();

		camelContext.stop();

	}

	class IntegrationRouteBuilder extends RouteBuilder {

		public void configure() throws Exception {

			from("direct://GO")
					.doTry()
					.bean(new CalculatorService())
					.bean(new EmailService())
					.doCatch(ArithmeticException.class).process(new Processor() {
						public void process(Exchange exchange) throws Exception {
							System.out.println("ArithmeticException caught and handled!!!");
						}
					})
					.handled(true);
					//.to("direct://Errors");
			
			
			from("direct://GOHome")
				.bean(new CalculatorService())
				.bean(new EmailService())
				.to("stream:out");
			
			/*from("direct://Errors")
				.log("Error are handled on this route!!!!!")
				.log("${exception}")
				.to("stream:err");*/
					
		}

	}
}
