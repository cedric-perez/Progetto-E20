package it.unipv.ingsw.progettoe20.server;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.SQLException;

public class ParkingManager {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        // Some tests to check database, delete when writing this class
        try {
            dbManager.initDatabase();
            dbManager.newRecord("test5678");
        } catch (MySQLIntegrityConstraintViolationException sqle) {
            System.out.println("..il record con quella primarykey c'è già, questo non succederà quando introdurremo una generazione intelligente degli ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
