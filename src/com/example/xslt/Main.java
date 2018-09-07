package com.example.xslt;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

	public static void main(String[] args) throws Exception {
		new Main();

	}

	public Main() throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.setTracing(true);

		context.addRoutes(new DynamicRouterBuilder());
		context.start();

		System.in.read();

		context.stop();
		System.out.println("Done!");

	}

	class DynamicRouterBuilder extends RouteBuilder {

		public void configure() throws Exception {

			from("file://C:/camel-workspace/integration/src/com/example/xslt?noop=true")
					.tracing()
					.filter(header(Exchange.FILE_NAME).endsWith(".xml"))
					.to("stream:out")
					.to("xslt:C:/camel-workspace/integration/src/com/example/xslt/employee.xsl")
					.to("stream:out");
		}
	}

}
