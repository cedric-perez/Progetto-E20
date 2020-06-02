package it.unipv.ingsw.progettoe20.server.database;

public class Queries {

// GENERAL DB QUERIES
    /**
     * Mostra una lista delle table presenti.
     */
    static final String LIST_DB_TABLES = "SHOW TABLES;";

    /**
     * Crea un database. Necessita di una stringa consecutiva che specifichi il nome
     * del database.
     */
    static final String CREATE_DB = "CREATE DATABASE "; // with PreparedStatement don't work because of ''

    /**
     * Crea la tabella dei ticket.
     */
    static final String CREATE_TABLE_TICKETS = "CREATE TABLE " + DBConstants.TICKET_TABLE + " ("
            + DBConstants.TICKET_FIRST_COLUMN + " varchar(" + DBConstants.TICKET_ID_LENGTH + ") not NULL, "
            + DBConstants.TICKET_SECOND_COLUMN + " DATETIME, " + DBConstants.TICKET_THIRD_COLUMN + " DATETIME, "
            + DBConstants.TICKET_FOURTH_COLUMN + " BOOLEAN, " + " PRIMARY KEY ( " + DBConstants.TICKET_FIRST_COLUMN
            + " ))";

    /**
     * Crea la tabella dei livelli.
     */
    static final String CREATE_TABLE_LEVELS = "CREATE TABLE " + DBConstants.LEVEL_TABLE + " ("
            + DBConstants.LEVEL_FIRST_COLUMN + " varchar(" + DBConstants.LEVEL_NAME_LENGTH + ") not NULL, "
            + DBConstants.LEVEL_SECOND_COLUMN + " INT not NULL, " + DBConstants.LEVEL_THIRD_COLUMN + " INT not NULL, "
            + " PRIMARY KEY ( " + DBConstants.LEVEL_FIRST_COLUMN + " ))";

    /**
     * Crea la tabella delle tariffe
     */
    static final String CREATE_TABLE_PRICES = "CREATE TABLE " + DBConstants.PRICES_TABLE + " ("
            + DBConstants.PRICES_FIRST_COLUMN + " INT not NULL, " + DBConstants.PRICES_SECOND_COLUMN + " DOUBLE not NULL, "
            + " PRIMARY KEY ( " + DBConstants.PRICES_FIRST_COLUMN + " ))";

// TICKET QUERIES
    /**
     * Crea un nuovo ticket. Necessita dei parametri presenti nella query, nello
     * stesso ordine.
     */
    static final String TICKET_NEW = "INSERT INTO " + DBConstants.TICKET_TABLE + " (" + DBConstants.TICKET_FIRST_COLUMN
            + ", " + DBConstants.TICKET_SECOND_COLUMN + ", " + DBConstants.TICKET_THIRD_COLUMN + ", "
            + DBConstants.TICKET_FOURTH_COLUMN + ") " + "VALUES (?, ?, ?, ?)";

    /**
     * Aggiorna un ticket. Necessita dei parametri presenti nella query, nello
     * stesso ordine.
     */
    static final String TICKET_UPDATE = "UPDATE " + DBConstants.TICKET_TABLE + " SET "
            + DBConstants.TICKET_SECOND_COLUMN + " = ?, " + DBConstants.TICKET_THIRD_COLUMN + " = ?, "
            + DBConstants.TICKET_FOURTH_COLUMN + " = ? " + "WHERE " + DBConstants.TICKET_FIRST_COLUMN + " = ?";

    /**
     * Rimuove un ticket. Necessita dei parametri presenti nella query, nello stesso
     * ordine.
     */
    static final String TICKET_REMOVE = "DELETE FROM " + DBConstants.TICKET_TABLE + " WHERE "
            + DBConstants.TICKET_FIRST_COLUMN + " = ?";

    /**
     * Restituisce il ticket scelto. Necessita dei parametri presenti nella query,
     * nello stesso ordine.
     */
    static final String TICKET_GET = "SELECT * FROM " + DBConstants.TICKET_TABLE + " WHERE "
            + DBConstants.TICKET_FIRST_COLUMN + " = ?";

// LEVEL QUERIES
    /**
     * Crea un nuovo livello. Necessita dei parametri presenti nella query, nello
     * stesso ordine.
     */
    static final String LEVEL_NEW = "INSERT INTO " + DBConstants.LEVEL_TABLE + " (" + DBConstants.LEVEL_FIRST_COLUMN
            + ", " + DBConstants.LEVEL_SECOND_COLUMN + ", " + DBConstants.LEVEL_THIRD_COLUMN + ") "
            + "VALUES (?, ?, ?)";

    /**
     * Aggiorna un livello. Necessita dei parametri presenti nella query, nello
     * stesso ordine.
     */
    static final String LEVEL_UPDATE = "UPDATE " + DBConstants.LEVEL_TABLE + " SET " + DBConstants.LEVEL_SECOND_COLUMN
            + " = ?, " + DBConstants.LEVEL_THIRD_COLUMN + " = ? " + "WHERE " + DBConstants.LEVEL_FIRST_COLUMN + " = ?";

    /**
     * Rimuove un livello. Necessita dei parametri presenti nella query, nello
     * stesso ordine.
     */
    static final String LEVEL_REMOVE = "DELETE FROM " + DBConstants.LEVEL_TABLE + " WHERE "
            + DBConstants.LEVEL_FIRST_COLUMN + " = ?";

    /**
     * Restituisce il livello scelto. Necessita dei parametri presenti nella query,
     * nello stesso ordine.
     */
    static final String LEVEL_GET = "SELECT * FROM " + DBConstants.LEVEL_TABLE + " " + "WHERE "
            + DBConstants.LEVEL_FIRST_COLUMN + " = ?";

    /**
     * Restituisce la lista dei livelli.
     */
    static final String LEVEL_GET_LIST = "SELECT * FROM " + DBConstants.LEVEL_TABLE;

// PRICE QUERIES
    /**
     * Crea una nuova tariffa. Necessita dei parametri presenti nella query, nello
     * stesso ordine.
     */
    static final String PRICES_NEW = "INSERT INTO " + DBConstants.PRICES_TABLE + " (" + DBConstants.PRICES_FIRST_COLUMN
            + ", " + DBConstants.PRICES_SECOND_COLUMN + ") "
            + "VALUES (?, ?)";

    /**
     * Aggiorna una tariffa. Necessita dei parametri presenti nella query, nello
     * stesso ordine.
     */
    static final String PRICES_UPDATE = "UPDATE " + DBConstants.PRICES_TABLE + " SET " + DBConstants.PRICES_SECOND_COLUMN
            + " = ? " + "WHERE " + DBConstants.PRICES_FIRST_COLUMN + " = ?";

    /**
     * Rimuove una tariffa. Necessita dei parametri presenti nella query, nello
     * stesso ordine.
     */
    static final String PRICES_REMOVE = "DELETE FROM " + DBConstants.PRICES_TABLE + " WHERE "
            + DBConstants.PRICES_FIRST_COLUMN + " = ?";

    /**
     * Restituisce la lista delle tariffe.
     */
    static final String PRICES_GET_LIST = "SELECT * FROM " + DBConstants.PRICES_TABLE;
}
