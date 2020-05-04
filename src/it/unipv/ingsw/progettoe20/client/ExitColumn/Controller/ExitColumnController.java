package it.unipv.ingsw.progettoe20.client.ExitColumn.Controller;

import it.unipv.ingsw.progettoe20.client.ExitColumn.Model.ExitColumn;
import it.unipv.ingsw.progettoe20.client.ExitColumn.View.ExitColumnGUI;
import it.unipv.ingsw.progettoe20.client.ExitColumn.View.PannelCheckFalse;
import it.unipv.ingsw.progettoe20.client.ExitColumn.View.PannelCheckTrue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/*
  Controller del client ExitColumn
  contiene il Listener associato al bottone Conferma
  il quale avvia il metodo del model per verificare il pagamento
  in base all'esito verrà modificato il panel
*/

public class ExitColumnController {
    private ExitColumnGUI gui;
    private ExitColumn model;


    public ExitColumnController(ExitColumnGUI gui, ExitColumn model) {
        this.gui = gui;
        this.model = model;
        initListener();
    }

    public void initListener() {

        gui.getButtonTicket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //booleano risultato dal metodo
                Boolean check = model.checkObliteration(gui.getjTextField1().getText());
                if (check == true) {
                    //System.out.println("corretto"); stampa di test

                    //cambio di pannello
                    gui.setContentPane(new PannelCheckTrue());
                    gui.invalidate();
                    gui.validate();
                    //timer fine messaggio inizio nuova schermata per successivo ticket
                    new Timer().schedule(new TimerTask() {

                        @Override
                        public void run() {
                            gui.setContentPane(gui.getPannello());
                            gui.invalidate();
                            gui.validate();
                            gui.getjTextField1().setText("Ticket ID"); //per non far vedere il precedente ID immesso
                        }
                    }, 3000); //tempo dello splash-panel


                } else {
                    //System.out.println("non corretto"); stampa di test
                    gui.setContentPane(new PannelCheckFalse());
                    gui.invalidate();
                    gui.validate();
                    new Timer().schedule(new TimerTask() {

                        @Override
                        public void run() {
                            gui.setContentPane(gui.getPannello());
                            gui.invalidate();
                            gui.validate();
                            gui.getjTextField1().setText("Ticket ID"); //per non far vedere il precedente ID immesso
                        }
                    }, 3000);

                }
            }
        });


    }

}
