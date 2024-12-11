package org.classes;

import java.math.BigDecimal;

/**
 * Vendor class for creating vendor objects
 */
public class Vendor implements Runnable{
    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;

    /**
     *Constructor
     *
     * @param ticketPool Shared ticket pool where tickets are added and removed.
     * @param totalTickets Number of tickets a vendor can add to the ticket pool at a time.
     * @param ticketReleaseRate How frequently vendors add tickets.
     */
    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    /**
     *Executes the vendor thread.
     * If interrupted, thread will exit gracefully
     */
    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            try{
                //Create event object
                Event event = new Event("Summer Night", "Youth Center, Maharagama", "2025-03-02", "10.00PM");

                //Create ticket object
                Ticket ticket = new Ticket(event, i, new BigDecimal(1000.00), "2024-12-27");

                //Add the ticket to the ticketpool
                ticketPool.addTicket(ticket);

                //Wait for the defined intervals before adding the next ticket.
                Thread.sleep(ticketReleaseRate * 1000);

            } catch (InterruptedException e) {
                System.out.println("Thread got interrupted." + e.getMessage());
                Thread.currentThread().interrupt();//handle interruption
                break;//Exit the loop
            } catch (RuntimeException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error: " + e.getMessage());
            }
        }

    }

}
