package it.unipv.ingsw.progettoe20.server.model;

import it.unipv.ingsw.progettoe20.server.admin.model.priceType;

public class Price {

	private int minutes;
	private double price;
	private priceType type;

	public Price(int minutes, double price, priceType type) {
		this.minutes = minutes;
		this.price = price;
		this.type = type;
	}

	public int getMinutes() {
		return minutes;
	}

	public double getPrice() {
		return price;
	}

	public priceType getType() {
		return type;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setType(priceType type) {
		this.type = type;
	}
}
