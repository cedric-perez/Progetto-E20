package it.unipv.ingsw.progettoe20.client.enterColumn.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import it.unipv.ingsw.progettoe20.client.enterColumn.model.EnterColumn;
import it.unipv.ingsw.progettoe20.client.enterColumn.view.EnterColumnGui;



public class Controller {
private EnterColumnGui g;
private EnterColumn model;
private int reduceAv;
	public Controller( EnterColumnGui g, EnterColumn model) throws IOException {
		this.g=g;
		this.model=model;
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
	            	  
	            Boolean checkIdGeneration = model.genTicket();
	            g.setIdTicket(model.getIdTicket());
	             
	              new Timer().schedule(new TimerTask() {

	            	    @Override
	            	    public void run() { 
	            	    	reduceAv=model.getAvailability()-1;
	            	    	model.setAvailability(reduceAv);
	            	        g.getShowTicketId().setText(String.valueOf(""));
	            	        g.setTransitionObject();
	            	        g.getButton().setEnabled(true);
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
		 g.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                model.closeSocket();
	            }
	        });     
	         
	}
	public void checkConn() throws IOException {
		
		if (!this.model.getIsConn()) {
			this.g.initErrorGui();
			System.exit(0);
		}
	}
}
