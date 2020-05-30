package it.unipv.ingsw.progettoe20.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import it.unipv.ingsw.progettoe20.ErrorStrings;
import it.unipv.ingsw.progettoe20.server.admin.model.Administrator;
import it.unipv.ingsw.progettoe20.server.admin.view.AdministratorGUI;
import it.unipv.ingsw.progettoe20.server.cli.CommandLineInterface;
import it.unipv.ingsw.progettoe20.server.database.DatabaseFacade;

public class ServerFacade {

	public static void start() {
		DatabaseFacade dbManager = DatabaseFacade.getInstance();
		CommandLineInterface cli = new CommandLineInterface();

		dbManager.initDatabase();
		cli.start();

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
			System.out.println(ErrorStrings.SERVER_START_FAIL);
		}
	}
}
