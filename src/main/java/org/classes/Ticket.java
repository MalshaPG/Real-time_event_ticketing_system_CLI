package org.classes;

import java.math.BigDecimal;

/**
 * Blueprint for creating ticket objects
 */
public class Ticket{
    private int ticketID;
    private BigDecimal price;
    private Event event;
    private String releaseDate;

    /**
     * Constructor
     *
     * @param event The event associated with the ticket
     * @param ticketID The unique identifying number of a ticket
     * @param price The price of the ticket
     * @param releaseDate The release date of the ticket
     */
    public Ticket(Event event, int ticketID, BigDecimal price, String releaseDate) {
        this.event = event;
        this.ticketID = ticketID;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    //getters and Setters
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

    /**
     * @return String containing ticket details
     */
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
