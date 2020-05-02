package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView;

import javax.swing.*;
import java.awt.*;

/**
 *Classe che rappresenta la schermata di accettazione del pagamento
 */

public class PayGui extends JFrame {

    private JButton payb = new JButton("accept payment");
    private JLabel paylabel = new JLabel("Payment amount:");
    private JPanel panel = new JPanel();
    private JTextArea textpay = new JTextArea();

    public PayGui(){
        super("Id confirmed");
        setSize(300,200);
        setLocation(500,280);
        panel.setLayout (null);

        payb.setBounds(70,100,175,20);
        paylabel.setBounds(75,0,150,20);
        textpay.setBounds(70,30,150,20);

        textpay.setEditable(false);
        textpay.setBackground(Color.black);
        textpay.setForeground(Color.cyan);

        // test andra sostituito con i dati contenuti dal database
        textpay.setText("You have to pay: " +"test");

        paylabel.setForeground(Color.white);

        panel.setBackground(Color.DARK_GRAY);;

        panel.add(paylabel);
        panel.add(payb);
        panel.add(textpay);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public JButton getPayb() {
        return payb;
    }
}
