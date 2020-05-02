package it.unipv.ingsw.progettoe20.client.enterColumn.model;

public class EnterColumn {
	private int availlability=100;
	public EnterColumn() {
		
	}
	public String generateTicket() {
		return "CODICEDIPROVA";
		
	}
	public int getAvailability() {
		return this.availlability;
	}
	public void reduceAvailability() {
		this.availlability--;
	}
}
