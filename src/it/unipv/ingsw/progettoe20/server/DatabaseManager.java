package it.unipv.ingsw.progettoe20.server;

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
public class DatabaseManager{

    private BasicDataSource connectionPool;

    /**
     * Costruisce un nuovo DatabaseManager. Chiede la password del database, la contorlla e inizializza la connessione.
     *      Il pool di connessioni viene inizializzato ad una connessione.
     */
    public DatabaseManager() {
        passwordInit();

        String dbUrl = DBConstants.DB_URL;
        connectionPool = new BasicDataSource();
        connectionPool.setUsername(DBConstants.USER);
        connectionPool.setPassword(DBConstants.PASS);
        connectionPool.setDriverClassName(DBConstants.JDBC_DRIVER);
        connectionPool.setUrl(dbUrl);
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
     * @return password inserita.
     */
    private String askPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter database password for user " + DBConstants.USER + ":");
        return scanner.nextLine();
    }

    /**
     * Controlla che la password del database sia corretta. Per fare ciò tenta una connessione al database.
     * @param password La password da controllare.
     * @return true se la password è corretta, false altrimenti.
     */
    private boolean checkPassword(String password) {
        try {
            Class.forName(DBConstants.JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, password);
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
     * @throws SQLException
     */
    void initDatabase() throws SQLException {
        // Checks if database already exist
        if (getDatabaseList().contains(DBConstants.DB_NAME)) {
            System.out.println("Database \"" + DBConstants.DB_NAME + "\" already exist, nothing done");
            return;
        }
        Connection connection = connectionPool.getConnection();

        // Creates database
        Statement stmt = connection.createStatement();
        System.out.print("Creating database \"" + DBConstants.DB_NAME + "\"...");
        stmt.executeUpdate(Queries.CREATE_DB);
        System.out.println("done");

        // Create table
        stmt.execute(Queries.USE_DB + DBConstants.DB_NAME);
        System.out.print("Created table \n" + DBConstants.PARKED_TABLE +"...");
        stmt.executeUpdate(Queries.CREATE_TABLE);
        System.out.println("done");
    }

    /**
     * Restituisce un ArrayList di stringhe con i nomi dei database presenti.
     * @return un ArrayList di stringhe con i nomi dei database presenti.
     * @throws SQLException
     */
    List<String> getDatabaseList() throws SQLException {
        ArrayList<String> response = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(Queries.LIST_DB);
        while (rs.next()) {
            response.add(rs.getString(1));  // 1 = first column
        }
        return response;
    }

    /**
     * Crea un nuovo record sul database. Necessita dell'ID come chiave primaria. L'ora d'ingresso è impostata all'istante della richiesta al database.
     * @param id identificatore del record.
     * @throws SQLException
     * @throws IllegalArgumentException se la lunghezza dell'ID non è quella impostata.
     */
    void newRecord(String id) throws SQLException, IllegalArgumentException {
        checkInjection(id);
        if (id.length() != DBConstants.ID_LENGTH) {
            throw new IllegalArgumentException("ID length must be " + DBConstants.ID_LENGTH + "!");
        }
        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute(Queries.USE_DB + DBConstants.DB_NAME);
        PreparedStatement pstmt = connection.prepareStatement(Queries.PARKED_NEWRECORD);
        pstmt.setString(1, id);
        pstmt.executeUpdate();
        System.out.println("Added new record with ID = " + id);
    }

    /**
     * Semplice controllo dei casi comuni di code injection.
     * @param string stringa da controllare.
     * @throws IllegalArgumentException se è stato identificato un tentativo di code injection.
     */
    void checkInjection(String string) throws IllegalArgumentException {
        if (string.contains("'") || string.contains(")") || string.contains(";") || string.contains("-")) {
            throw new IllegalArgumentException("Nice try");
        }
    }
}
