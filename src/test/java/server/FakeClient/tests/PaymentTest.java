package server.FakeClient.tests;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import server.FakeClient.utils.FailedTestException;
import server.FakeClient.utils.TestConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentTest {
    private BufferedReader in;
    private PrintWriter out;
    private DatabaseFacade dbFacade;

    /**
     * Costruisce un PaymentChecksTest.
     *
     * @param in       connessione in lettura alla socket.
     * @param out      connessione in scrittura alla socket.
     * @param dbFacade reference a un DatabaseFacade.
     */
    public PaymentTest(BufferedReader in, PrintWriter out, DatabaseFacade dbFacade) {
        this.in = in;
        this.out = out;
        this.dbFacade = dbFacade;
    }

    /**
     * Effettua il test richiedendo la generazione di un ID. Controlla se l'ID generato è presente nel DB.
     *
     * @throws FailedTestException se il test fallisce.
     */
    public void test(String generated) throws FailedTestException {
        String result;

        System.out.println(String.format(TestConstants.TEST_TITLE, Protocol.REQUEST_PAYMENT_CHECK.toUpperCase() + " && " + Protocol.REQUEST_PAYMENT_ACCEPTED.toUpperCase()));

        // TEST 1: unpaid ticket
        out.println(Protocol.REQUEST_PAYMENT_CHECK + Protocol.SEPARATOR + generated);
        try {
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Check correct response format
        if (!result.equals(Protocol.RESPONSE_PAID_FALSE)) {
            throw new FailedTestException("expected '" + Protocol.RESPONSE_PAID_FALSE + "' got '" + result + "'");
        }

        // TEST 2: paid ticket (test both Payment and Check)
        out.println(Protocol.REQUEST_PAYMENT_ACCEPTED + Protocol.SEPARATOR + generated);
        try {
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Check correct response format
        if (!result.equals(Protocol.RESPONSE_OK)) {
            throw new FailedTestException("expected '" + Protocol.RESPONSE_OK + "' got '" + result + "'");
        }

        out.println(Protocol.REQUEST_PAYMENT_CHECK + Protocol.SEPARATOR + generated);
        try {
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Check correct response format
        if (!result.equals(Protocol.RESPONSE_PAID_TRUE)) {
            throw new FailedTestException("expected '" + Protocol.RESPONSE_PAID_TRUE + "' got '" + result + "'");
        }


        System.out.println(TestConstants.TEST_SUCCESS);
    }
}
