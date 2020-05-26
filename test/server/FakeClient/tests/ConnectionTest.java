package server.FakeClient.tests;

import it.unipv.ingsw.progettoe20.server.ServerConstants;
import server.FakeClient.tests.PingTest;
import server.FakeClient.utils.FailedTestException;
import server.FakeClient.utils.TestConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Testa la connessione al server.
 */
public class ConnectionTest {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Stabilisce la connessione con il server.
     *
     * @throws FailedTestException
     */
    public void connect() throws FailedTestException {
        System.out.println(String.format(TestConstants.TEST_TITLE, "CONNECTION"));
        try {
            clientSocket = new Socket("localhost", ServerConstants.PORT);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println(TestConstants.TEST_SUCCESS);
        } catch (IOException e) {
            throw new FailedTestException("can't connect to server");
        }
    }

    public BufferedReader getIncomingConnection() {
        return in;
    }

    public PrintWriter getOutgoingConnection() {
        return out;
    }
}
