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




    /**
     * Metodo che richiede la conferma di corretta obliterazione
     *
     * @param id
     * @return Response Enum con i vari esiti del check
     */
    public ResponseEnum checkObliteration(String id) {

        if (checkId(id)) {
            try {
                out.println("paid:" + id);
                String answer = in.readLine();
                System.out.println(answer);
                if (answer.equals("done")) {
                    deleteTicket(id);       //commentarlo in caso di test per prevenire cancellazione record
                    return ResponseEnum.CONFIRMED_EXIT;
                } else return ResponseEnum.NO_PAID;
            } catch (IOException i) {
                return ResponseEnum.NO_PAID;
            } catch (NullPointerException n) {
                isConnected = false;
                return ResponseEnum.NO_PAID; //TODO CHANGE WITH GENERIC ERROR
            }
        }
        else return ResponseEnum.NO_ID_FOUND;

    }

    /**
     * Metodo che elimina il Ticket
     *
     * @param id
     * @return true se il ticket (a cui è associato l'id) è stato eliminato,false in caso contrario
     */
    public Boolean deleteTicket(String id) {
        try {
            out.println("delete:" + id);
            String answer = in.readLine();
            System.out.println(answer);
            return answer.equals("done");
        } catch (IOException i) {
            return false;
        } catch (NullPointerException n) {
            isConnected = false;
            return false;
        }

    }

    /**
     * metodo che cerca l'id nel database
     * @param id
     * @return true se l'id é presente nel database, false se invece non lo è
     */
    public boolean checkId(String id){
        try {
            out.println("id:"+ id);
            String answer = in.readLine();
            System.out.println(answer);
            return answer.equals("done");
        }
        catch (IOException i){
            return false;
        }
        catch ( NullPointerException n){
            isConnected = false;
            return false;
        }
    }

    //getter per avvisare stato connessione con il Server
    public Boolean getIsConnected() {
        return isConnected;
    }
}
