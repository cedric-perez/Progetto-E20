package it.unipv.ingsw.progettoe20.server.cli;

import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.admin.model.Administrator;
import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

class CommandHandler {
    static void handle(String command) {
        switch (command) {
            // A friendly pin
            case (CommandStrings.PING):
                System.out.println(CommandStrings.PONG);
                break;
            // Admin GUI requested
            case (CommandStrings.GUI):
                Administrator.create(DatabaseFacade.getInstance());
        		AdministratorGUI adminGui = new AdministratorGUI();
                break;
            // Server stop requested
            case (CommandStrings.EXIT):
                System.out.println(CommandStrings.EXIT_REQUESTED);
                Logger.close();
                System.exit(0);
        }
    }
}
