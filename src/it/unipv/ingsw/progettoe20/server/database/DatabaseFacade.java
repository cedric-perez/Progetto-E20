package it.unipv.ingsw.progettoe20.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

/*  Resources
JDBC: https://www.tutorialspoint.com/jdbc/
Database Connection Pooling: https://devcenter.heroku.com/articles/database-connection-pooling-with-java
BasicDataSource Doc: https://commons.apache.org/proper/commons-dbcp/api-1.2.2/org/apache/commons/dbcp/BasicDataSource.html
Statement execute/executeQuery/executeUpdate: https://www.edureka.co/community/12548/java-execute-vs-executequery-vs-executeupdate
PreparedStatement usage: https://www.javatpoint.com/PreparedStatement-interface
 */

import it.unipv.ingsw.progettoe20.ErrorStrings;
import it.unipv.ingsw.progettoe20.server.model.Level;
import it.unipv.ingsw.progettoe20.server.model.Ticket;

/**
 * Gestisce la connessione al database.
 */
public class DatabaseFacade {
	// TODO: this class should be splitted according to patterns
	private static DatabaseFacade dbFacade = null;
	private BasicDataSource connectionPool;

	/**
	 * Costruisce un nuovo DatabaseFacade. Chiede la password del database, la
	 * controlla e inizializza la connessione. Il pool di connessioni viene
	 * inizializzato ad una connessione.
	 */
	private DatabaseFacade() {
		passwordInit();

		String dbUrl = DBConstants.DB_URL;
		connectionPool = new BasicDataSource();
		connectionPool.setUsername(DBConstants.USER);
		connectionPool.setPassword(DBConstants.PASS);
		connectionPool.setDriverClassName(DBConstants.JDBC_DRIVER);
		connectionPool.setUrl(dbUrl);
		connectionPool.setMaxTotal(DBConstants.MAX_CONNECTIONS);
		System.out.print("Connecting...");
		connectionPool.setInitialSize(1);
		System.out.println("done");
	}

	public static DatabaseFacade getInstance() {
		if (dbFacade == null) {
			dbFacade = new DatabaseFacade();
		}
		return dbFacade;
	}

