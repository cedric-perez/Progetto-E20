package it.unipv.ingsw.progettoe20.client.enterColumn.model;

import java.io.BufferedReader;
import java.util.Observable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class EnterColumn extends Observable{
	private int availlability=100;
	private BufferedReader in;
	private PrintWriter out;
	private boolean isConnected=false;
	private String answer;
	private Socket clientSocket;
	public EnterColumn()  {
		checkServerConnection();
	}
	public void checkServerConnection() {
		
		   try {
	            clientSocket = new Socket("localhost", 9000);
	            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            out = new PrintWriter(clientSocket.getOutputStream(), true);
	            isConnected = true;
	        } catch (IOException i) {
	            isConnected = false;
	        }
	} 
	 public void closeSocket() {
	        try {
	            clientSocket.close();
	        }
	        catch (IOException i ){
	            System.out.println("Socket Error");
	        }
	        catch ( NullPointerException n) {
	            isConnected = false;
	        }
	    }	
	   public Boolean genTicket() throws IOException {
		   try {
	            out.println("genid");
	            answer = in.readLine();
	            System.out.println(answer);
	            
	            return answer.equals("done");
	            
	        } catch (IOException i) {
	            return false;
	        } catch (NullPointerException n) {
	            isConnected = false;
	            return false;
	        }
			

	    }
	public boolean getIsConn() {
		
		return this.isConnected;
	}	
		
	
	public String getIdTicket() {
		int countString= answer.length();
		String id= answer.substring(5, countString);
		return id;
		
	}
	public int getAvailability() {
		return this.availlability;
	}

	public void setAvailability(int availability) {
	    this.availlability=availability;
	    this.setChanged();
	    this.notifyObservers();
		
	}
	
}
