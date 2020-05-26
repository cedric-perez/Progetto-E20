package server.FakeClient.tests;

import it.unipv.ingsw.progettoe20.Protocol;
import server.FakeClient.utils.FailedTestException;
import server.FakeClient.utils.TestConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Testa il comando ping.
 */
public class PingTest {
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Costruisce un PingTest.
     *
     * @param in connessione in lettura alla socket.
     * @param out connessione in scrittura alla socket.
     */
    public PingTest(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Effettua il test inviando "ping" alla socket. Si aspetta di ricevere "pong".
     *
     * @throws FailedTestException se il test fallisce.
     */
    public void test() throws FailedTestException {
        String result;

        System.out.println(String.format(TestConstants.TEST_TITLE, Protocol.PING.toUpperCase()));
        out.println(Protocol.PING);

        try {
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (result.equals(Protocol.PONG)) {
            System.out.println(TestConstants.TEST_SUCCESS);
        } else {
            throw new FailedTestException("expected '" + Protocol.PONG + "' got '" + result + "'");
        }
    }
}