	/**
	 * Chiede e controlla la password inserita finché non è corretta.
	 */
	private void passwordInit() {
		String password;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("Enter database password for user " + DBConstants.USER + ": ");
			password = scanner.nextLine();
		} while (!checkPassword(password));
	}

	/**
	 * Controlla che la password del database sia corretta. Per fare ciò tenta una
	 * connessione al database.
	 *
	 * @param password La password da controllare.
	 * @return true se la password è corretta, false altrimenti.
	 */
	private boolean checkPassword(String password) {
		try {
			Class.forName(DBConstants.JDBC_DRIVER);
			DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println("Password is incorrect, please try again");
			return false;
		}
		DBConstants.PASS = password;
		return true;
	}

	/**
	 * Inizializza il database, se non presente. Controlla la presenza del database,
	 * se assente crea il database e le table.
	 */
	public void initDatabase() {
		Connection connection;
		ArrayList<String> dbList;

		try {
			connection = connectionPool.getConnection();
			dbList = getDatabaseList();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		// TODO remove "use db" everywhere
		// Checks if database already exist
		if (!dbList.contains(DBConstants.TICKET_DB_NAME)) {
			try {
				// Creates database
				Statement stmt = connection.createStatement();
				System.out.print("Creating database \"" + DBConstants.TICKET_DB_NAME + "\"...");
				stmt.executeUpdate(Queries.CREATE_DB + DBConstants.TICKET_DB_NAME);
				System.out.println("done");

				// Create table for tickets
				stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
				System.out.print("Creating table for tickets \"" + DBConstants.TICKET_TABLE + "\"...");
				stmt.executeUpdate(Queries.CREATE_TABLE_TICKETS);
				System.out.println("done");

				// Create table for levels
				stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
				System.out.print("Creating table for levels \"" + DBConstants.LEVEL_TABLE + "\"...");
				stmt.executeUpdate(Queries.CREATE_TABLE_LEVELS);
				System.out.println("done");

				// Create table for prices
				stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
				System.out.print("Creating table for prices \"" + DBConstants.PRICES_TABLE + "\"...");
				stmt.executeUpdate(Queries.CREATE_TABLE_PRICES);
				System.out.println("done");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Database already set up, nothing done");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Restituisce un ArrayList di stringhe con i nomi dei database presenti.
	 *
	 * @return un ArrayList di stringhe con i nomi dei database presenti.
	 * @throws SQLException se ci sono problemi nell'accesso al database.
	 */
	private ArrayList<String> getDatabaseList() throws SQLException {
		ArrayList<String> response = new ArrayList<>();
		Connection connection = connectionPool.getConnection();

		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(Queries.LIST_DB);
		while (rs.next()) {
			response.add(rs.getString(1)); // 1 = first column
		}
		return response;
	}

	/**
	 * Modifica un record sul database, se non è presente, lo crea.
	 *
	 * @param ticket oggetto Ticket da salvare nel DB.
	 */
	public void updateTicket(Ticket ticket) {
		Connection connection;
		PreparedStatement pstmt;

		try {
			connection = connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			// throw some error
			return;
		}

		try {
			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);

			if (checkTicketById(ticket.getId())) {
				pstmt = connection.prepareStatement(Queries.TICKET_UPDATE);
				pstmt.setTimestamp(1, ticket.getEntranceTime());
				pstmt.setTimestamp(2, ticket.getPaymentTime());
				pstmt.setBoolean(3, ticket.isPaid());
				pstmt.setString(4, ticket.getId());
				pstmt.executeUpdate();
				System.out.println("Ticket " + ticket.getId() + " updated successfully");
			} else {
				pstmt = connection.prepareStatement(Queries.TICKET_NEW);
				pstmt.setString(1, ticket.getId());
				pstmt.setTimestamp(2, ticket.getEntranceTime());
				pstmt.setTimestamp(3, ticket.getPaymentTime());
				pstmt.setBoolean(4, ticket.isPaid());
				pstmt.executeUpdate();
				System.out.println("Ticket " + ticket.getId() + " created successfully");
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Controlla che un ticket sia presente nella table. Se non è presente lancia
	 * un'eccezione.
	 *
	 * @param id identificatore del record.
	 */
	public boolean checkTicketById(String id) {
		Connection connection;
		try {
			connection = connectionPool.getConnection();

			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
			PreparedStatement pstmt = connection.prepareStatement(Queries.TICKET_GET);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();
			if (!result.next()) {
				return false;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Rimuove il ticket passato come argomento dal database.
	 *
	 * @param ticket oggetto Ticket da rimuovere dal DB.
	 * @throws IllegalArgumentException se il ticket non è presente nel DB.
	 */
	public void removeTicket(Ticket ticket) throws IllegalArgumentException {
		try {
			if (!checkTicketById(ticket.getId())) {
				throw new IllegalArgumentException(ErrorStrings.ID_NOT_FOUND);
			}
			Connection connection = connectionPool.getConnection();

			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
			PreparedStatement pstmt = connection.prepareStatement(Queries.TICKET_REMOVE);
			pstmt.setString(1, ticket.getId());
			pstmt.executeUpdate();
			System.out.println(ticket.getId() + " removed from database");

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Restituisce un Ticket prelevato dal database, selezionato mediante ID.
	 *
	 * @param id identificatore del Ticket.
	 * @return ticket richiesto.
	 */
	public Ticket getTicketById(String id) {
		Ticket ticket;
		try {
			if (!checkTicketById(id)) {
				throw new IllegalArgumentException(ErrorStrings.ID_NOT_FOUND);
			}
			Connection connection = connectionPool.getConnection();

			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
			PreparedStatement pstmt = connection.prepareStatement(Queries.TICKET_GET);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();

			result.next();
			Timestamp entranceTime = result.getTimestamp(DBConstants.TICKET_SECOND_COLUMN);
			Timestamp paymentTime = result.getTimestamp(DBConstants.TICKET_THIRD_COLUMN);
			boolean paid = result.getBoolean(DBConstants.TICKET_FOURTH_COLUMN);
			ticket = new Ticket(id, entranceTime, paymentTime, paid);

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return ticket;
	}

	/**
	 * Modifica un record sul database, se non è presente, lo crea.
	 *
	 * @param level oggetto Level da salvare nel DB.
	 */
	public void updateLevel(Level level) {
		Connection connection;
		PreparedStatement pstmt;
		try {
			connection = connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			// throw some error
			return;
		}

		try {
			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);

			if (checkLevelByName(level.getName())) {
				pstmt = connection.prepareStatement(Queries.LEVEL_UPDATE);
				pstmt.setInt(1, level.getAvailable());
				pstmt.setInt(2, level.getTotal());
				pstmt.setString(3, level.getName());
				pstmt.executeUpdate();
				System.out.println("Level" + level.getName() + " updated successfully");
			} else {
				pstmt = connection.prepareStatement(Queries.LEVEL_NEW);
				pstmt.setString(1, level.getName());
				pstmt.setInt(2, level.getAvailable());
				pstmt.setInt(3, level.getTotal());
				pstmt.executeUpdate();
				System.out.println("Level " + level.getName() + " created successfully");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Controlla che un livello sia presente nella table. Se non è presente lancia
	 * un'eccezione.
	 *
	 * @param name identificatore del record.
	 */
	public boolean checkLevelByName(String name) throws IllegalArgumentException {
		Connection connection;
		try {
			connection = connectionPool.getConnection();

			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
			PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_GET);
			pstmt.setString(1, name.toUpperCase());
			ResultSet result = pstmt.executeQuery();
			if (!result.next()) {
				return false;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Rimuove il livello passato come argomento dal database.
	 *
	 * @param level oggetto Level da rimuovere dal DB.
	 * @throws IllegalArgumentException se il livello non è presente nel DB.
	 */
	public void removeLevel(Level level) throws IllegalArgumentException {
		try {
			if (!checkLevelByName(level.getName())) {
				throw new IllegalArgumentException(ErrorStrings.LEVEL_NOT_FOUND);
			}
			Connection connection = connectionPool.getConnection();

			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
			PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_REMOVE);
			pstmt.setString(1, level.getName());
			pstmt.executeUpdate();
			System.out.println(level.getName() + " removed from database");

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Restituisce un Level prelevato dal database, selezionato mediante nome.
	 *
	 * @param name identificatore del livello.
	 * @return Level richiesto.
	 */
	public Level getLevelByName(String name) {
		Level level;
		try {
			if (!checkLevelByName(name)) {
				throw new IllegalArgumentException(ErrorStrings.LEVEL_NOT_FOUND);
			}
			Connection connection = connectionPool.getConnection();

			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
			PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_GET);
			pstmt.setString(1, name.toUpperCase());
			ResultSet result = pstmt.executeQuery();

			result.next();
			int available = result.getInt(DBConstants.LEVEL_SECOND_COLUMN);
			int total = result.getInt(DBConstants.LEVEL_THIRD_COLUMN);
			level = new Level(name, available, total);

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return level;
	}
	/**
	 * Restituisce un Level prelevato dal database, selezionato mediante nome.
	 *
	 * @param name identificatore del livello.
	 * @return LevelList.
	 */
	public List<Level> getLevelList() {
		Level level;
		List<Level> levelList= new ArrayList<Level>();
		try {
			Connection connection = connectionPool.getConnection();

			Statement stmt = connection.createStatement();
			stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
			PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_GET_TOTAL);
			ResultSet result = pstmt.executeQuery();
			while(result.next()==true) {
			String name= result.getString(DBConstants.LEVEL_FIRST_COLUMN);
			int available = result.getInt(DBConstants.LEVEL_SECOND_COLUMN);
			int total = result.getInt(DBConstants.LEVEL_THIRD_COLUMN);
			level = new Level(name, available, total);
			levelList.add(level);
			}
			

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return levelList;
	}
	
}
