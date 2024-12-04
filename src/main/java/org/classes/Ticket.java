package org.classes;

import java.math.BigDecimal;

public class Ticket{
    private int ticketID;
    private BigDecimal price;
    private Event event;
    private String releaseDate;

    public Ticket(Event event, int ticketID, BigDecimal price, String releaseDate) {
        this.event = event;
        this.ticketID = ticketID;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", price=" + price +
                ", event=" + event +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
