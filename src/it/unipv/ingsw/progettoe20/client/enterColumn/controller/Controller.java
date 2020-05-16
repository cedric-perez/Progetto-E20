package it.unipv.ingsw.progettoe20.client.enterColumn.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import it.unipv.ingsw.progettoe20.client.enterColumn.view.EnterColumnGui;



public class Controller {
private EnterColumnGui g;
	
	public Controller( EnterColumnGui g) throws IOException {
		this.g=g;
		checkConn();
		initListener();
	}
	public void initListener() {
		
		g.getButton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
	            try {
	              g.getButton().setEnabled(false);
	              g.getButton().setBackground(new Color(143,0,255));
	              if (g.getAvailability()>0) {
	            	  String ticket=g.getColumn().generateTicket();
	            	  Boolean checkInserimento = g.getColumn().insertTicket();
	            	  
	            	 // if(checkInserimento==true) {
	            	  g.setIdTicket(ticket);
	             
	              new Timer().schedule(new TimerTask() {

	            	    @Override
	            	    public void run() {  
	            	    	g.updateAvailability();
	            	        g.getShowTicketId().setText(String.valueOf(""));
	            	        g.setTransitionObject();
	            	        g.getButton().setEnabled(true);
	            	        }
	            	}, 10000 );
	              
	            	  }
	             // }
	              else {
	              g.setIdTicket("Attendi, nessuna disponibilità");
	              }
	            } catch (Exception ex) {
	              g.setIdTicket("Errore");
	            }
	         }
		});
	         
	         
	}
	public void checkConn() throws IOException {
		
		if (!this.g.getColumn().getIsConn()) {
			this.g.initErrorGui();
			System.exit(0);
		}
	}
}
