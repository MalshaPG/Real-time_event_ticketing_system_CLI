package org.classes;

import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;
import java.util.logging.*;

/**
 * A shared ticket pool between vendors and customers
 * Use synchronized queue to manage tickets in a thread-safe manner.
 */
public class TicketPool {
    //Logger for logging ticket transactions
    private static final Logger ticketLogger = Logger.getLogger("Ticket_Log");
    private Queue<Ticket> tickets;//Queue for storing tickets
    private int maxTicketCapacity;


    /**
     *Constructor
     * Initialize a logger to achieve logging ticket transactions
     *
     * @param maximumCapacity Maximum ticket capacity of the ticket pool.
     */
    public TicketPool(int maximumCapacity) {
        this.maxTicketCapacity = maximumCapacity;
        this.tickets = new LinkedList<>();

        try {
            //Configure the logger to write logs to a file
            FileHandler fileHandler = new FileHandler("Tickets.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            ticketLogger.addHandler(fileHandler);
            ticketLogger.setUseParentHandlers(false);//Prevent duplicating console logs
            ticketLogger.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            System.out.println("Failed to setup the addLogger: " + e.getMessage());
        }
    }


    /**
     * Add tickets to the ticket pool
     *
     * @param ticket The added ticket to the ticket pool by a vendor
     */
    public synchronized void addTicket(Ticket ticket) {
        while (tickets.size() >= maxTicketCapacity) {
            try {
                //Wait until there is a space in the ticket pool
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception: " + e.getMessage());
            }
        }

        while (tickets.size() < maxTicketCapacity) {
            this.tickets.add(ticket);//Add the ticket to the ticktpool
            notifyAll();//Notify all waiting threads

            //Log the ticket transaction
            System.out.println(Thread.currentThread().getName() + " has added a ticket to the pool, current size is " + tickets.size());
            ticketLogger.info(Thread.currentThread().getName() + " has added a ticket to the pool, current size is " + tickets.size());
        }
    }


    /**
     * Remove tickets from the ticket pool
     * If the ticket pool is empty, the thread will wait until there are available tickets
     */
    public synchronized void buyTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        Ticket ticket = tickets.poll();//Remove a ticket from the ticketpool
        notifyAll();//Notify all waiting threads

        //Log the ticket transaction
        System.out.println(Thread.currentThread().getName() + " has bought a ticket from the pool, current size is " + tickets.size()+"\n Ticket is:" + ticket);
        ticketLogger.info(Thread.currentThread().getName() + " has bought a ticket from the pool \n Ticket is: "+ ticket);

    }
}
