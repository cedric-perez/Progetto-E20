package it.unipv.ingsw.progettoe20.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Logs events to plain text file.
 */
public class Logger {
    public static final String LOG_FILE = "ServerLOGS";
    private static PrintWriter writer = null;

    // Code executed as init
    static {
        checkFileExistence();
        try {
            writer = new PrintWriter(LOG_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write a string into logfile. Timestamp added by default.
     * @param log string to write.
     */
    public static void log(String log) {
        writer.println("[" + new Timestamp(System.currentTimeMillis()) + "]    " + log);
    }

    /**
     * Close the file.
     */
    public static void close() {
        writer.close();
    }

    /**
     * Check if logfile already exist, if so it will be renamed adding .old.
     */
    private static void checkFileExistence() {
        File f = new File(LOG_FILE);
        if(f.exists() && !f.isDirectory()) {
            File old = new File(LOG_FILE + ".old");
            f.renameTo(old);
        }
    }
}
