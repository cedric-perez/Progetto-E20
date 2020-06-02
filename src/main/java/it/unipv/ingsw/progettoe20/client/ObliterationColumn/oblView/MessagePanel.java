package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView;

import javax.swing.*;
import java.awt.*;

/**
 * Pannelo usato per scambiare messaggi con l'utente
 */
public class MessagePanel extends JPanel {

    public MessagePanel(String message, String title, Icon icon) {
        UIManager.put("OptionPane.background", new java.awt.Color(30, 30, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 18));
        UIManager.getLookAndFeelDefaults().put("Panel.background", new java.awt.Color(30, 30, 30));
        JOptionPane.showMessageDialog(null, message, title, 1, icon);

    }
}
