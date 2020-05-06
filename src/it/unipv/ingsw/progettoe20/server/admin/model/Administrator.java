package it.unipv.ingsw.progettoe20.server.admin.model;

/*
 * Classe principale per la gestione dell'interfaccia dell'amministratore.
 * Può aggiungere o rimuovere parcheggi e modificare le tariffe.
 */

public class Administrator {

	private int capacity = 0;
	private double hourlyprice = 0;
	private double maxprice = 0;
	private double minprice = 0;

	// aggiunge nuovi posti al parcheggio
	public int addParkings(int n) {
		capacity += n;
		return capacity;
	}

	// toglie posti al parcheggio
	public int removeParkings(int n) {
		if (capacity - n >= 0) {
			capacity -= n;
			return capacity;
		} else {
			return -1; // se si tolgono più parcheggi di quelli presenti restituisce -1
		}
	}

	// modifica le tariffe
	public void changePrice(double newprice, priceType type) {
		switch (type) {
		case HOURLYPRICE:
			hourlyprice = newprice;
			break;
		case MAXIMUMPRICE:
			maxprice = newprice;
			break;
		case MINIMUMPRICE:
			minprice = newprice;
			break;
		}

	}
}
