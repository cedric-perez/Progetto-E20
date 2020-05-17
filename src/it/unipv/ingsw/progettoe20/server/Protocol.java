package it.unipv.ingsw.progettoe20.server;

/**
 * Definisce il protocollo a livello applicazione utilizzato. Consiste in una semplice stringa formata da comando:ID.
 * In questa classe sono definiti i comandi utilizzabili e le possibili risposte.
 */
public class Protocol {
    public static final String SEPARATOR = ":";

    // Requests
    public static final String REQUEST_GENERATE_ID = "genid";
    public static final String REQUEST_PAY_AMOUNT = "payamount";
    public static final String REQUEST_DELETE_ID = "delete";
    public static final String REQUEST_END = "end";
    public static final String REQUEST_ID = "id";
    public static final String REQUEST_PAYMENT_CHECK = "paid";
    public static final String REQUEST_PAYMENT_ACCEPTED = "acceptpay";

    // Responses
    public static final String RESPONSE_OK = "done";
    public static final String RESPONSE_ERROR = "error";

    // Ping
    public static final String PING = "ping";
    public static final String PONG = "pong";
}
