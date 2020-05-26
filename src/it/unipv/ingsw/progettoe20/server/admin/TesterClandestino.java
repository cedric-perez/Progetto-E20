package it.unipv.ingsw.progettoe20.server.admin;

import it.unipv.ingsw.progettoe20.server.admin.controller.AdministratorController;
import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;

/**
 * Tester Administrator
 */
public class TesterClandestino {
    public static void main(String[] args){
        AdministratorGUI adming = new AdministratorGUI();
        AdministratorController admincon = new AdministratorController(adming);
    }

}
