package it.unipv.ingsw.progettoe20.server;

public class Main {
    public static void main(String[] args) {
        ServerFacade serverFacade = new ServerFacade();
        serverFacade.init();
        serverFacade.start();
    }
}
