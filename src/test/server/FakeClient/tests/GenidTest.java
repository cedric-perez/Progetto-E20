package test.server.FakeClient.tests;

import it.unipv.ingsw.progettoe20.Protocol;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import test.server.FakeClient.utils.FailedTestException;
import test.server.FakeClient.utils.TestConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Testa la generazione dell'ID.
 */
public class GenidTest {
    private BufferedReader in;
    private PrintWriter out;
    private DatabaseFacade dbFacade;

    /**
     * Costruisce un GeindTest.
     *
     * @param in connessione in lettura alla socket.
     * @param out connessione in scrittura alla socket.
     * @param dbFacade reference a un DatabaseFacade
     */
    public GenidTest(BufferedReader in, PrintWriter out, DatabaseFacade dbFacade) {
        this.in = in;
        this.out = out;
        this.dbFacade = dbFacade;
    }

    /**
     * Effettua il test richiedendo la generazione di un ID. Controlla se l'ID generato è presente nel DB.
     *
     * @throws FailedTestException se il test fallisce.
     */
    public String test() throws FailedTestException {
        String  result;

        System.out.println(String.format(TestConstants.TEST_TITLE, Protocol.REQUEST_GENERATE_ID.toUpperCase()));
        out.println(Protocol.REQUEST_GENERATE_ID);

        try {
            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Check correct response format
        String[] parts = result.split(Protocol.SEPARATOR);
        if (parts.length != 2) {
            throw new FailedTestException("expected '" + Protocol.RESPONSE_OK + ":ID' got '" + result + "'");
        }

        // Check DB changes
        if (!dbFacade.checkTicketById(parts[1])) {
            throw new FailedTestException("generated ID '" + parts[1] + "' not found in DB");
        } else {
            System.out.println(TestConstants.TEST_SUCCESS);
        }
        return result;
    }
}
