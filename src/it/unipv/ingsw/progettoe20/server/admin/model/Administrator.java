package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseManager;

/*
 * Classe principale per la gestione dell'interfaccia dell'amministratore.
 * Può aggiungere o rimuovere parcheggi e modificare le tariffe.
 */

public class Administrator {

	private double hourlyprice = 0;
	private double maxprice = 0;
	private double minprice = 0;

	private static Administrator instance;
	private DatabaseManager databaseManager;

	// Singleton
	private Administrator(final DatabaseManager pDatabaseManager) {
		databaseManager = pDatabaseManager;
	}

	// per ottenere l'istanza dell'amministratore
	public static Administrator getInstance() {
		return instance;
	}

	// crea l'instanza dell'amministratore
	public static void create(final DatabaseManager pDatabaseManager) {
		instance = new Administrator(pDatabaseManager);
	}

	// aggiunge nuovi posti al parcheggio
	public int addParkings(int n, String level) {
		int capacity = getCapacityByLevel(level);
		capacity += n;
		return capacity;
	}

	// toglie posti al parcheggio
	public int removeParkings(int n, String level) {
		int capacity = getCapacityByLevel(level);
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

	public int getCapacityByLevel(String level) {
		// TODO Auto-generated method stub
		return 0;
	}

	// aggiunge un livello al parcheggio
	public void addLevel(String level, int capacity) {

	}

	// rimuove un livello al parcheggio se presente
	public void removeLevel(String level) {

	}
}
