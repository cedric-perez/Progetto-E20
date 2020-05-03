package it.unipv.ingsw.progettoe20.server;

public class DBConstants {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.1.5:3306"; // for testing purposes, will change when ready
    static final String USER = "root";  // for testing purposes, will change when ready
    static final String PASS = "kekkek";    //hardcoded password = bad
    static final String DB_NAME = "parking";
    static final String PARKED_TABLE = "parkedcars";
    static final String PARKED_FIRST_COLUMN = "ID";
    static final String PARKED_SECOND_COLUMN = "EntranceTime";
    static final String PARKED_THIRD_COLUMN = "PaymentTime";
    static final String PARKED_FOURTH_COLUMN = "Paid";
    static final int ID_LENGTH = 8;

}
