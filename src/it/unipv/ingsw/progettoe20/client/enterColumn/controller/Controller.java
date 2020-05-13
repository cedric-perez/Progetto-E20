package it.unipv.ingsw.progettoe20.client.enterColumn.controller;

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
	            
	              if (g.getAvailability()>0) {
	            	  String idTicket= g.getColumn().generateTicket();
	            	  
	            	  Boolean checkInserimento = g.getColumn().insertTicket(idTicket);
	            	  
	            	 // if(checkInserimento==true) {
	            	  g.setIdTicket(idTicket);
	             
	              new Timer().schedule(new TimerTask() {

	            	    @Override
	            	    public void run() {  
	            	    	g.updateAvailability();
	            	        g.getShowTicketId().setText(String.valueOf(""));
	            	        g.setTransitionObject();
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
