package it.unipv.ingsw.progettoe20.server.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.progettoe20.server.admin.model.Administrator;
import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.admin.view.PriceManagementGUI;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;

/*
 * Listener che che controlla le operazioni effettuate nella schermata del PriceManagementGUI
 * Modifica le tariffe disponibili
 */
public class PriceListener implements ActionListener {

	private final PriceManagementGUI gui;
	private final Administrator admin;

	public PriceListener(PriceManagementGUI gui) {
		this.gui = gui;
		admin = Administrator.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand().equals("Home")) {
			// Se si preme il bottone di home
			changeGUI();
		} else {
			// Se preme il bottone di confirm
			changePrices();
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
	 * Legge la nuova tariffa inserita nel JTextField
	 *
	 * @return number nuova tariffa inserita
	 *
	 */
	public double enteredNumber() {
		String str = gui.getField().getText();
		double number = 0.0;

		if (str.equals("")) {
			// Se non viene inserito nessun numero
			JOptionPane.showMessageDialog(null, "Please, enter the number", "Error", 1, null);
			throw new IllegalArgumentException("Impossible! Enter the number");
		}

		number = Double.parseDouble(str);
		return number;
	}

	/*
	 * Modifica i valori delle tariffe in base a quanto scelto nella combo box
	 */
	public void changePrices() {
		double newprice = enteredNumber();
		String action = (String) gui.getCombo().getSelectedItem();
		int minutes;

		if (action.equals("Hourly price")) {
			System.out.println("1");
			// Se si vuole modificare la tariffa oraria
			minutes = DBConstants.MINUTES_HOURLY;

		} else if (action.equals("Maximum price")) {
			// Se si vuole modificare la tariffa massima
			minutes = DBConstants.MINUTES_MAXIMUM;

		} else {
			// Se si vuole modificare la tariffa minima
			minutes = DBConstants.MINUTES_MINIMUM;

		}

		// Modifica della tariffa
		admin.changePrice(newprice, minutes);
		gui.getField().setText("");
		JOptionPane.showMessageDialog(null, "Price: " + newprice + " euro", "Info", 1, null);
	}
}
