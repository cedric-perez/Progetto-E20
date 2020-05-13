package it.unipv.ingsw.progettoe20.client.enterColumn.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EnterColumn {
	private int availlability=100;
	private BufferedReader in;
	private PrintWriter out;
	private boolean isConnected=false;
	public EnterColumn() {
		checkServerConnection();
	}
	public void checkServerConnection() {
		
		   try {
	            Socket clientSocket = new Socket("localhost", 9000);
	            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            isConnected = true;
	        } catch (IOException i) {
	            isConnected = false;
	        }
	} 
		
	   public Boolean insertTicket(String id) throws IOException {
	        try {
	        	 out.println("id:"+ id);
	             //System.out.println(id);
	             if (id.equals("done")) {
	                 return true;
	             } else return false;
	          
	           
	        } catch (NullPointerException n) {
	            isConnected = false;
	            return false;
	        }
			

	    }
	public boolean getIsConn() {
		
		return this.isConnected;
	}	
		
	
	public String generateTicket() {
		return "ID000000";
		
	}
	public int getAvailability() {
		return this.availlability;
	}
	public void reduceAvailability() {
		this.availlability--;
	}
}
