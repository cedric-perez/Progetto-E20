package it.unipv.ingsw.progettoe20.server.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.admin.view.ParkingManagementGUI;
import it.unipv.ingsw.progettoe20.server.admin.view.PriceManagementGUI;

/*
 Listener che controlla quale bottone è stato schiacciato nella schermata dell'AdiministratorGUI
 Apre Parking Management oppure Price Management in base al bottone premuto
 */

public class AdministratorListener implements ActionListener {

	private final AdministratorGUI adminGUI;

	public AdministratorListener(AdministratorGUI gui) {
		this.adminGUI = gui;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Parking Management")) {
			ParkingManagementGUI parkingGUI = new ParkingManagementGUI();
			parkingGUI.setVisible(true);
			adminGUI.setVisible(false);
		} else if (event.getActionCommand().equals("Price Management")) {
			PriceManagementGUI priceGUI = new PriceManagementGUI();
			priceGUI.setVisible(true);
			adminGUI.setVisible(false);
		}

	}

}
