package server.FakeClient;

import it.unipv.ingsw.progettoe20.server.Logger;
import it.unipv.ingsw.progettoe20.server.ServerFacade;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;
import server.FakeClient.tests.DeleteTest;
import server.FakeClient.tests.GenidTest;
import server.FakeClient.tests.PingTest;
import server.FakeClient.tests.ConnectionTest;
import server.FakeClient.utils.FailedTestException;
import server.FakeClient.utils.TestConstants;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Main {
    //TODO: [IMPORTANT] testing on production db is a big nono, fix before exam

    /**
     * Lancia tutti i test.
     * @param args
     */
    public static void main(String[] args) {
        ServerFacade serverFacade = new ServerFacade();
        serverFacade.init();
        DatabaseFacade dbFacade = serverFacade.getDbManager();
        new Thread(serverFacade::start).start();

        String generatedTicket;

        try {
            // Testing connection
            ConnectionTest connectionTest = new ConnectionTest();
            connectionTest.connect();
            BufferedReader in = connectionTest.getIncomingConnection();
            PrintWriter out = connectionTest.getOutgoingConnection();

            // Testing ping
            PingTest pingTest = new PingTest(in, out);
            pingTest.test();

            // Testing genid
            GenidTest genidTest = new GenidTest(in, out, dbFacade);
            generatedTicket = genidTest.test();

            // Testing delete
            DeleteTest deleteTest = new DeleteTest(in, out, dbFacade);
            deleteTest.test(generatedTicket);

            // End
            Logger.close();
            System.exit(0);
        } catch (FailedTestException e) {
            System.out.println(String.format(TestConstants.TEST_FAIL, e.getMessage()));
        }
    }
}
