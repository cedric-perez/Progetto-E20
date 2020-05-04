package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblController;

import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.OblGui;
import it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView.PayGui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe che controlla che l'id del ticket esista
 * e in caso manda alla schermata di pagamento
 */
public class ButtonListener implements ActionListener {
    private OblGui oblgui;

    public ButtonListener(OblGui oblgui){
        this.oblgui = oblgui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String idcheck = oblgui.getIdinput().getText();

        if (idcheck.equals("test")) {
           PayGui payGui = new PayGui();
            payGui.setVisible(true);
            PaymentListener paymentListener = new PaymentListener(payGui, idcheck, oblgui);
            payGui.getPayb().addActionListener(paymentListener);
           oblgui.setVisible(false);
           oblgui.getIdinput().setText("");
        } else {
            JOptionPane.showMessageDialog(null, "An error occurred: id may be wrong or any payment has already been made" , "Error" , 1 , new javax.swing.ImageIcon(getClass().getResource("/ErrorPic.png")));
            oblgui.getIdinput().setText("");
            oblgui.getIdinput().requestFocus();
        }
    }

}