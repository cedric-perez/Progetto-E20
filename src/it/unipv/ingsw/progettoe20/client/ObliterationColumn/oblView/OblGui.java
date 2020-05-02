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
    private JLabel idlabel = new JLabel("Insert ticket id");

    public OblGui(){
        super("OBLITERATION COLUMN");
        setSize(300,200);
        setLocation(500,280);
        panel.setLayout (null);


        idinput.setBounds(70,30,150,20);
        checkb.setBounds(70,100,175,20);
        idlabel.setBounds(75,0,150,20);

        idlabel.setForeground(Color.white);
        idinput.setForeground(Color.cyan);
        idinput.setBackground(Color.BLACK);
        panel.setBackground(Color.DARK_GRAY);

        panel.add(checkb);
        panel.add(idinput);
        panel.add(idlabel);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public JTextField getIdinput() {
        return idinput;
    }

    public JButton getCheckb() {
        return checkb;
    }
}
