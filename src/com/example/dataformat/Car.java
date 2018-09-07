package com.example.dataformat;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",")
public class Car implements Serializable {

	@DataField(pos = 1)
	String vin;

	@DataField(pos = 2)
	String model;

	@DataField(pos = 3)
	String make;

	@DataField(pos = 4)
	double price;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [vin=" + vin + ", model=" + model + ", make=" + make + ", price=" + price + "]";
	}

}