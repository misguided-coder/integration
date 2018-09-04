package com.example.eip;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class MessageProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("Inside MessageProcessor!!!!!");
		System.out.println(exchange);
		Message message = exchange.getIn();
		System.out.println(message.getBody());
		System.out.println(message.getHeaders());
		message.setHeader("Author", "Ritesh");
		exchange.getIn().setBody("Life is cool.");
		
	}

}
