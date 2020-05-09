package it.unipv.ingsw.progettoe20.server;


import it.unipv.ingsw.progettoe20.server.database.DatabaseManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ParkingManager {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        try {
            dbManager.initDatabase();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        try {
            ServerSocket server = new ServerSocket(ServerConstants.SERVER_PORT);
            while (true) {
                Socket socket = server.accept();
                ClientHandler handler = new ClientHandler(socket, dbManager, ServerConstants.HANDLER_THREAD_NAME);
                handler.start();
            }
        } catch (IOException e) {
            System.out.println("Server can't start");
            e.printStackTrace();
        }
    }
}
