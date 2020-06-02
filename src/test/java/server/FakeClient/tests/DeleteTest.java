package server.FakeClient.tests;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import server.FakeClient.utils.FailedTestException;
import server.FakeClient.utils.TestConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteTest {
    private BufferedReader in;
    private PrintWriter out;
    private DatabaseFacade dbFacade;

    /**
     * Costruisce un DeleteTest.
     *
     * @param in connessione in lettura alla socket.
     * @param out connessione in scrittura alla socket.
     * @param dbFacade reference a un DatabaseFacade.
     */
    public DeleteTest(BufferedReader in, PrintWriter out, DatabaseFacade dbFacade) {
        this.in = in;
        this.out = out;
        this.dbFacade = dbFacade;
    }

    /**
     * Effettua il test richiedendo la cancellazione di un ID. Controlla se l'ID è stato rimosso dal DB.
     *
     * @throws FailedTestException se il test fallisce.
     */
    public void test(String generated) throws FailedTestException {
        String  result;

        System.out.println(String.format(TestConstants.TEST_TITLE, Protocol.REQUEST_DELETE_ID.toUpperCase()));

        // TEST 1: check real ticket
        out.println(Protocol.REQUEST_DELETE_ID + Protocol.SEPARATOR + generated);
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
        // Check DB changes
        if (dbFacade.checkTicketById(generated)) {
            throw new FailedTestException("deleted ID '" + generated + "' found in DB");
        }

        // TEST 2: check ticket not present
        out.println(Protocol.REQUEST_DELETE_ID + Protocol.SEPARATOR + generated);
        try {
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Check correct response format
        if (!result.contains(Protocol.RESPONSE_ERROR)) {
            throw new FailedTestException("expected '" + Protocol.RESPONSE_ERROR + "' got '" + result + "'");
        }


        System.out.println(TestConstants.TEST_SUCCESS);
    }
}
