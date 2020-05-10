package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;



public class OblController {
    private OblGui oblgui;
    private ObliterationColumn oc;

    public OblController(OblGui oblgui , ObliterationColumn oc){
        this.oblgui = oblgui;
        this.oc = oc;
        initComponents();
    }

    private void initComponents(){
        ButtonListener buttonListener = new ButtonListener(oblgui, oc);
        oblgui.getCheckb().addActionListener(buttonListener);
    }

}
