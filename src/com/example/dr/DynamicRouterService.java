package com.example.dr;

import org.apache.camel.Exchange;
import org.apache.camel.Header;

public class DynamicRouterService {
	
	
	public String decideNextRouteByContent(String body, @Header(Exchange.SLIP_ENDPOINT) String previousRouter) {

		if (previousRouter == null) {
			return "direct://stoneA";
		} else if (body.contains("A")) {
			return "direct://stoneB";
		} else if (body.contains("B")) {
			return "direct://stoneC";
		} else {
			return null;
		}
	}

	public String decideNextRoute(String body, @Header(Exchange.SLIP_ENDPOINT) String previousRouter) {

		if (previousRouter == null) {
			return "direct://stoneA";
		} else if (previousRouter.equals("direct://stoneA")) {
			return "direct://stoneB";
		} else if (previousRouter.equals("direct://stoneB")) {
			return "direct://stoneC";
		} else {
			return null;
		}
	}

}
