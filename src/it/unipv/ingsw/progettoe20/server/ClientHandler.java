package it.unipv.ingsw.progettoe20.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLException;

// TODO: add comments, find better solution for switch statement, write something in error catches

/**
 * Il ClientHandler è l'unità che si occupa della connessione con il client. Ogni handler è associato a un solo client.
 */
public class ClientHandler extends Thread {
    private Socket socket;
    private RequestHandler requestHandler;
    private boolean end = false;

    /**
     * Costruttore del ClientHandler.
     *
     * @param socket    socket utilizzata per la connessione.
     * @param requestHandler reference al RequestHandler associato.
     * @param name      nome del thread.
     */
    public ClientHandler(Socket socket, RequestHandler requestHandler, String name) {
        super(name);
        this.socket = socket;
        this.requestHandler = requestHandler;
    }

    /**
     * Legge il contenuto della socket e verifica la presenza di richieste. Alla richiesta di chiusura termina la connessione.
     */
    public void run() {
        try {
            while (!end) {
                String request = listenSocket(socket.getInputStream());
                 end = requestHandler.handle(request);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Legge il contenuto della socket.
     *
     * @param input il collegamento in lettura alla socket.
     * @return richiesta letta.
     * @throws IOException se ci sono problemi nel collegamento alla socket.
     */
    private String listenSocket(InputStream input) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;

        while (((c = input.read()) >= 0) && (c != 0x0a /* <LF> */)) {
            if (c != 0x0d /* <CR> */) {
                sb.append((char) c);
            } else {
                // Ignore <CR>.
            }
        }
        return sb.toString();
    }

}
