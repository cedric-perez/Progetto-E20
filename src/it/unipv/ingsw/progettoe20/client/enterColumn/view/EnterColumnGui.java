package it.unipv.ingsw.progettoe20.client.enterColumn.view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import it.unipv.ingsw.progettoe20.client.enterColumn.model.EnterColumn;



public class EnterColumnGui extends JFrame{

	private EnterColumn instance;
	private JButton getTicket;
	private JLabel LotAvailability;
	private JLabel ShowTicketId;
	private JLabel wIcon1;
	private JLabel wIcon2;
	private Label message;
	private BufferedImage wPic1;
	private BufferedImage wPic2;
	private BufferedImage wPic3;
	private JPanel panel;

	public EnterColumnGui(EnterColumn instance) throws IOException {
		this.instance=instance;
		initComponents();
	}
	public void initComponents() throws IOException{	
		
		  //setTitleGui
		  setTitle("Enter Column Gui");
		  
		  //components declaration
		  
	      LotAvailability = new JLabel();
	      ShowTicketId = new JLabel();
	      wIcon1 = new JLabel();
	      wIcon2 = new JLabel();
	      panel = new JPanel();
	      message= new Label("", Label.RIGHT);
	      
		  //label setting
	      
	      setAvailability();
	      LotAvailability.setHorizontalAlignment(SwingConstants.CENTER); 
	      
	      //icon setting
		  wPic1=  ImageIO.read(this.getClass().getResource("/ParkingPic.png"));
	      wPic2 = ImageIO.read(this.getClass().getResource("/TicketPic.png"));
	      wPic3 = ImageIO.read(this.getClass().getResource("/CarPic.png"));
	      wIcon1.setHorizontalAlignment(SwingConstants.RIGHT);
	      wIcon2.setHorizontalAlignment(SwingConstants.RIGHT);
	      wIcon1.setIcon(new ImageIcon(wPic1));
	      //setting Button
	    
	      getTicket = new JButton("Dispense your Ticket");
	      
	      //components setting
	      Font f = new Font("TimesRoman", Font.BOLD+Font.ITALIC, 20);
	      Border bWhiteLine = BorderFactory.createLineBorder(Color.white, 4, true); 
	      LotAvailability.setBorder(bWhiteLine);
	      LotAvailability.setForeground(new java.awt.Color(000,064,128));
	      LotAvailability.setFont(new Font("TimesRoman", Font.BOLD, 25));
	      
	      getTicket.setFont(new Font("TimesRoman", Font.BOLD, 18));
	      setMinimumSize(new java.awt.Dimension(200, 300));
	      setResizable(false); 
	      //panel setting    
	      panel.setFont(f);
	      panel.setBackground(new Color(225,225,225));      
	      panel.setLocation(100, 100);
	      panel.setLayout(new GridLayout(4, 2, 10, 10));
	      panel.add(wIcon1);
	      panel.add(new Label("Get your parking Ticket here !", Label.CENTER));
	      panel.add(new Label(" Lot available: ", Label.RIGHT));
	      panel.add(LotAvailability);	// indicazione posti disponibili
	      panel.add(new Label("", Label.RIGHT));
	      panel.add(getTicket);
	     
	      panel.add(wIcon2);
	      panel.add(ShowTicketId);// indicazione del codice cliente generato 
	      
	      
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
		this.wIcon2.setIcon( new ImageIcon(wPic2));
		this.ShowTicketId.setText(" Ticket ID is:"+ code);
	}
	public void setTransitionObject() {
		ImageIcon img= new ImageIcon(wPic3);
		img.getImage().flush();
		wIcon2.setIcon(img);
		
		
	}
	public void updateAvailability() {
		this.instance.reduceAvailability();
		
		setAvailability();
	}
	public JLabel getShowTicketId() {
		return this.ShowTicketId;
	}
}
