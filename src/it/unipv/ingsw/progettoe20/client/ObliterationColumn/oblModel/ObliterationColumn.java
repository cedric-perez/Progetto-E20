package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Questa classe Rappresenta la colonna di pagamento del parcheggio
 * Si occupa quindi di confermare che la transazione, identificata dal id del ticket
 * é andata a buon fine
 */
public class ObliterationColumn  {

    private Socket socket;
    private BufferedReader in;
    private  PrintWriter out;
    private Boolean onlineFlag;
    private double paymentAmount = 0;

    /**
     * metodo che inizializza la classe Obliteration column come client
     */
    public ObliterationColumn()  {
        try {
            socket = new Socket("localhost", 9000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            onlineFlag = true;
        } catch(IOException i){
            System.out.println("rip");
            onlineFlag = false;
        }
    }


    /**
     * metodo che cerca l'id nel database
     * @param id
     * @return true se l'id é presente nel database, false se invece non viene trovato
     */
    public boolean checkId(String id){
        try {
            out.println("id:"+ id);
            String answer = in.readLine();
            System.out.println(answer);
            if (answer.equals("done")) {
                return true;
            } else return false;
        }
        catch (IOException i){
            return false;
        }
        catch ( NullPointerException n){
            onlineFlag = false;
            return false;
        }
    }


    /**
     *Metodo che permette il pagamento
     * @param id
     * @return true se il pagamento va a buon fine, altrimenti false
     */
    public boolean Pay(String id){
        try {
            out.println("acceptpay:"+ id);
            String answer = in.readLine();
            System.out.println(answer);
            if (answer.equals("done")) {
                return true;
            } else return false;
        }
        catch (IOException i){
            return false;
        }
        catch ( NullPointerException n){
            onlineFlag = false;
            return false;
        }
    }

    /**
     * metodo che permette di visualizzare l'ammontare del pagamento
     * @param id
     * @return paymentAmount(da mostrare nel display del client)
     */
    public double PaymentAmount(String id){
        return paymentAmount;
    }


    public Boolean getOnlineFlag() {
        return onlineFlag;
    }
}
