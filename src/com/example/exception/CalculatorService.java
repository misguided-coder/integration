package com.example.exception;

public class CalculatorService  {

	public void doCal(String message) {
		System.out.println("CalculatorService - Calcutions are done!!!!");
		int rs = 10/0;
		System.out.println("Result is : "+rs);
		
	}

}
