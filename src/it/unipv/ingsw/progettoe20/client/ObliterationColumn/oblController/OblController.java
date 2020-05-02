package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;



public class OblController {
    private OblGui oblgui;

    public OblController(OblGui oblgui ){
        this.oblgui = oblgui;
        initComponents();
    }

    private void initComponents(){
        ButtonListener buttonListener = new ButtonListener(oblgui);
        oblgui.getCheckb().addActionListener(buttonListener);
    }

}
