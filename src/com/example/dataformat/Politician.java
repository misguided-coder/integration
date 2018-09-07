package com.example.dataformat;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Politician {

	int id;
	String name;
	int frauds;
	String assets;

	public Politician() {
	}

	public Politician(String name, int frauds, String assets) {
		this.name = name;
		this.frauds = frauds;
		this.assets = assets;
	}

	public Politician(int id, String name, int frauds, String assets) {
		this.id = id;
		this.name = name;
		this.frauds = frauds;
		this.assets = assets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFrauds() {
		return frauds;
	}

	public void setFrauds(int frauds) {
		this.frauds = frauds;
	}

	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}

}
