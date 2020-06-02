package it.unipv.ingsw.progettoe20.server.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.progettoe20.server.admin.model.Administrator;
import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.admin.view.ParkingManagementGUI;

/*
 *Listener che controlla le operazioni effettuate nella schermata del ParkingManagementGUI
 *Aggiunge o rimuove dei parcheggi.
 */
public class ParkingListener implements ActionListener {

	private ParkingManagementGUI gui;
	private Administrator admin;

	public ParkingListener(ParkingManagementGUI gui) {
		this.gui = gui;
		admin = Administrator.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Home")) {
			// Se preme il bottone di home
			changeGUI();
		} else {
			// Se preme il bottone di confirm
			changeParkings();
		}
	}

	/*
	 * Torna alla GUI dell'Administrator
	 *
	 */
	public void changeGUI() {
		AdministratorGUI adminGUI = new AdministratorGUI();
		gui.setVisible(false);
		adminGUI.setVisible(true);
	}

	/*
	 * Legge il nome del livello inserito nel JTextField
	 *
	 * @return name nome livello richiesto
	 *
	 */
	public String enteredLevel() {
		String name = gui.getField().getText();

		if (name.equals("")) {
			// Se non viene inserito nessun nome
			JOptionPane.showMessageDialog(null, "Please, enter the level", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the level name");
		}

		return name;
	}

	/*
	 * Legge il numero di parcheggi inseriti nel JTextField
	 *
	 * @return number numero di parcheggi inseriti
	 *
	 */
	public int enteredParkingLots() {
		String parkingLots = gui.getField2().getText();
		int number = 0;

		if (parkingLots.equals("")) {
			// Se non viene inserito nessun numero
			JOptionPane.showMessageDialog(null, "Please, enter the parking lots", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the parking lots");
		}

		number = Integer.parseInt(parkingLots);
		return number;
	}

	/*
	 * Modifica il numero di parcheggi in base a quanto scelto nella combo box
	 *
	 */
	public void changeParkings() {
		String action = (String) gui.getCombo().getSelectedItem();
		String name = enteredLevel();
		int number = enteredParkingLots();

		if (action.equals("Add parkings")) {
			// Aggiunge parcheggi
			int capacity = admin.addParkings(name, number);
			JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
		} else {
			// Rimuove dei parcheggi
			int capacity = admin.removeParkings(name, number);
			JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
		}
	}
}
