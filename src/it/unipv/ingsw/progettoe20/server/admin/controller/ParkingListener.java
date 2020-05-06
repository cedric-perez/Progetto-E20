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
		admin = new Administrator();
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

	// legge il numero inserito nel field
	public int enteredNumber() {
		String str = gui.getField().getText();
		int number = 0;
		if (str.equals("")) {
			JOptionPane.showMessageDialog(null, "Please, enter the number", "Error", 1, null);
		} else {
			number = Integer.parseInt(str);
		}
		return number;
	}

	// modifica il numero dei parcheggi
	public void changeParkings() {
		String action = (String) gui.getCombo().getSelectedItem();
		int number = enteredNumber();
		if (action.equals("Add parkings")) { // aggiunge parcheggi
			int capacity = admin.addParkings(number);
			JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
		} else { // rimuove dei parcheggi
			int capacity = admin.removeParkings(number);
			JOptionPane.showMessageDialog(null, "available parkings: " + capacity, "Info", 1, null);
			if (capacity == -1) {
				JOptionPane.showMessageDialog(null, "You cannot remove more parkings than there are available", "Error",
						1, null);
			}
		}
	}
}
