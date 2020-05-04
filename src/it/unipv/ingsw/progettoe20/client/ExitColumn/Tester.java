package it.unipv.ingsw.progettoe20.client.ExitColumn;

import it.unipv.ingsw.progettoe20.client.ExitColumn.Controller.ExitColumnController;
import it.unipv.ingsw.progettoe20.client.ExitColumn.Model.ExitColumn;
import it.unipv.ingsw.progettoe20.client.ExitColumn.View.ExitColumnGUI;

import javax.swing.*;

//Tester per il client ExitColumn

public class Tester {

    public static void main(String[] args) {

        ExitColumnGUI gui = new ExitColumnGUI();
        ExitColumn model = new ExitColumn();
        ExitColumnController controller = new ExitColumnController(gui, model);
        gui.setVisible(true);

    }
}
