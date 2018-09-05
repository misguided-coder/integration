package com.example.transform;

import org.apache.camel.Exchange;

public class MessageTransformer  {

	public String insertPipe(Exchange exchange) throws Exception {
		StringBuilder builder = new StringBuilder();
		System.out.println("Inside MessageTransformer!!!!!");
		String content = exchange.getIn().getBody(String.class);
		String[] resultArr = content.split(" ");
		for(String data : resultArr) {
			builder.append(data);
			builder.append("|");
		}
		return builder.toString();
	}
	
}
