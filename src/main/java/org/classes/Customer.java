package org.classes;

/**
 * Customer class ,for creating customer objects
 */
public class Customer implements Runnable{
    private TicketPool ticketpool;
    private int customerRetrievalRate;
    private int quantity;

    /**
     * Constructor
     *
     * @param ticketpool Where vendors add tickets to and customers buy ticket from
     * @param customerRetrievalRate How often customers purchase tickets.
     * @param quantity The number of tickets that the customer will buy at a time
     */
    public Customer(TicketPool ticketpool, int customerRetrievalRate, int quantity) {
        this.ticketpool = ticketpool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    /**
     *Run method override the run method of the Thread class
     * Execute the customer Thread
     * If the thread got interrupted, it will exit gracefully
     */
    @Override
    public void run(){
        for (int i = 0; i < quantity; i++) {
            try{
                //Call the buy method on the ticket pool
                ticketpool.buyTicket();

                //Wait for (customerRetrievalRate * 1000) milliseconds before the next purchase.
                Thread.sleep(customerRetrievalRate * 1000);
            }catch(InterruptedException e){
                System.out.println("Thread got interrupted." + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            } catch (RuntimeException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

}
