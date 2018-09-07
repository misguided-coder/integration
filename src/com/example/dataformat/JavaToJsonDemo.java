package com.example.dataformat;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class JavaToJsonDemo {

	public static void main(String[] args) throws Exception {
		new JavaToJsonDemo();

	}

	public JavaToJsonDemo() throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.setTracing(true);

		context.addRoutes(new DynamicRouterBuilder());
		context.start();
		
		Politician p1 = new Politician(1000,"Sonia",1200,"50000CR");
		Politician p2 = new Politician(2000,"Lalu",800,"400CR");

		ProducerTemplate template = context.createProducerTemplate();

		template.sendBody("direct:JavaData",p1);
		template.sendBody("direct:JavaData",p2);
		

		System.in.read();

		context.stop();
		System.out.println("Done!");

	}

	class DynamicRouterBuilder extends RouteBuilder {

		public void configure() throws Exception {

			from("direct:JavaData") 
				.marshal()
				.json() 
				.to("stream:out");

			
		
		}
	}

}
