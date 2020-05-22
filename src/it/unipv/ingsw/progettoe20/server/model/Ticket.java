package it.unipv.ingsw.progettoe20.server.model;

import it.unipv.ingsw.progettoe20.server.database.DBConstants;

import java.sql.Timestamp;

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

    private void checkIdLength(String id) throws  IllegalArgumentException {
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

    public boolean isPaid() {
        return paid;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
