package it.unipv.ingsw.progettoe20.server.model;

public class Price {

	private int minutes;
	private double price;

	public Price(int minutes, double price) {
		this.minutes = minutes;
		this.price = price;
	}

	public int getMinutes() {
		return minutes;
	}

	public double getPrice() {
		return price;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
