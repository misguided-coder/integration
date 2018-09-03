package com.example.simple.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class LogProcessor implements Processor  {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Message inFlowPessage = exchange.getIn();
		
		System.out.println("BODY : "+inFlowPessage.getBody());
		
		System.out.println("LogProcessor logged the activity!!!!");		
		
	}

}
