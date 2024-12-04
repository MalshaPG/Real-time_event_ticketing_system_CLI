package org.classes;

import java.math.BigDecimal;

public class Vendor implements Runnable{
    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;//from the JSON file

    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Event event = new Event("Summer Night", "Youth Center, Maharagama", "2025-03-02", "10.00PM");
            Ticket ticket = new Ticket(event, i, new BigDecimal(1000.00), "2024-12-27");
            ticketPool.addTicket(ticket);
            try{
                Thread.sleep(ticketReleaseRate * 1000);
            }catch(InterruptedException e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }

}
