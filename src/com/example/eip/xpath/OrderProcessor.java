package com.example.eip.xpath;

public class OrderProcessor {

	public void processOrder(String content) throws Exception {
		System.out.println("Pizza Order has been accepted and processed successfully!");
		String orderId = "" + System.currentTimeMillis();
	}

	public void processBooks(String content) throws Exception {
		System.out.println("Book Order has been accepted and processed successfully!");
		String orderId = "" + System.currentTimeMillis();
	}

	
	/*
	 * public void processOrder(Exchange exchange) throws Exception { String
	 * orderContent = exchange.getIn().getBody(String.class); System.out.
	 * println("Pizza Order has been accepted and processed successfully!"); String
	 * orderId = ""+System.currentTimeMillis();
	 * exchange.getOut().setBody("Order ID is "+orderId); }
	 */
}
