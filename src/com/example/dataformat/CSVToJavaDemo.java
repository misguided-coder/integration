package com.example.dataformat;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.DataFormat;

public class CSVToJavaDemo {

	public static void main(String[] args) throws Exception {
		new CSVToJavaDemo();

	}

	public CSVToJavaDemo() throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.setTracing(true);

		context.addRoutes(new DynamicRouterBuilder());
		context.start();
		System.in.read();

		context.stop();
		System.out.println("Done!");

	}

	class DynamicRouterBuilder extends RouteBuilder {

		DataFormat bindy = new BindyCsvDataFormat(Car.class);

		public void configure() {
			from("file://C:/camel-workspace/integration/src/com/example/dataformat?noop=true")
					.tracing()
					.filter(header(Exchange.FILE_NAME).endsWith(".csv"))
							.split().tokenize("\n")
							.to("stream:out")
							.unmarshal(bindy)
							.log("Done")
							.to("stream:out");
		}
	}

}
