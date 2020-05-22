package server.FakeClient.tests;

import it.unipv.ingsw.progettoe20.Protocol;
import server.FakeClient.utils.FailedTestException;
import server.FakeClient.utils.TestConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PingTest {
    private BufferedReader in;
    private PrintWriter out;

    public PingTest(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

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
