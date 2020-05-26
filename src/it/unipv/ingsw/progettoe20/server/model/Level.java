package it.unipv.ingsw.progettoe20.server.model;

import it.unipv.ingsw.progettoe20.ErrorStrings;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;

public class Level {
    private final String name;
    private int available;
    private int total;

    public Level(String name, int total) {
        if (!checkName(name)) {
            throw new IllegalArgumentException(ErrorStrings.WRONG_LEVEL_NAME_LENGTH);
        }
        this.name = name.toUpperCase();
        this.available = total;
        this.total = total;
    }

    public Level(String name, int available, int total) {
        this.name = name.toUpperCase();
        this.available = available;
        this.total = total;
    }

    public void increaseAvailable() {
        setAvailable(getAvailable() + 1);
    }

    private boolean checkName(String name) {
        return name.length() == DBConstants.LEVEL_NAME_LENGTH;
    }

    public String getName() {
        return name;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
