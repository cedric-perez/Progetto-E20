package it.unipv.ingsw.progettoe20.client.ObliterationColumn;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController.OblController;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;


/**
 * tester per obliterazione
 */
public class Tester {
    public static void main(String[] args) {
        //args = new String[1];
        //args[0] = "cli"; per testare velocemente l'interfaccia testuale

        if (args.length != 0 && args[0].equals("cli")) {

            ObliterationColumn model = new ObliterationColumn(args[0]);
        } else {

            ObliterationColumn oc = new ObliterationColumn("");
            OblGui oblgui = new OblGui();
            OblController oblc = new OblController(oblgui, oc);
        }
    }
}
