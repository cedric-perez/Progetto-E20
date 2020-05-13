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
    private JTextField textpay = new JTextField();
    private JLabel titlelabel = new JLabel("PAYMENT STATION");

    public PayGui(){
        super("Id confirmed");
        setSize(600,400);
        setLocationRelativeTo(null);
        setResizable(false);

        //panel settings
        panel.setLayout (null);
        panel.setBackground(new java.awt.Color(30,30 , 30));

        //location settings
        textpay.setBounds(140,180,300,30);
        payb.setBounds(190,230,200,40);
        paylabel.setBounds(195,130,200,40);
        titlelabel.setBounds(25,10,500,40);

        //textpay settings


        textpay.setFont(new java.awt.Font("Tahoma", 0, 18));
        textpay.setSize(300 , 30);;
        textpay.setEditable(false);
        textpay.setBackground(Color.black);
        textpay.setForeground(Color.cyan);
            // test andra sostituito con i dati contenuti dal database
        textpay.setText("You have to pay: " +"test");

        //button settings
        payb.setFont(new java.awt.Font("Courier", 0, 18));

        //paylabel settings
        paylabel.setFont(new java.awt.Font("Tahoma", 0, 24));
        paylabel.setForeground(Color.white);

        //titlelabel settings
        titlelabel.setFont(new java.awt.Font("Algerian", 0, 28));
        titlelabel.setForeground(new java.awt.Color(196,10 , 255));
        titlelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ParkingPic.png")));

        panel.add(paylabel);
        panel.add(payb);
        panel.add(textpay);
        panel.add(titlelabel);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public JButton getPayb() {
        return payb;
    }
}
