package it.unipv.ingsw.progettoe20.server.database;

public class Queries {

    static final String LIST_DB = "SHOW DATABASES;";
    static final String CREATE_DB = "CREATE DATABASE "; // could be better, but with PreparedStatement not work because
    // of ''
    static final String USE_DB = "USE "; // same

    static final String CARS_CREATE_TABLE = "CREATE TABLE " + DBConstants.CARS_TABLE + " ("
            + DBConstants.CARS_FIRST_COLUMN + " varchar(" + DBConstants.CARS_ID_LENGTH + ") not NULL, "
            + DBConstants.CARS_SECOND_COLUMN + " DATETIME, " + DBConstants.CARS_THIRD_COLUMN + " DATETIME, "
            + DBConstants.CARS_FOURTH_COLUMN + " BOOLEAN, " + " PRIMARY KEY ( " + DBConstants.CARS_FIRST_COLUMN + " ))";

    static final String PARKING_NEWRECORD = "INSERT INTO " + DBConstants.CARS_TABLE + " ("
            + DBConstants.CARS_FIRST_COLUMN + ", " + DBConstants.CARS_SECOND_COLUMN + ", "
            + DBConstants.CARS_FOURTH_COLUMN + ") " + "VALUES (?, NOW(), FALSE)";

    static final String PARKING = "SELECT * FROM " + DBConstants.CARS_TABLE + " " + "WHERE "
            + DBConstants.CARS_FIRST_COLUMN + " = ?";

    static final String PARKING_SET_PAYMENT = "UPDATE " + DBConstants.CARS_TABLE + " " + "SET "
            + DBConstants.CARS_THIRD_COLUMN + " = NOW(), " + DBConstants.CARS_FOURTH_COLUMN + " = TRUE " + "WHERE "
            + DBConstants.CARS_FIRST_COLUMN + " = ?";

    static final String PARKING_REMOVE_RECORD = "DELETE FROM " + DBConstants.CARS_TABLE + " " + "WHERE "
            + DBConstants.CARS_FIRST_COLUMN + " = ?";

    static final String PARKING_CHECK_PAYMENT_FLAG = PARKING + " AND " + DBConstants.CARS_FOURTH_COLUMN + " = TRUE ";

    static final String PARKING_CHECK_PAYMENT = PARKING_CHECK_PAYMENT_FLAG +
            " AND TIMEDIFF(NOW() ," + DBConstants.CARS_THIRD_COLUMN
			+ " )<'00:" + DBConstants.CARS_MAX_EXIT_TIME + ":00'";

    static final String LEVEL_CREATE_TABLE = "CREATE TABLE " + DBConstants.LEVEL_TABLE + " ("
            + DBConstants.LEVEL_FIRST_COLUMN + " varchar(" + DBConstants.LEVEL_NAME_LENGTH + ") not NULL, "
            + DBConstants.LEVEL_SECOND_COLUMN + " INT not NULL, " + DBConstants.LEVEL_THIRD_COLUMN + " INT not NULL, "
            + " PRIMARY KEY ( " + DBConstants.LEVEL_FIRST_COLUMN + " ))";

    static final String LEVEL_NEWRECORD = "INSERT INTO " + DBConstants.LEVEL_TABLE + " ("
            + DBConstants.LEVEL_FIRST_COLUMN + ", " + DBConstants.LEVEL_SECOND_COLUMN + ", "
            + DBConstants.LEVEL_THIRD_COLUMN + ") " + "VALUES (?, ?, ?)";

    static final String LEVEL_REMOVE_RECORD = "DELETE FORM" + DBConstants.LEVEL_TABLE + " " + "WHERE "
            + DBConstants.LEVEL_FIRST_COLUMN + " = ?";
}
