package it.unipv.ingsw.progettoe20.server.admin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.ingsw.progettoe20.server.admin.controller.AdministratorController;

/* 
 Prima schermata di interfaccia dell'amministratore.
 Offre la scelta tra gestione delle tariffe e gestione dei parcheggi
*/

public class AdministratorGUI extends JFrame{

	public JLabel title;
	public JPanel panel;
	public JButton btnParkings, btnPrices;
	private AdministratorController administratorController;
	
	
	public AdministratorGUI() {
		
		panel = new JPanel();
		title = new JLabel("ADMINISTRATOR");
		btnParkings = new JButton("Parking Management");
		btnPrices = new JButton("Price Management");
	
		
		//frame settings
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 400);
		setLocation(340, 240);
		setTitle("ADMINISTRATOR GUI");
		
		//panel settings
		panel.setBackground(new Color(30, 30, 30));
		panel.setLayout(null);
		
		//label settings
		title.setFont(new Font(Font.MONOSPACED, 3, 30));	//3 = bold e italic
		title.setForeground(new Color(196, 10, 255));
		
		//btnParkings settings
		btnParkings.setFont(new Font(Font.MONOSPACED, 1, 20));	//1 = bold
		btnParkings.setBackground(new Color(222, 177, 255));
		
		//btnPrices settings
		btnPrices.setFont(new Font(Font.MONOSPACED, 1, 20));
		btnPrices.setBackground(new Color(222, 177, 255));
		
		//locations settings
		btnParkings.setBounds(140, 150, 300, 40);
		btnPrices.setBounds(140, 230, 300, 40);
		title.setBounds(25, 10, 500, 40);
		
		panel.add(title);
		panel.add(btnParkings);
		panel.add(btnPrices);
		
		administratorController = new AdministratorController(this);		
	}


	public JButton getBtnParkings() {
		return btnParkings;
	}


	public JButton getBtnPrices() {
		return btnPrices;
	}

	
 	
}
