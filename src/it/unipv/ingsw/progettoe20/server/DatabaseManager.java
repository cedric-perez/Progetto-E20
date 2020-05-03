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

public class DatabaseManager{

private BasicDataSource connectionPool;

    public DatabaseManager() {
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

    public void initDatabase() throws SQLException {
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

    public List<String> getDatabaseList() throws SQLException {
        ArrayList<String> response = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(Queries.LIST_DB);
        while (rs.next()) {
            response.add(rs.getString(1));  // 1 = first column
        }
        return response;
    }

    public void newRecord(String id) throws SQLException, IllegalArgumentException {
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

    public void checkInjection(String string) throws IllegalArgumentException {
        if (string.contains("'") || string.contains(")") || string.contains(";") || string.contains("-")) {
            throw new IllegalArgumentException("Nice try");
        }
    }
}
