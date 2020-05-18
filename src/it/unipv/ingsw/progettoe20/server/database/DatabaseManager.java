package it.unipv.ingsw.progettoe20.server.database;

/*  Resources
JDBC: https://www.tutorialspoint.com/jdbc/
Database Connection Pooling: https://devcenter.heroku.com/articles/database-connection-pooling-with-java
BasicDataSource Doc: https://commons.apache.org/proper/commons-dbcp/api-1.2.2/org/apache/commons/dbcp/BasicDataSource.html
Statement execute/executeQuery/executeUpdate: https://www.edureka.co/community/12548/java-execute-vs-executequery-vs-executeupdate
PreparedStatement usage: https://www.javatpoint.com/PreparedStatement-interface
 */

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Gestisce la connessione al database.
 */
public class DatabaseManager {

    private BasicDataSource connectionPool;


    /**
     * Costruisce un nuovo DatabaseManager. Chiede la password del database, la contorlla e inizializza la connessione.
     * Il pool di connessioni viene inizializzato ad una connessione.
     */
    public DatabaseManager() {
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

    /**
     * Chiede e controlla la password inserita finché non è corretta.
     */
    private void passwordInit() {
        String password;

        do {
            password = askPassword();
        } while (!checkPassword(password));
    }

    /**
     * Legge la password da riga di comando.
     *
     * @return password inserita.
     */
    private String askPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter database password for user " + DBConstants.USER + ":");
        return scanner.nextLine();
    }

    /**
     * Controlla che la password del database sia corretta. Per fare ciò tenta una connessione al database.
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
     * Inizializza il database, se non presente. Controlla la presenza del database, se assente crea il database e la table.
     *
     * @throws SQLException se ci sono problemi nell'accesso al database.
     */
    public void initDatabase() throws SQLException {
        Connection connection = connectionPool.getConnection();
        boolean changed = false;

        // Checks if database already exist
        if (!getDatabaseList().contains(DBConstants.CARS_DB_NAME)) {

            // Creates database for cars
            Statement stmt = connection.createStatement();
            System.out.print("Creating database for cars \"" + DBConstants.CARS_DB_NAME + "\"...");
            stmt.executeUpdate(Queries.CREATE_DB + DBConstants.CARS_DB_NAME);
            System.out.println("done");

            // Create table for cars
            stmt.execute(Queries.USE_DB + DBConstants.CARS_DB_NAME);
            System.out.print("Creating table for cars \"" + DBConstants.CARS_TABLE + "\"...");
            stmt.executeUpdate(Queries.CARS_CREATE_TABLE);
            System.out.println("done");

            changed = true;
        }
        if (!getDatabaseList().contains(DBConstants.LEVEL_DB_NAME)) {
            // Creates database for levels
            Statement stmt = connection.createStatement();
            System.out.print("Creating database for levels \"" + DBConstants.LEVEL_DB_NAME + "\"...");
            stmt.executeUpdate(Queries.CREATE_DB + DBConstants.LEVEL_DB_NAME);
            System.out.println("done");

            // Create table for levels
            stmt.execute(Queries.USE_DB + DBConstants.LEVEL_DB_NAME);
            System.out.print("Creating table for levels \"" + DBConstants.LEVEL_TABLE + "\"...");
            stmt.executeUpdate(Queries.LEVEL_CREATE_TABLE);
            System.out.println("done");

            changed = true;
        }

        if (!changed) {
            System.out.println("Database already set up, nothing done");
        }
        connection.close();
    }

    /**
     * Restituisce un ArrayList di stringhe con i nomi dei database presenti.
     *
     * @return un ArrayList di stringhe con i nomi dei database presenti.
     * @throws SQLException se ci sono problemi nell'accesso al database.
     */
    List<String> getDatabaseList() throws SQLException {
        ArrayList<String> response = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(Queries.LIST_DB);
        while (rs.next()) {
            response.add(rs.getString(1));  // 1 = first column
        }
        connection.close();
        return response;
    }

