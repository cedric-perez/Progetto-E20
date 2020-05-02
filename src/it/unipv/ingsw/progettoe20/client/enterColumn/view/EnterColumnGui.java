package it.unipv.ingsw.progettoe20.client.enterColumn.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

import it.unipv.ingsw.progettoe20.client.enterColumn.model.EnterColumn;



public class EnterColumnGui extends JFrame{

	private EnterColumn instance;
	private JButton getTicket;
	private JLabel LotAvailability;
	private JLabel ShowTicketId;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JPanel panel;
	public EnterColumnGui(EnterColumn instance) {
		this.instance=instance;
		initComponents();
	}
	public void initComponents() {	     
		  setTitle("Enter Column Gui");
		  label1 = new JLabel();
		  label2 = new JLabel();
		  label3 = new JLabel();
	      LotAvailability = new JLabel();
	      CompoundBorder composto = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder());
	      LotAvailability.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), composto));
	      ShowTicketId = new JLabel();
	      
	      getTicket = new JButton("Dispense your Ticket");
	     
	      label1.setText("Posti disponibili");
	     
	      label3.setText("Il tuo codice biglietto è:");
	      setAvailability();
	      LotAvailability.setHorizontalAlignment(SwingConstants.CENTER);     
	      
	      panel = new JPanel();
	      panel.setSize(400	, 150);
	      panel.setLocation(100, 100);
	      panel.setLayout(new GridLayout(3, 2, 10, 10));
	      
	      panel.add(new Label("Posti disponibili", Label.CENTER));
	      
	      panel.add(LotAvailability);	// il cliente consulta questo dato per capire se l'accesso è consentito
	      
	      panel.add(new Label("", Label.RIGHT));
	      
	      panel.add(getTicket);
	      
	      panel.add(new Label("Il codice del biglietto è", Label.CENTER));
	      
	      panel.add(ShowTicketId);	      // il codice bigletto viene mostrato al momento dell'erogazione del Ticket 
	      
	      getContentPane().add(panel);
	       

	}
	
	public JButton getButton() {
		return getTicket;
	}
	public EnterColumn getColumn() {
		return instance;
	}
	public void setAvailability() {
		this.LotAvailability.setText(String.valueOf(instance.getAvailability()).toString());
		
	}
	public int getAvailability() {
		return Integer.parseInt(LotAvailability.getText());
	}
	
	public void setIdTicket(String code) {
		this.ShowTicketId.setText(code);
	}
	public void updateAvailability() {
		this.instance.reduceAvailability();
		setAvailability();
	}
	public JLabel getShowTicketId() {
		return this.ShowTicketId;
	}
}
