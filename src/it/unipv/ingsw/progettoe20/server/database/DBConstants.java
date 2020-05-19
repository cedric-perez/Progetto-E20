package it.unipv.ingsw.progettoe20.server.database;

public class DBConstants {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://pusi77.ddns.net:33077"; // for testing purposes, will change when ready
    public static final int MAX_CONNECTIONS = 32; // MariaDB max should be 150 concurrent connections
    public static final String USER = "root";  // for testing purposes, will change when ready
    public static String PASS;

    public static final String CARS_DB_NAME = "parking";
    public static final String CARS_TABLE = "parkedcars";
    public static final String CARS_FIRST_COLUMN = "ID";
    public static final String CARS_SECOND_COLUMN = "EntranceTime";
    public static final String CARS_THIRD_COLUMN = "PaymentTime";
    public static final String CARS_FOURTH_COLUMN = "Paid";
    public static final int CARS_ID_LENGTH = 8;
    public static final int CARS_MAX_EXIT_TIME = 30;

    public static final String LEVEL_DB_NAME = "levelsdb";
    public static final String LEVEL_TABLE = "levels";
    public static final String LEVEL_FIRST_COLUMN = "Name";
    public static final String LEVEL_SECOND_COLUMN = "Available";
    public static final String LEVEL_THIRD_COLUMN = "Total";
    public static final int LEVEL_NAME_LENGTH = 1;
}
