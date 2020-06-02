package it.unipv.ingsw.progettoe20.server.database;

public class DBConstants {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_NAME = "parking";
	public static final String DB_URL = "jdbc:mysql://pusi77.ddns.net:33077/" + DB_NAME;
	public static final int MAX_CONNECTIONS = 32; // MariaDB max should be 150 concurrent connections
	public static final String USER = "root";
	public static String PASS;

	public static final String TICKET_TABLE = "parkedcars";
	public static final String TICKET_FIRST_COLUMN = "ID";
	public static final String TICKET_SECOND_COLUMN = "EntranceTime";
	public static final String TICKET_THIRD_COLUMN = "PaymentTime";
	public static final String TICKET_FOURTH_COLUMN = "Paid";
	public static final int TICKET_ID_LENGTH = 8;

	public static final String LEVEL_TABLE = "levels";
	public static final String LEVEL_FIRST_COLUMN = "Name";
	public static final String LEVEL_SECOND_COLUMN = "Available";
	public static final String LEVEL_THIRD_COLUMN = "Total";
	public static final int LEVEL_NAME_LENGTH = 1;

	public static final String PRICES_TABLE = "prices";
	public static final String PRICES_FIRST_COLUMN = "Minutes";
	public static final String PRICES_SECOND_COLUMN = "Prices";

	// Minuti per la tariffa oraria
	public static final int MINUTES_HOURLY = 60;
	// Minuti per la tariffa minima
	public static final int MINUTES_MINIMUM = 30;
	// Minuti per la tariffa massima (8 ore)
	public static final int MINUTES_MAXIMUM = 480;

	public static final int TICKET_MAX_EXIT_TIME_DAYS = 0;
	public static final int TICKET_MAX_EXIT_TIME_HOURS = 0;
	public static final int TICKET_MAX_EXIT_TIME_MINUTES = 30;
	public static final int TICKET_MAX_EXIT_TIME_SECONDS = 0;
	public static final long TICKET_MAX_EXIT_TIME_TOTAL_SECONDS = TICKET_MAX_EXIT_TIME_SECONDS
			+ TICKET_MAX_EXIT_TIME_MINUTES * 60 + TICKET_MAX_EXIT_TIME_HOURS * 60 * 60
			+ TICKET_MAX_EXIT_TIME_DAYS * 24 * 60 * 60;

}