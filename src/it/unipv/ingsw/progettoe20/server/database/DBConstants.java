package it.unipv.ingsw.progettoe20.server.database;

public class DBConstants {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://pusi77.ddns.net:33077"; // for testing purposes, will change when ready
    public static final int MAX_CONNECTIONS = 32; // MariaDB max should be 150 concurrent connections
    public static final String USER = "root";  // for testing purposes, will change when ready
    public static String PASS;
    public static final String DB_NAME = "parking";
    public static final String PARKED_TABLE = "parkedcars";
    public static final String PARKED_FIRST_COLUMN = "ID";
    public static final String PARKED_SECOND_COLUMN = "EntranceTime";
    public static final String PARKED_THIRD_COLUMN = "PaymentTime";
    public static final String PARKED_FOURTH_COLUMN = "Paid";
    public static final int ID_LENGTH = 8;

}
