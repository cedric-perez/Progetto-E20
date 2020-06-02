package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.MessagePanel;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;

/**
 * classe controller della scheramta di obliterazione
 * avvisa anche se il server é offline quando si apre la gui
 */
public class OblController {
    private OblGui oblgui;
    private ObliterationColumn oc;

    public OblController(OblGui oblgui, ObliterationColumn oc) {
        this.oblgui = oblgui;
        this.oc = oc;
        initComponents();
    }

    /**
     * inizializza i listeners
     */
    private void initComponents() {
        ButtonListener buttonListener = new ButtonListener(oblgui, oc);
        oblgui.getCheckb().addActionListener(buttonListener);
        /**
         * controlla che il client sia connesso al server, altrimenti lo chiude
         */
        if (oc.getOnlineFlag() == false) {
            new MessagePanel("An error occurred: connection to server failed", "Error", new javax.swing.ImageIcon(getClass().getResource("/ErrorPic2.png")));
            oc.closeSocket();
            System.exit(0);
        }

        oblgui.setVisible(true);
        /**
         * chiude il socket all'uscita
         */
        oblgui.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                oc.closeSocket();
            }
        });
    }

}
