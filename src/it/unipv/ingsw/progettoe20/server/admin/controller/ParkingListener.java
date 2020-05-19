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
		if (event.getActionCommand().equals("Home")) { // se si preme home
			changeGUI();
		} else { // se si preme confirm
			changeParkings();
		}
	}

	// torna a AdministratorGUI
	public void changeGUI() {
		AdministratorGUI adminGUI = new AdministratorGUI();
		AdministratorController admincontroller = new AdministratorController(adminGUI);
		gui.setVisible(false);
		adminGUI.setVisible(true);
	}

	// legge il nome del livello inserito nel field1
	public String enteredLevel() {
		String level = gui.getField().getText();
		if (level.equals("")) {
			JOptionPane.showMessageDialog(null, "Please, enter the level", "Error", 1, null);
		}
		return level;
	}

	// legge il numero di parcheggi da aggiunger/togliere inserito in field2
	public int enteredParkingLots() {
		String parkingLots = gui.getField2().getText();
		int number = 0;
		if (parkingLots.equals("")) {
			JOptionPane.showMessageDialog(null, "Please, enter the parking lots", "Error", 1, null);
		} else {
			number = Integer.parseInt(parkingLots);
		}
		return number;
	}

	// modifica il numero dei parcheggi
	public void changeParkings() {
		String action = (String) gui.getCombo().getSelectedItem();
		String level = enteredLevel();
		int number = enteredParkingLots();
		if (action.equals("Add parkings")) { // aggiunge parcheggi
			int capacity = admin.addParkings(number, level);
			JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
		} else { // rimuove dei parcheggi
			int capacity = admin.removeParkings(number, level);
			JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
			if (capacity == -1) {
				JOptionPane.showMessageDialog(null, "You cannot remove more parkings than there are available", "Error",
						1, null);
			}
		}
	}
}
