package it.unipv.ingsw.progettoe20.server.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.progettoe20.server.admin.model.Administrator;
import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.admin.view.LevelManagementGUI;

public class LevelListener implements ActionListener {

	private LevelManagementGUI gui;
	private Administrator admin;

	public LevelListener(LevelManagementGUI gui) {
		this.gui = gui;
		admin = Administrator.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Home")) { // se si preme home
			changeGUI();
		} else { // se si preme confirm
			changeLevel();
		}
	}

	// modifica i livelli
	private void changeLevel() {
		String action = (String) gui.getCombo().getSelectedItem();
		String level = enteredLevel();
		int number = enteredParkingLots();
		if (action.equals("Add level")) { // aggiunge un livello
			admin.addLevel(level, number);
		} else { // rimuove un livello
			admin.removeLevel(level);

		}
	}

	private int enteredParkingLots() {
		String str = gui.getParkingLots().getText();
		int number = 0;
		if (str.equals("")) {
			JOptionPane.showMessageDialog(null, "Please, enter the parking lots", "Error", 1, null);
		} else {
			number = Integer.parseInt(str);
		}
		return number;
	}

	// legge il nome del livello inserito nel levelname
	private String enteredLevel() {
		String level = gui.getLevelname().getText();
		if (level.equals("")) {
			JOptionPane.showMessageDialog(null, "Please, enter the level name", "Error", 1, null);
		}
		return level;
	}

	// torna a AdministratorGUI
	public void changeGUI() {
		AdministratorGUI adminGUI = new AdministratorGUI();
		AdministratorController admincontroller = new AdministratorController(adminGUI);
		gui.setVisible(false);
		adminGUI.setVisible(true);
	}

}
