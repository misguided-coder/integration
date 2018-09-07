package com.example.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class UserTranslator implements Processor{
	
	public void process(Exchange exchange) throws Exception {
		
		User user=exchange.getIn().getBody(User.class);
		List<String> list=new ArrayList<String>();
		list.add(user.getAddress());
		list.add(user.getLastName());
		
		exchange.getIn().setBody(list);
	}

}
