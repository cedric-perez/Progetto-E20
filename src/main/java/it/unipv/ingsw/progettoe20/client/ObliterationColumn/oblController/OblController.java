package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;

import javax.swing.*;
import java.io.IOException;

/**
 * classe controller della scheramta di obliterazione
 * avvisa anche se il server é offline quando si apre la gui
 */
public class OblController {
    private OblGui oblgui;
    private ObliterationColumn oc;

    public OblController(OblGui oblgui , ObliterationColumn oc){
        this.oblgui = oblgui;
        this.oc = oc;
        initComponents();
    }

    /**
     * inizializza i listeners
     */
    private void initComponents(){
        ButtonListener buttonListener = new ButtonListener(oblgui, oc);
        oblgui.getCheckb().addActionListener(buttonListener);
        /**
         * controlla che il client sia connesso al server
         */
        if(oc.getOnlineFlag()== false){
            JOptionPane.showMessageDialog(null, "An error occurred: connection to server failed", "Error", 1, new javax.swing.ImageIcon(getClass().getResource("/ErrorPic.png")));
        }
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