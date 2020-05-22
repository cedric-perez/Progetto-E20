package it.unipv.ingsw.progettoe20.server.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/*  Resources
JDBC: https://www.tutorialspoint.com/jdbc/
Database Connection Pooling: https://devcenter.heroku.com/articles/database-connection-pooling-with-java
BasicDataSource Doc: https://commons.apache.org/proper/commons-dbcp/api-1.2.2/org/apache/commons/dbcp/BasicDataSource.html
Statement execute/executeQuery/executeUpdate: https://www.edureka.co/community/12548/java-execute-vs-executequery-vs-executeupdate
PreparedStatement usage: https://www.javatpoint.com/PreparedStatement-interface
 */

import it.unipv.ingsw.progettoe20.ErrorStrings;
import it.unipv.ingsw.progettoe20.server.model.Ticket;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Gestisce la connessione al database.
 */
public class DatabaseFacade {
    //TODO: this class should be splitted according to patterns
    private BasicDataSource connectionPool;

    /**
     * Costruisce un nuovo DatabaseFacade. Chiede la password del database, la
     * controlla e inizializza la connessione. Il pool di connessioni viene
     * inizializzato ad una connessione.
     */
    public DatabaseFacade() {
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
     * se assente crea il database e la table.
     */
    public void initDatabase() {
        Connection connection;
        ArrayList<String> dbList;
        boolean changed = false;

        try {
            connection = connectionPool.getConnection();
            dbList = getDatabaseList();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Checks if database already exist
        if (!dbList.contains(DBConstants.TICKET_DB_NAME)) {
            try {
                // Creates database for cars
                Statement stmt = connection.createStatement();
                System.out.print("Creating database for cars \"" + DBConstants.TICKET_DB_NAME + "\"...");
                stmt.executeUpdate(Queries.CREATE_DB + DBConstants.TICKET_DB_NAME);
                System.out.println("done");

                // Create table for cars
                stmt.execute(Queries.USE_DB + DBConstants.TICKET_DB_NAME);
                System.out.print("Creating table for cars \"" + DBConstants.TICKET_TABLE + "\"...");
                stmt.executeUpdate(Queries.CREATE_TICKETS_TABLE);
                System.out.println("done");

                changed = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (!dbList.contains(DBConstants.LEVEL_DB_NAME)) {
            try {
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (!changed) {
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
     * Crea un nuovo record sul database. Necessita dell'ID come chiave primaria.
     * L'ora d'ingresso è impostata all'istante della richiesta al database.
     *
     * @param ticket oggetto Ticket da salvare nel DB.
     * @throws IllegalArgumentException
     */
    public void updateTicket(Ticket ticket) throws IllegalArgumentException {
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

            if (checkID(ticket.getId())){
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
     * Controlla che un ID sia presente nella table. Se non è presente lancia
     * un'eccezione.
     *
     * @param id identificatore del record.
     */
    public boolean checkID(String id) throws IllegalArgumentException {
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
     */
    public void removeRecord(Ticket ticket) throws IllegalArgumentException {
        try {
            if (!checkID(ticket.getId())) {
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
     * @param id identificatore del Ticket.
     * @return ticket richiesto.
     */
    public Ticket getTicketById(String id) {
        Ticket ticket;
        try {
            if (!checkID(id)) {
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

            System.out.println(entranceTime.toString() + paymentTime.toString() + paid);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return ticket;
    }


    //TODO: update level part to new style

    /**
     * Aggiunge un posto al livello corrispondente a quello del ticket id.
     *
     * @param id identificatore del record.
     */
    private void addAvailability(String id) throws IllegalArgumentException {
        Connection connection;
        try {
            connection = connectionPool.getConnection();

            Statement stmt = connection.createStatement();

            PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_ADD_AVAILABILITY);
            String level = id.substring(0, 1);
            pstmt.setString(1, level);
            stmt.execute(Queries.USE_DB + DBConstants.LEVEL_DB_NAME);
            pstmt.executeUpdate();

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Inserisce un nuovo livello nel database.
     *
     * @param name     nome del livello, dev'essere un solo carattere.
     * @param capacity numero di posti del livello.
     * @throws IllegalArgumentException se la lunghezza del nome è inferiore a 1
     *                                  oppure la capienza è < 1
     */
    public void newLevel(String name, int capacity) throws IllegalArgumentException {
        if (name.length() != 1) {
            throw new IllegalArgumentException("Level name must be 1 character long!");
        }
        if (capacity < 1) {
            throw new IllegalArgumentException("Level must have at least 1 parking!");
        }

        try {
            Connection connection = connectionPool.getConnection();

            Statement stmt = connection.createStatement();
            stmt.execute(Queries.USE_DB + DBConstants.LEVEL_DB_NAME);
            PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_NEWRECORD);
            pstmt.setString(1, name);
            pstmt.setInt(2, capacity);
            pstmt.setInt(3, capacity);
            pstmt.executeUpdate();

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Rimuove il record di un livello con nome uguale a name
     *
     * @param name del livello
     * @throws IllegalArgumentException se la lunghezza del nome è inferiore a 1
     */
    public void removeLevel(String name) throws IllegalArgumentException {
        if (name.length() != 1) {
            throw new IllegalArgumentException("Level name must be 1 character long!");
        }

        try {
            Connection connection = connectionPool.getConnection();

            Statement stmt = connection.createStatement();
            stmt.execute(Queries.USE_DB + DBConstants.LEVEL_DB_NAME);
            PreparedStatement pstmt = connection.prepareStatement(Queries.LEVEL_REMOVE_RECORD);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Level " + name + " removed from database");


            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
