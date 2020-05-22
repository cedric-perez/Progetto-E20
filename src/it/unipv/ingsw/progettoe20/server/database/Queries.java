package it.unipv.ingsw.progettoe20.server.database;

public class Queries {
    //TODO: fix javadoc comments
    /**
     * General database queries
     */
    static final String LIST_DB = "SHOW DATABASES;";
    static final String CREATE_DB = "CREATE DATABASE "; // with PreparedStatement don't work because of ''
    static final String USE_DB = "USE "; // same

    static final String CREATE_TICKETS_TABLE = "CREATE TABLE " + DBConstants.TICKET_TABLE + " ("
            + DBConstants.TICKET_FIRST_COLUMN + " varchar(" + DBConstants.TICKET_ID_LENGTH + ") not NULL, "
            + DBConstants.TICKET_SECOND_COLUMN + " DATETIME, " + DBConstants.TICKET_THIRD_COLUMN + " DATETIME, "
            + DBConstants.TICKET_FOURTH_COLUMN + " BOOLEAN, " + " PRIMARY KEY ( " + DBConstants.TICKET_FIRST_COLUMN + " ))";

    /**
     * Ticket queries
     */
    static final String TICKET_NEW = "INSERT INTO " + DBConstants.TICKET_TABLE + " ("
            + DBConstants.TICKET_FIRST_COLUMN + ", " + DBConstants.TICKET_SECOND_COLUMN + ", "
            + DBConstants.TICKET_THIRD_COLUMN + ", " + DBConstants.TICKET_FOURTH_COLUMN + ") " + "VALUES (?, ?, ?, ?)";

    static final String TICKET_UPDATE = "UPDATE " + DBConstants.TICKET_TABLE + " SET "
            + DBConstants.TICKET_SECOND_COLUMN + " = ?, " + DBConstants.TICKET_THIRD_COLUMN + " = ?, "
            + DBConstants.TICKET_FOURTH_COLUMN + " = ? " + "WHERE " + DBConstants.TICKET_FIRST_COLUMN + " = ?";

    static final String TICKET_REMOVE = "DELETE FROM " + DBConstants.TICKET_TABLE + " " + "WHERE "
            + DBConstants.TICKET_FIRST_COLUMN + " = ?";

    static final String TICKET_GET = "SELECT * FROM " + DBConstants.TICKET_TABLE + " " + "WHERE "
            + DBConstants.TICKET_FIRST_COLUMN + " = ?";

    /**
     * Level queries
     */
    static final String LEVEL_CREATE_TABLE = "CREATE TABLE " + DBConstants.LEVEL_TABLE + " ("
            + DBConstants.LEVEL_FIRST_COLUMN + " varchar(" + DBConstants.LEVEL_NAME_LENGTH + ") not NULL, "
            + DBConstants.LEVEL_SECOND_COLUMN + " INT not NULL, " + DBConstants.LEVEL_THIRD_COLUMN + " INT not NULL, "
            + " PRIMARY KEY ( " + DBConstants.LEVEL_FIRST_COLUMN + " ))";

    static final String LEVEL_NEWRECORD = "INSERT INTO " + DBConstants.LEVEL_TABLE + " ("
            + DBConstants.LEVEL_FIRST_COLUMN + ", " + DBConstants.LEVEL_SECOND_COLUMN + ", "
            + DBConstants.LEVEL_THIRD_COLUMN + ") " + "VALUES (?, ?, ?)";

    static final String LEVEL_REMOVE_RECORD = "DELETE FORM" + DBConstants.LEVEL_TABLE + " " + "WHERE "
            + DBConstants.LEVEL_FIRST_COLUMN + " = ?";

    static final String LEVEL_ADD_AVAILABILITY = " UPDATE " + DBConstants.LEVEL_TABLE
            + " SET " + DBConstants.LEVEL_SECOND_COLUMN + " = " + DBConstants.LEVEL_SECOND_COLUMN
            + " + 1 WHERE " + DBConstants.LEVEL_FIRST_COLUMN + " = ? ";


}
