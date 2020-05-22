package it.unipv.ingsw.progettoe20.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import it.unipv.ingsw.progettoe20.server.admin.model.Administrator;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

public class ParkingManager {

	public static void main(String[] args) {
		DatabaseFacade dbManager = new DatabaseFacade();

		dbManager.initDatabase();

		// creazione dell'administrator
		Administrator.create(dbManager);

		try {
			// Start server
			ServerSocket server = new ServerSocket(ServerConstants.PORT);
			while (true) {
				Socket socket = server.accept();
				PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
				RequestHandler requestHandler = new RequestHandler(dbManager, socketWriter);
				ClientHandler clientHandler = new ClientHandler(socket, requestHandler,
						ServerConstants.HANDLER_THREAD_NAME);
				clientHandler.start();
			}
		} catch (IOException e) {
			System.out.println("Can't start server!");
		}
	}
}
