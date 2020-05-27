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
		if (event.getActionCommand().equals("Home")) {
			// Se preme il bottone di home
			changeGUI();
		} else {
			// Se preme il bottone di confirm
			changeLevel();
		}
	}

	/*
	 * Modifica i livelli in base a quanto scelto nella combo box
	 *
	 */
	private void changeLevel() {
		String action = (String) gui.getCombo().getSelectedItem();
		String name = enteredLevel();

		if (action.equals("Add level")) {
			// Aggiunge un livello
			int number = enteredParkingLots();
			admin.addLevel(name, number);
			JOptionPane.showMessageDialog(null, "Level " + name + " added", "Info", 1, null);
		} else {
			// Rimuove un livello
			admin.removeLevel(name);
			JOptionPane.showMessageDialog(null, "Level " + name + " removed", "Info", 1, null);

		}
	}

	/*
	 * Legge il numero di parcheggi inseriti nel JTextField
	 *
	 * @return number numero di parcheggi inseriti
	 *
	 */
	private int enteredParkingLots() {
		String str = gui.getParkingLots().getText();
		int number = 0;

		if (str.equals("")) {
			// Se non viene inserito nessun numero
			JOptionPane.showMessageDialog(null, "Please, enter the parking lots", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the parking lots");
		}

		number = Integer.parseInt(str);
		return number;
	}

	/*
	 * Legge il nome del livello inserito nel JTextField
	 *
	 * @return name nome livello richiesto
	 *
	 */
	private String enteredLevel() {
		String name = gui.getLevelname().getText();

		if (name.equals("")) {
			// Se non viene inserito nessun nome
			JOptionPane.showMessageDialog(null, "Please, enter the level name", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the level name");
		}

		return name;
	}

	/*
	 * Torna alla GUI dell'Administrator
	 *
	 */
	public void changeGUI() {
		AdministratorGUI adminGUI = new AdministratorGUI();
		AdministratorController admincontroller = new AdministratorController(adminGUI);
		gui.setVisible(false);
		adminGUI.setVisible(true);
	}

}
