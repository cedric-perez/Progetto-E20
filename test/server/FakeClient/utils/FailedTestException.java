package server.FakeClient.utils;

/**
 * Eccezione personalizzata per i fallimenti dei test.
 */
public class FailedTestException extends Exception{

    public FailedTestException(String message) {
        super(message);
    }
}
