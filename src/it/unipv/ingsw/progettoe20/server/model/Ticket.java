package it.unipv.ingsw.progettoe20.server.model;

import it.unipv.ingsw.progettoe20.server.ServerConstants;
import it.unipv.ingsw.progettoe20.server.database.DBConstants;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Ticket {
    private String id;
    private Timestamp entranceTime;
    private Timestamp paymentTime;
    private boolean paid;

    public Ticket(String id) throws IllegalArgumentException {
        checkIdLength(id);
        this.id = id;
        entranceTime = new Timestamp(System.currentTimeMillis());
        paymentTime = null;
        paid = false;
    }

    public Ticket(String id, Timestamp entranceTime, Timestamp paymentTime, boolean paid) throws IllegalArgumentException {
        checkIdLength(id);
        this.id = id;
        this.entranceTime = entranceTime;
        this.paymentTime = paymentTime;
        this.paid = paid;
    }

    private void checkIdLength(String id) throws IllegalArgumentException {
        if (id.length() != DBConstants.TICKET_ID_LENGTH) {
            throw new IllegalArgumentException("ID length must be " + DBConstants.TICKET_ID_LENGTH + "!");
        }
    }

    public String getId() {
        return id;
    }

    public Timestamp getEntranceTime() {
        return entranceTime;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }


    /**
     * metodo che verifica la correttezza dell'obliterazione (flag e data pagamento)
     */

    public boolean obliterationCheck() {

        Timestamp callTime = new Timestamp(System.currentTimeMillis());
        long diff = callTime.getTime() - paymentTime.getTime();
        long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);

        return (paid && (diffSeconds <= ServerConstants.TICKET_MAX_EXIT_TIME_TOTAL_SECONDS));
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
