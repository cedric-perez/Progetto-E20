package it.unipv.ingsw.progettoe20.server.database;


public class Queries {

    static final String LIST_DB = "SHOW DATABASES;";
    static final String CREATE_DB = ("CREATE DATABASE " + DBConstants.DB_NAME);
    static final String USE_DB = "USE "; // could be better
    static final String CREATE_TABLE = "CREATE TABLE " + DBConstants.PARKED_TABLE + " (" +
            DBConstants.PARKED_FIRST_COLUMN + " varchar(" + DBConstants.ID_LENGTH + ") not NULL, " +
            DBConstants.PARKED_SECOND_COLUMN + " DATETIME, " +
            DBConstants.PARKED_THIRD_COLUMN + " DATETIME, " +
            DBConstants.PARKED_FOURTH_COLUMN +" BOOLEAN, " +
            " PRIMARY KEY ( id ))";
    static final String PARKED_NEWRECORD = "INSERT INTO " + DBConstants.PARKED_TABLE + " (" +
            DBConstants.PARKED_FIRST_COLUMN + ", " +
            DBConstants.PARKED_SECOND_COLUMN + ", " +
            DBConstants.PARKED_FOURTH_COLUMN + ") " +
            "VALUES (?, NOW(), FALSE)";
    static final String PARKED_CHECK_ID_EXISTENCE = "SELECT * FROM " + DBConstants.PARKED_TABLE + " " +
            "WHERE " + DBConstants.PARKED_FIRST_COLUMN + " = ?";
    static final String PARKED_SET_PAYMENT = "UPDATE " + DBConstants.PARKED_TABLE + " " +
            "SET " + DBConstants.PARKED_THIRD_COLUMN + " = NOW(), " +
            DBConstants.PARKED_FOURTH_COLUMN + " = TRUE " +
            "WHERE " + DBConstants.PARKED_FIRST_COLUMN + " = ?";
    static final String PARKED_REMOVE_RECORD = "DELETE FROM " + DBConstants.PARKED_TABLE + " " +
            "WHERE " + DBConstants.PARKED_FIRST_COLUMN + " = ?";
    static final String PARKED_CHECK_PAYMENT = PARKED_CHECK_ID_EXISTENCE + " AND "+
            DBConstants.PARKED_FOURTH_COLUMN+" = TRUE ";
}
