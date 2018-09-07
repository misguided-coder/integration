package com.ibm.service;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

@WebService
public class PoliticianService{

	Map<Integer,String> names = null;
	Map<String,Integer> details = null;
	
	public PoliticianService(){
		names = new HashMap<Integer,String>();
		names.put(100,"Lalu");			
		names.put(200,"Sonia");			
		names.put(300,"ARaja");			
		names.put(400,"Sibbal");			
	
		details = new HashMap<String,Integer>();
		details.put("Lalu",560);			
		details.put("Sonia",320);			
		details.put("ARaja",800);			
		details.put("Sibbal",130);			
	
	}

	public String politicianName(int id){
		System.out.println("Inside politicianName("+id+")");
		String name = names.get(id);
		if(name==null)
			return "Data Not Found";
		else
			return name;
	}

	public int fraudCount(String name){
		System.out.println("Inside fraudCount("+name+")");
		int frauds = details.get(name);
		if(frauds == 0)
			return -1;
		else
			return frauds;
	}

}