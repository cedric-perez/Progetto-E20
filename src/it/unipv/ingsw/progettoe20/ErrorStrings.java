package it.unipv.ingsw.progettoe20;

import it.unipv.ingsw.progettoe20.server.database.DBConstants;

public class ErrorStrings {
    public static final String ID_NOT_FOUND = "Can't find ticket in database!";

    public  static final String LEVEL_NOT_FOUND = "Can't find level in database!";
    public static final String WRONG_LEVEL_NAME_LENGTH = "Level's name must be " + DBConstants.LEVEL_NAME_LENGTH +
            " long!";
}
