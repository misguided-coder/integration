package com.example.eip.direct;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ResultWriteDemo {

	public static void main(String s[]) throws Exception {
		new ResultWriteDemo();
	}

	public ResultWriteDemo() throws Exception {

		CamelContext camelContext = new DefaultCamelContext();

		camelContext.addComponent("activemq", activeMQComponent("tcp://localhost:61616"));
		camelContext.addRoutes(new IntegrationRouteBuilder());

		camelContext.start();

		
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate(); 
		producerTemplate.sendBodyAndHeader("activemq:CarsQ","Maruti Baleno","CarType","Sedan");
		producerTemplate.sendBodyAndHeader("activemq:CarsQ","BMW X1","CarType","Sedan");
		producerTemplate.sendBodyAndHeader("activemq:CarsQ","BMW X6","CarType","SUV");
		producerTemplate.sendBodyAndHeader("activemq:CarsQ","Fortuner","CarType","SUV");
		
		
		System.in.read();

		camelContext.stop();

	}

	class IntegrationRouteBuilder extends RouteBuilder {

		public void configure() throws Exception {

			from("activemq:CarsQ")
				.choice()
					.when(header("CarType").isEqualTo("Sedan")) //Simple Language
						.to("direct://SedanCars") 
					.when(header("CarType").isEqualTo("SUV")) //Simple Language
						.to("direct://SUVCars") 
					.when(header("CarType").isEqualTo("Luxury")) //Simple Language
						.to("direct://LuxuryCars")
					.otherwise()
						.to("direct://NewCars")
					.end();
			
			from("direct://SedanCars")
				.log("Processing Sedan Cars!!!!! ${headers}")
				.bean(CarProcessor.class)
				//.setHeader("CamelFileName",constant("SedanCars.txt"))
				.setHeader("CamelFileName").simple("SedanCars-${date:now:day-hh-mm-ss}.txt")
				.to("file://C:\\camel-workspace\\integration\\out?fileExist=Append");
						
			from("direct://SUVCars")
				.log("Processing SUV Cars!!!!! ${headers}")
				.bean(CarProcessor.class)
				//.setHeader("CamelFileName",constant("SUVCars.txt"))
				.setHeader("CamelFileName").simple("SUVCars-${date:now:day-hh-mm-ss}.txt")
				.to("file://C:\\camel-workspace\\integration\\out?fileExist=Append");
			
			from("direct://LuxuryCars")
				.log("Processing Luxury Cars!!!!!")
				.bean(CarProcessor.class);

			from("direct://NewCars")
				.log("Processing New Cars!!!!!")
				.bean(CarProcessor.class);

			
		}

	}
}
