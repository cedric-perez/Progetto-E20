package it.unipv.ingsw.progettoe20.client.enterColumn.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.border.Border;

import it.unipv.ingsw.progettoe20.client.enterColumn.model.EnterColumn;



public class EnterColumnGui extends JFrame implements Observer{

	private EnterColumn column;
	private JButton getTicket;
	private JLabel LotAvailability;
	private JLabel ShowTicketId;
	private JLabel wIcon1;
	private JLabel wIcon2;
	private JLabel lotLabel;
	private JLabel levelLabel;
	private BufferedImage wPic1;
	private BufferedImage wPic2;
	private BufferedImage wPic3;
	private BufferedImage wPic4;
	private JPanel panelTop;
	private JPanel panelSud;

	public EnterColumnGui(EnterColumn column) throws IOException {
		this.column=column;
		
		initComponents();
		setAvailability();
	}
	public void initComponents() throws IOException {
		
		  //setTitleGui
		  setTitle("Enter Column Gui");
		  
		  //components declaration
		  
		  levelLabel=new JLabel();
		  lotLabel=new JLabel();
	      LotAvailability = new JLabel();
	      ShowTicketId = new JLabel();
	      wIcon1 = new JLabel();
	      wIcon2 = new JLabel();
	      panelTop = new JPanel();
	      panelSud =new JPanel();
	      panelTop = new JPanel();
	      
		  //label setting
	      
	      LotAvailability.setHorizontalAlignment(SwingConstants.CENTER); 
	      levelLabel.setHorizontalAlignment(SwingConstants.CENTER); 
	      ShowTicketId.setHorizontalAlignment(SwingConstants.CENTER); 
	      //setting Button
	      getTicket = new JButton("Dispense your Ticket");
	      
	      //icon setting
		  wPic1=  ImageIO.read(this.getClass().getResource("/ParkingPic.png"));
	      wPic2 = ImageIO.read(this.getClass().getResource("/TicketPic.png"));
	      wPic3 = ImageIO.read(this.getClass().getResource("/CarPic.png"));	     
	      
	      //Components Alignment
	      wIcon1.setHorizontalAlignment(SwingConstants.CENTER);
	      wIcon2.setHorizontalAlignment(SwingConstants.RIGHT);
	      wIcon1.setIcon(new ImageIcon(wPic1));    
	      
	      //Background/Foreground Settings
	      LotAvailability.setForeground(new java.awt.Color(143,0,255));
	      levelLabel.setForeground(Color.WHITE);
	      ShowTicketId.setForeground(Color.WHITE);
	      getTicket.setBackground(new Color(143,0,255));
	      panelSud.setBackground(new Color(92,92,92));  
	      panelTop.setBackground(new Color(92,92,92)); 
	      //Font Settings
	      Font f = new Font("TimesRoman", Font.BOLD+Font.ITALIC, 20);  
	      LotAvailability.setFont(new Font("TimesRoman", Font.BOLD, 25));
	      getTicket.setFont(new Font("TimesRoman", Font.BOLD, 18)); 
	      ShowTicketId.setFont(new Font("TimesRoman", Font.BOLD,16));
	      levelLabel.setFont(new Font("TimesRoman", Font.BOLD,16));
	      panelSud.setFont(new Font("TimesRoman", Font.BOLD, 20));
	      panelTop.setFont(new Font("TimesRoman", Font.BOLD, 20));
	      //Border Settings
	      Border bWhiteLine = BorderFactory.createLineBorder(Color.white, 4, true); 
	      LotAvailability.setBorder(bWhiteLine);
	     
	      //panel setting    
	      setMinimumSize(new java.awt.Dimension(400, 500));
	      setResizable(false); 
	      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		  setLocation((int)(dim.getWidth()-this.getWidth())/2, (int) (dim.getHeight()-this.getHeight())/2);
		      
	          
		  //Layout Settings
    
	      panelTop.setLayout(new BorderLayout());  
	      panelTop.add(wIcon1,BorderLayout.NORTH);
	      panelTop.add((new Label("Lot available:  ", Label.RIGHT)),BorderLayout.WEST);
	      panelTop.add(LotAvailability,BorderLayout.CENTER);
	      panelTop.add(getTicket,BorderLayout.SOUTH);  
	      panelSud.setLayout(new BorderLayout());
	      panelSud.add(levelLabel,BorderLayout.NORTH);
	      panelSud.add(wIcon2, BorderLayout.WEST);
	      panelSud.add(ShowTicketId, BorderLayout.CENTER); 
	      getContentPane().add(panelTop,BorderLayout.NORTH);
	      getContentPane().add(panelSud,BorderLayout.CENTER);
	      
	}
	public void initErrorGui() throws IOException {
		getTicket = new JButton("");
		getTicket.setVisible(false);
		wPic4 = ImageIO.read(this.getClass().getResource("/ErrorPic.png"));
	    ImageIcon icon=new ImageIcon(wPic4);		
    UIManager.put("OptionPane.background", Color.black);
    UIManager.put("OptionPane.messageForeground", Color.WHITE);
    UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 18));
    UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 25));
    UIManager.getLookAndFeelDefaults().put("Panel.background", Color.black);
    JOptionPane.showMessageDialog(null, "An error occurred: connection to server failed", "Error", 1, icon);

}

	

	public JButton getButton() {
		return getTicket;
	}
	
	public void setAvailability() {
		this.LotAvailability.setText(String.valueOf(this.column.getAvailability()).toString());
	}
	
	public void setLevelTicket(String text) {
		this.levelLabel.setText("Please, Go to "+ text +" level!");
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
	
	public JLabel getShowTicketId() {
		return this.ShowTicketId;
	}
	
	

	@Override
	public void update(Observable o, Object arg) {
	
	    LotAvailability.setText(String.format("%d", ((EnterColumn) o).getAvailability()));
	    repaint();
		
	}
}
