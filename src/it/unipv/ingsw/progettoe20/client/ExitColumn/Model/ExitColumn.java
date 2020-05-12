package it.unipv.ingsw.progettoe20.client.ExitColumn.Model;
/*
  Questa classe rappresenta la colonna di uscita dal parcheggio, si occupa di controllare
  che il ticket sia valido per l'uscita verficandone l'obliterazione e il tempo intercorso,
  in caso positivo permette l'uscita del veicolo
  in caso negativo richiede di recarsi alla colonnina di obliterazione
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ExitColumn {

    private Socket clientSocket;
    private Boolean isConnected;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Costruttore del client Exit column
     */

    public ExitColumn() {
        try {
            clientSocket = new Socket("localhost", 9000);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            isConnected = true;
        } catch (IOException i) {
            isConnected = false;
        }
    }


    //Metodo che richiede la conferma di obliterazione e ritorna un booleano
    public Boolean checkObliteration(String id) {

        if (id.equals("prova1")) return true; //Ticket corretto per test
        else return false;

    }

    //getter per avvisare stato connessione con il Server
    public Boolean getIsConnected() {
        return isConnected;
    }
}
