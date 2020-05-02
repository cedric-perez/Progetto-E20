package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.PayGui;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel.ObliterationColumn;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;


import javax.swing.*;
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

    public PaymentListener(PayGui paygui , String id , OblGui oblgui ){
        this.paygui = paygui;
        this.id = id;
        this.oblgui = oblgui;

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (ObliterationColumn.pay(id)) {
            JOptionPane.showMessageDialog(null, "Payment Confirmed: Goodbye & drive safely", "Congratulations", 1, null);
            paygui.dispose();


            oblgui.setVisible(true);
            oblgui.getIdinput().setText("");
            oblgui.getIdinput().requestFocus();
        }
        else {
            JOptionPane.showMessageDialog(null, "An error occurred: failed payment");
            paygui.dispose();

            oblgui.setVisible(true);
            oblgui.getIdinput().setText("");
            oblgui.getIdinput().requestFocus();

        }
    }


}
