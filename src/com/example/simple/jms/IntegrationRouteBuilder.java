package com.example.simple.jms;

import org.apache.camel.builder.RouteBuilder;

public class IntegrationRouteBuilder extends RouteBuilder {

	public void configure() throws Exception {

		from("file://C:/camel-workspace/integration/in?noop=true")
			.to("jms:queue:NewsQ");
	}

}
