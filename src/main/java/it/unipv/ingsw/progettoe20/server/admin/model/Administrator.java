package it.unipv.ingsw.progettoe20.server.admin.model;

import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import it.unipv.ingsw.progettoe20.server.model.Level;
import it.unipv.ingsw.progettoe20.server.model.Price;

/*
 * Classe principale per la gestione dell'interfaccia dell'amministratore.
 * Può aggiungere o rimuovere parcheggi e modificare le tariffe.
 */

public class Administrator {

	private double hourlyprice = 0;
	private double maxprice = 0;
	private double minprice = 0;

	private static Administrator instance;
	private DatabaseFacade databaseFacade;

	// Singleton
	private Administrator(final DatabaseFacade pDatabaseFacade) {
		databaseFacade = pDatabaseFacade;
	}

	/*
	 * Restituisce l'istanza dell'amministratore
	 */
	public static Administrator getInstance() {
		return instance;
	}

	/*
	 * Crea l'unica instanza dell'amministratore
	 */
	public static void create(final DatabaseFacade pDatabaseManager) {
		instance = new Administrator(pDatabaseManager);
	}

	/*
	 * Aggiunge nuovi posti al parcheggio
	 *
	 * @param name nome del livello a cui aggiungere i parcheggi
	 *
	 * @param n numbero di parcheggi da aggiungere
	 *
	 * @return newCapacity nuova disponibilità totale del livello
	 */
	public int addParkings(String name, int n) {
		Level level = databaseFacade.getLevelByName(name);
		int newCapacity = level.getTotal() + n;

		level.setTotal(newCapacity);
		databaseFacade.updateLevel(level);
		return newCapacity;
	}

	/*
	 * Toglie posti al parcheggio
	 *
	 * @param level nome del livello a cui togliere i parcheggi
	 *
	 * @param n numbero di parcheggi da togliere
	 *
	 * @return newCapacity nuova disponibilità totale del livello
	 */
	public int removeParkings(String name, int n) {
		Level level = databaseFacade.getLevelByName(name);
		int newCapacity = level.getTotal() - n;
		if (newCapacity < 0) {
			// Se cerca di togliere più parcheggi di quelli presenti
			throw new IllegalArgumentException("Impossible! You want to remove too many parking lots");
		} else {
			level.setTotal(newCapacity);
			databaseFacade.updateLevel(level);
		}
		return newCapacity;
	}

	/*
	 * Modifica le tariffe
	 *
	 * @param newprice nuovo prezzo
	 *
	 * @param type quale prezzo da modificare
	 */
	public void changePrice(double price, int minutes) {
		Price newPrice = new Price(minutes, price);
		databaseFacade.updatePrice(newPrice);
	}

	/*
	 * Aggiunge un livello al parcheggio
	 *
	 * @param level nome del nuovo livello
	 *
	 * @param capacity numbero di parcheggi dentro il nuovo livello
	 */
	public void addLevel(String name, int capacity) {
		Level newLevel = new Level(name, capacity);
		databaseFacade.updateLevel(newLevel);
	}

	/*
	 * Rimuove un livello al parcheggio se presente
	 *
	 * @param name nome del livello da togliere
	 */
	public void removeLevel(String name) {
		Level level = databaseFacade.getLevelByName(name);
		databaseFacade.removeLevel(level);
	}

}
