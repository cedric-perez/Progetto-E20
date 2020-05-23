package it.unipv.ingsw.progettoe20;

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
    public static final String REQUEST_CHECK_ID = "id";
    public static final String REQUEST_PAYMENT_CHECK = "paid"; //restituisce paid:true o paid:false
    public static final String REQUEST_PAYMENT_ACCEPTED = "acceptpay";
    public static final String REQUEST_NEWLEVEL = "newlevel";

    // Responses
    public static final String RESPONSE_OK = "done";
    public static final String RESPONSE_ERROR = "error";
    public static final String RESPONSE_PAID_TRUE = "paid:true";
    public static final String RESPONSE_PAID_FALSE = "paid:false";

    // Ping
    public static final String PING = "ping";
    public static final String PONG = "pong";
}
