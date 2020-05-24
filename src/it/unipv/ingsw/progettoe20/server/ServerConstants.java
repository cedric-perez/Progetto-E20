package it.unipv.ingsw.progettoe20.server;

/**
 * Definisce alcune costanti utilizzate del server.
 */
public class ServerConstants {
    public static final int PORT = 9000;
    public static final String HANDLER_THREAD_NAME = "Handler";

    public static final int TICKET_MAX_EXIT_TIME_DAYS = 0;
    public static final int TICKET_MAX_EXIT_TIME_HOURS = 0;
    public static final int TICKET_MAX_EXIT_TIME_MINUTES = 30;
    public static final int TICKET_MAX_EXIT_TIME_SECONDS = 0;
    public static final long TICKET_MAX_EXIT_TIME_TOTAL_SECONDS = TICKET_MAX_EXIT_TIME_SECONDS +
            (TICKET_MAX_EXIT_TIME_MINUTES * 60) +
            (TICKET_MAX_EXIT_TIME_HOURS * 60 * 60) +
            (TICKET_MAX_EXIT_TIME_DAYS * 24 * 60 * 60);

}
