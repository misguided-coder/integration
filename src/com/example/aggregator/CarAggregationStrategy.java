package com.example.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class CarAggregationStrategy implements AggregationStrategy{

	public Exchange aggregate(Exchange previousExchange, Exchange currentExchange) {

		if(previousExchange == null)
			return currentExchange;
		
		String previousContent = previousExchange.getIn().getBody(String.class);
		String currentContent = currentExchange.getIn().getBody(String.class);
		
		previousExchange.getIn().setBody(previousContent +"||"+currentContent);
		
		return previousExchange;
	}

		
}
