package it.unipv.ingsw.progettoe20.client.enterColumn.model;

import java.io.BufferedReader;
import java.util.Observable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class EnterColumn extends Observable{
	private int totalLot;
	private BufferedReader in;
	private PrintWriter out;
	private boolean isConnected=false;
	private String answer;
	private Socket clientSocket;
	 /**
     * costruttore  
     */
	public EnterColumn()  {
		checkServerConnection();
		setAvailability();
	}
	
	 /**
     * metodo che imposta la connessione al database
     *
     * @return true se il database � connesso
     */
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
	
	 /**
     * metodo che chiude la socket
     *
     */
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
	 
	 /**
	     * metodo che manda la richiesta per la generazione del Ticket
	     *
	     * @param
	     * @return true se l'id è stato generato correttamente, false se invece non lo è
	     */
	 
	 public Boolean genTicket() throws IOException {
		   try {
	            out.println("genid");
	            answer = in.readLine();
	            System.out.println(answer);
	            String id=answer.substring(5,answer.length());
	            if(answer.equals("done:"+id)) 
	            {return true;}
	            else {return false;}
	            
	        } catch (IOException i) {
	            return false;
	        } catch (NullPointerException n) {
	            isConnected = false;
	            return false;
	        }
			

	 }
	 
	 /**
	     * metodo che controlla la connessione
	     *
	     */
	 
	public boolean getIsConn() {
		
		return this.isConnected;
	}	
		
	
	 /**
     * metodo che manda la richiesta di aggiornamento dei posti disponibili del parcheggio
     * @return true se la richiesta � stata portata a termine correttamente, false se invece non lo è
     */
	
	public Boolean setAvailability() {
		 try {
	            out.println("parkinglot");
	            answer = in.readLine();
	            
	            String stringLot=answer.substring(5,answer.length());
	            totalLot=Integer.parseInt(stringLot);
	            if(answer.equals("done:"+ totalLot)) 
	            {return true;}
	            else {return false;}
	            
	        } catch (IOException i) {
	            return false;
	        } catch (NullPointerException n) {
	            isConnected = false;
	            return false;
	        }
	    }
		
	
	public String getIdTicket() {
		int countString= answer.length();
		String id= answer.substring(5, countString);
		return id;
		
	}
	public int getAvailability() {
		return this.totalLot;
	}

	public void setAvailability(int availability) {
	    this.totalLot=availability;
	    this.setChanged();
	    this.notifyObservers();
		
	}
	
}
