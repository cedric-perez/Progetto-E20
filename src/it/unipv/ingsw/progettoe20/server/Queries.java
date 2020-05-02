package it.unipv.ingsw.progettoe20.server;


public class Queries {

    static final String LIST_DB = "SHOW DATABASES;";
    static final String CREATE_DB = ("CREATE DATABASE " + DBConstants.DB_NAME);
    static final String USE_DB = "USE "; // could be better
    static final String CREATE_TABLE = "CREATE TABLE " + DBConstants.PARKED_TABLE + " (" +
            DBConstants.PARKED_TABLE_FIRST + " varchar(" + DBConstants.ID_LENGTH + ") not NULL, " +
            DBConstants.PARKED_TABLE_SECOND + " DATETIME, " +
            DBConstants.PARKED_TABLE_THIRD + " DATETIME, " +
            DBConstants.PARKED_TABLE_FOURTH +" BOOLEAN, " +
            " PRIMARY KEY ( id ))";
}
