package com.example.eip.xpath;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BooksOrderProcessor implements Processor {
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String orderContent = exchange.getIn().getBody(String.class);
		System.out.println("Books Order has been accepted and processed successfully!");
		String orderId = ""+System.currentTimeMillis();
		exchange.getOut().setBody("Order ID is "+orderId);
	}
}
