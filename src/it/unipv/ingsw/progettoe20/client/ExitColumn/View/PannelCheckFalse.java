package it.unipv.ingsw.progettoe20.client.ExitColumn.View;

import javax.swing.*;

/*
  Splash-panel in caso di ticket non idoneo
*/

public class PannelCheckFalse extends JPanel {

    public PannelCheckFalse() {
        initComponents();
    }


    private void initComponents() {

        PannelloErrore = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        PannelloErrore.setBackground(new java.awt.Color(20, 10, 51));
        PannelloErrore.setMaximumSize(new java.awt.Dimension(1800, 1000));
        PannelloErrore.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ParkingPic.png")));
        jLabel3.setText("EXIT COLUMN");
        PannelloErrore.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ErrorPic.png")));
        jLabel4.setText("Errore: codice errato o non obliterato");
        PannelloErrore.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

        //impostazioni di layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(PannelloErrore, javax.swing.GroupLayout.PREFERRED_SIZE, 1800, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(PannelloErrore, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }


    // Dichiarazione varibili
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel PannelloErrore;


    //Getter componenti per listener

    public JPanel getPannelloErrore() {
        return PannelloErrore;
    }
}


