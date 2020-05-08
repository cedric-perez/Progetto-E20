package it.unipv.ingsw.progettoe20.server;


import java.sql.SQLException;

public class ParkingManager {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        try {
            dbManager.initDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
