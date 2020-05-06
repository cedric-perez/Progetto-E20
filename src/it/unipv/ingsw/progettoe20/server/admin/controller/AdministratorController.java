package it.unipv.ingsw.progettoe20.server.admin.controller;

import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;

/*
 * Controller per la gestione per l'Administrator.
 */
public class AdministratorController {

	private final AdministratorGUI gui;

	public AdministratorController(AdministratorGUI gui) {
		this.gui = gui;
		initComponents();
	}

	public void initComponents() {
		AdministratorListener adminlistener = new AdministratorListener(gui);
		gui.getBtnParkings().addActionListener(adminlistener);
		gui.getBtnPrices().addActionListener(adminlistener);
	}

}
