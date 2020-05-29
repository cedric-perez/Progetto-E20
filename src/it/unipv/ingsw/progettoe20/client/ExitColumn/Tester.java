package it.unipv.ingsw.progettoe20.client.ExitColumn;

import it.unipv.ingsw.progettoe20.client.ExitColumn.Controller.ExitColumnController;
import it.unipv.ingsw.progettoe20.client.ExitColumn.Model.ExitColumn;
import it.unipv.ingsw.progettoe20.client.ExitColumn.View.ExitColumnGUI;

import javax.swing.*;

//Tester per il client ExitColumn

public class Tester {

    public static void main(String[] args) {

        //controllo se viene avviato con parameteo cli come input type

        if (args.length != 0 && args[0].equals("cli")) {

            ExitColumn model = new ExitColumn(args[0]);
        } else {

            ExitColumn model = new ExitColumn("");
            ExitColumnGUI gui = new ExitColumnGUI();
            ExitColumnController controller = new ExitColumnController(gui, model);
            gui.setVisible(true);

        }

    }
}
