package it.unipv.ingsw.progettoe20.client.ObliterationColumn.oblModel;

import it.unipv.ingsw.progettoe20.client.ClientConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Questa classe Rappresenta la colonna di pagamento del parcheggio
 * Si occupa quindi di confermare che la transazione, identificata dal id del ticket
 * é andata a buon fine
 */
public class ObliterationColumn {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Boolean onlineFlag;
    private double paymentAmount = 0;
    private String inputType;

    /**
     * metodo che inizializza la classe Obliteration column come client
     */
    public ObliterationColumn(String inputType) {
        try {
            socket = new Socket(ClientConstants.HOST, ClientConstants.PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            onlineFlag = true;
            this.inputType = inputType;
            checkInputType();
        } catch (IOException i) {
            System.out.println("rip");
            onlineFlag = false;
        }
    }


    /**
     * metodo che cerca l'id nel database
     *
     * @param id
     * @return true se l'id é presente nel database, false se invece non viene trovato
     */
    public boolean checkId(String id) {
        try {
            out.println("id:" + id);
            String answer = in.readLine();
            System.out.println(answer);
            if (answer.equals("done")) {
                return true;
            } else return false;
        } catch (IOException i) {
            onlineFlag = false;
            return false;
        } catch (NullPointerException n) {
            onlineFlag = false;
            return false;
        }
    }


    /**
     * Metodo che permette il pagamento
     *
     * @param id
     * @return true se il pagamento va a buon fine, altrimenti false
     */
    public boolean Pay(String id) {
        try {
            out.println("acceptpay:" + id);
            String answer = in.readLine();
            System.out.println(answer);
            if (answer.equals("done")) {
                return true;
            } else return false;
        } catch (IOException i) {
            onlineFlag = false;
            return false;
        } catch (NullPointerException n) {
            onlineFlag = false;
            return false;
        }
    }

    /**
     * metodo che permette di visualizzare l'ammontare del pagamento
     *
     * @param id
     * @return paymentAmount(da mostrare nel display del client)
     */
    public String PaymentAmount(String id) {
        try {
            out.println("payamount:" + id);
            String answer = in.readLine();
            System.out.println(answer);
            return answer;
        } catch (IOException i) {
            onlineFlag = false;
            return "errore";
        } catch (NullPointerException n) {
            onlineFlag = false;
            return "errore";
        }
    }


    /**
     * metodo per chiudere il socket
     */
    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException i) {
            System.out.println("Socket Error");
        } catch (NullPointerException n) {
            onlineFlag = false;
        }
    }

    /**
     * Metodo che verifica la metodologia di input (GUI o cli)
     */
    public void checkInputType() {
        if (inputType.equals("cli")) {
            cli();
        } else System.out.println("GUI avviata");

    }

    /**
     * Metodo che rappresenta l'interfaccia testuale
     */
    private void cli() {
        String insertText = "";
        String acceptText = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Hai scelto la modlità command line input, inserisci il TicketID o exit per terminare.");
            insertText = scanner.next();
            if (insertText.equals("exit")) break;
            if (checkId(insertText)) {
                System.out.println("accettare il pagamento di " + "$" + paymentAmount + " (inserisci si per procedere).");
                acceptText = scanner.next();
                if (acceptText.equals("si")) {
                    Pay(insertText);
                    System.out.println("pagamento accettato");
                } else System.out.println("pagamento rifiutato");
            }

        }
        System.out.println("Hai terminato l'esecuzione");
        scanner.close();
        System.exit(0);
    }

    /**
     * metodo per ottenere lo stato della connessione
     *
     * @return true se il client si é connesso al server , false se l'ultima richiesta é fallita
     */
    public Boolean getOnlineFlag() {
        return onlineFlag;
    }
}
