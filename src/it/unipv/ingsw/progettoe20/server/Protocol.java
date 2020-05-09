package it.unipv.ingsw.progettoe20.server;

/**
 * Definisce il protocollo a livello applicazione utilizzato. Consiste in una semplice stringa formata da comando:ID.
 * In questa classe sono definiti i comandi utilizzabili.
 */
public class Protocol {
    public static final String REQUEST_GENERATE_ID = "genid";
    public static final String REQUEST_PAY_AMOUNT = "payamount";
    public static final String REQUEST_DELETE_ID = "delete";
    public static final String REQUEST_END = "end";
}
