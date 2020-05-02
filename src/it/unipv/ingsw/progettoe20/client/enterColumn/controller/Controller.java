package it.unipv.ingsw.progettoe20.client.enterColumn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import it.unipv.ingsw.progettoe20.client.enterColumn.view.EnterColumnGui;



public class Controller {
private EnterColumnGui g;
	
	public Controller( EnterColumnGui g) {
		this.g=g;
		initListener();
	}
	public void initListener() {
		
		g.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            try {
	            
	              if (g.getAvailability()>0) {
	              g.setIdTicket(g.getColumn().generateTicket());
	              new Timer().schedule(new TimerTask() {

	            	    @Override
	            	    public void run() {  
	            	    	g.updateAvailability();
	            	        g.getShowTicketId().setText(String.valueOf(""));
	            	        }
	            	}, 10000 );
	              
	              }
	              else {
	              g.setIdTicket("Attendi, nessuna disponibilità");
	              }
	            } catch (Exception ex) {
	              g.setIdTicket("Errore");
	            }
	         }
		});
	         
	         
	}
}
