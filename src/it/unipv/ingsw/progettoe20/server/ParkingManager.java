package it.unipv.ingsw.progettoe20.server;

import java.sql.SQLException;

public class ParkingManager {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        // Some tests to check database, delete when writing this class
        try {
            dbManager.initDatabase();
            dbManager.newRecord("test5678");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