    /**
     * Crea un nuovo record sul database. Necessita dell'ID come chiave primaria. L'ora d'ingresso è impostata all'istante della richiesta al database.
     *
     * @param id identificatore del record.
     * @throws SQLException             se ci sono problemi nell'accesso al database.
     * @throws IllegalArgumentException se la lunghezza dell'ID non è quella impostata.
     */
    public void newRecord(String id) throws SQLException, IllegalArgumentException {
    	 
        if (id.length() != DBConstants.CARS_ID_LENGTH) {
            throw new IllegalArgumentException("ID length must be " + DBConstants.CARS_ID_LENGTH + "!");
        }
       
        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute(Queries.USE_DB + DBConstants.CARS_DB_NAME);
        PreparedStatement pstmt = connection.prepareStatement(Queries.PARKING_NEWRECORD);
        pstmt.setString(1, id);
        pstmt.executeUpdate();
        System.out.println("Added new record with ID = " + id);
        connection.close();
    }
    /**
     * Imposta il PaymentTime di un record e ne setta il flag a true.
     *
     * @param id identificatore del record.
     * @throws SQLException se ci sono problemi nell'accesso al database.
     */
    public void setPaymentTime(String id) throws SQLException, IllegalArgumentException {
        checkID(id);

        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute(Queries.USE_DB + DBConstants.CARS_DB_NAME);
        PreparedStatement pstmt = connection.prepareStatement(Queries.PARKING_SET_PAYMENT);
        pstmt.setString(1, id);
        pstmt.executeUpdate();
        System.out.println("Payment time for " + id + " set");

        connection.close();
    }

    /**
     * Controlla che un ID sia presente nella table. Se non è presente lancia un'eccezione.
     *
     * @param id identificatore del record.
     * @throws SQLException se ci sono problemi nell'accesso al database.
     */
    public void checkID(String id) throws SQLException, IllegalArgumentException {
        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute(Queries.USE_DB + DBConstants.CARS_DB_NAME);
        PreparedStatement pstmt = connection.prepareStatement(Queries.PARKING);
        pstmt.setString(1, id);
        ResultSet result = pstmt.executeQuery();
        if (!result.next()) {
            System.out.println("No record with ID = " + id + " found");
            connection.close();
            throw new IllegalArgumentException("ID not found");
        }
        connection.close();
    }

    /**
     * Rimuove il record con un certo ID dal database.
     *
     * @param id identificatore del record.
     * @throws SQLException se ci sono problemi nell'accesso al database.
     */
    public void removeRecord(String id) throws SQLException, IllegalArgumentException {
        checkID(id);

        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute(Queries.USE_DB + DBConstants.CARS_DB_NAME);
        PreparedStatement pstmt = connection.prepareStatement(Queries.PARKING_REMOVE_RECORD);
        pstmt.setString(1, id);
        pstmt.executeUpdate();
        System.out.println(id + " removed from database");

        connection.close();
    }

    /**
     * Controlla se l'id ha il flag di pagamento.
     *
     * @param id identificatore del record.
     * @throws SQLException se ci sono problemi nell'accesso al database.
     * @throws IllegalArgumentException se il pagamento non è stato effettuato.
     */
    public void checkPayment(String id) throws SQLException, IllegalArgumentException {
        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute(Queries.USE_DB + DBConstants.CARS_DB_NAME);
        PreparedStatement pstmt = connection.prepareStatement(Queries.PARKING_CHECK_PAYMENT);
        pstmt.setString(1, id);
        ResultSet result = pstmt.executeQuery();
        if (!result.next()) {
            System.out.println("record with ID = " + id + " is not obliterated");
            connection.close();
            throw new IllegalArgumentException("Paid flag is false");
        }
        connection.close();
    }

    /**
     * Inserisce un nuovo livello nel database.
     *
     * @param name nome del livello, dev'essere un solo carattere.
     * @param capacity numero di posti del livello.
     * @throws SQLException se ci sono problemi nell'accesso al database.
     * @throws IllegalArgumentException se la lunghezza del nome è inferiore a 1 oppure la capienza è < 1
     */
    public void newLevel(String name, int capacity) throws SQLException, IllegalArgumentException {
        if (name.length() != 1) {
            throw new IllegalArgumentException("Level name must be 1 character long!");
        }
        if (capacity < 1) {
            throw new IllegalArgumentException("Level must have at least 1 parking!");
        }
        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute(Queries.USE_DB + DBConstants.LEVEL_DB_NAME);
        PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_NEWRECORD);
        pstmt.setString(1, name);
        pstmt.setInt(2, capacity);
        pstmt.setInt(3, capacity);
        pstmt.executeUpdate();

        connection.close();
    }
}
