package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblView;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che rappresenta la schermata di inserimento id
 */

public class OblGui  extends JFrame {

    private JButton checkb = new JButton("check payments");
    private JPanel panel = new JPanel();
    private JTextField idinput = new JTextField(15);
    private JLabel idlabel = new JLabel("Insert ticket id:");
    private JLabel titlelabel = new JLabel("PAYMENT STATION");

    public OblGui(){
        super("OBLITERATION COLUMN");
        setSize(600,400);
        //setLocation(340,240); test location
        setLocationRelativeTo(null);
        setResizable(false);

        //panel settingsgit
        panel.setLayout (null);
        panel.setBackground(new java.awt.Color(30,30 , 30));

        //input settings
        idinput.setFont(new java.awt.Font("Tahoma", 0, 18));
        idinput.setSize(300 , 30);
        idinput.setForeground(Color.cyan);
        idinput.setBackground(Color.BLACK);

        //button settings
        checkb.setFont(new java.awt.Font("Courier", 0, 18));

        //idlabel settings
        idlabel.setFont(new java.awt.Font("Tahoma", 0, 24));
        idlabel.setForeground(Color.white);

        //titlelabel settings
        titlelabel.setFont(new java.awt.Font("Algerian", 0, 28));
        titlelabel.setForeground(new java.awt.Color(196,10 , 255));
        titlelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ParkingPic.png")));

        //locations settings
        idinput.setBounds(140,180,300,30);
        checkb.setBounds(190,230,200,40);
        idlabel.setBounds(205,130,200,40);
        titlelabel.setBounds(25,10,500,40);


        panel.add(checkb);
        panel.add(idinput);
        panel.add(idlabel);
        panel.add(titlelabel);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);



    }

    /**
     * metodo per controllare l'id inserito dal cliente
     * @return testo che contiene l'id inserito
     */
    public JTextField getIdinput() {
        return idinput;
    }

    /**
     * metodo che restituisce il bottone di "check payments" per gestirne gli eventi
     * @return bottone "check payments"
     */
    public JButton getCheckb() {
        return checkb;
    }
}
