package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.MessagePanel;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.PayGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * classe che controlla se il pagamento é effettuato
 * in ogni caso si torna a una nuova schermata
 */

public class PaymentListener implements ActionListener {
    private PayGui paygui;
    private String id;
    private OblGui oblgui;
    private ObliterationColumn oc;

    public PaymentListener(PayGui paygui, String id, OblGui oblgui, ObliterationColumn oc) {
        this.paygui = paygui;
        this.id = id;
        this.oblgui = oblgui;
        this.oc = oc;
    }

    /**
     * Metodo per accettare il pagamento
     *
     * @param e pressione del bottone di conferma pagamento
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (oc.getOnlineFlag()) {
            if (oc.Pay(id)) {
                new MessagePanel("Payment Confirmed: Goodbye & drive safely", "Congratulations", new javax.swing.ImageIcon(getClass().getResource("/CarPic.png")));
                paygui.dispose();


                oblgui.setVisible(true);
                oblgui.getIdinput().setText("");
                oblgui.getIdinput().requestFocus();
            } else {
                new MessagePanel("An error occurred: failed payment", "Error", new javax.swing.ImageIcon(getClass().getResource("/ErrorPic.png")));
                paygui.dispose();

                oblgui.setVisible(true);
                oblgui.getIdinput().setText("");
                oblgui.getIdinput().requestFocus();

            }
        } else {
            new MessagePanel("An error occurred: connection to server failed", "Error", new javax.swing.ImageIcon(getClass().getResource("/ErrorPic2.png")));
            System.exit(0);
        }
    }

}
