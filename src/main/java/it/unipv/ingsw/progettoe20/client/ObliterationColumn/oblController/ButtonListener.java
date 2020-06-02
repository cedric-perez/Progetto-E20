package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.MessagePanel;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.PayGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe che controlla che l'id del ticket esista
 * e in caso manda alla schermata di pagamento
 */
public class ButtonListener implements ActionListener {
    private OblGui oblgui;
    private ObliterationColumn oc;

    public ButtonListener(OblGui oblgui, ObliterationColumn oc) {
        this.oblgui = oblgui;
        this.oc = oc;
    }

    /**
     * metodo per la ricerca dell'id
     * il metodo é dotato anche di un check che chiude il client se la connesione si é arrestata
     *
     * @param e pressione del bottone di cerca id
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String idcheck = oblgui.getIdinput().getText();

        if (oc.getOnlineFlag()) {
            if (oc.checkId(idcheck)) {
                PayGui payGui = new PayGui();
                payGui.setVisible(true);
                PaymentListener paymentListener = new PaymentListener(payGui, idcheck, oblgui, oc);
                payGui.getPayb().addActionListener(paymentListener);
                payGui.getTextpay().setText(String.valueOf(oc.PaymentAmount(idcheck)));
                oblgui.setVisible(false);
                oblgui.getIdinput().setText("");
            } else {
                new MessagePanel("An error occurred: id may be wrong", "Error", new javax.swing.ImageIcon(getClass().getResource("/ErrorPic.png")));
                oblgui.getIdinput().setText("");
                oblgui.getIdinput().requestFocus();
            }
        } else {
            new MessagePanel("An error occurred: connection to server failed", "Error", new javax.swing.ImageIcon(getClass().getResource("/ErrorPic2.png")));
            oc.closeSocket();
            System.exit(0);
        }
    }

}