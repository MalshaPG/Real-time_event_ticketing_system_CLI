package org.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;


/**+
 *Configuration class to create configuration instances
 */
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    /**
     * Constructor
     *
     * @param totalTickets Number of tickets a vendor can add to the ticket pool at a time.
     * @param ticketReleaseRate How frequently vendors add tickets.
     * @param customerRetrievalRate How often customers purchase tickets.
     * @param maxTicketCapacity Maximum ticket capacity of the ticket pool.
     */
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity){
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    //Getters and Setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    /**
     * Covert configuration java object to JSON format using GSON library
     * Save the configuration data
     *
     * @param filePath Path of the file which configuration data are saved to.
     */
    public void saveConfiguration(String filePath){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            FileWriter filewriter = new FileWriter(filePath);
            gson.toJson(this, filewriter);
            System.out.println("configuration saved as: "+ filePath);
            filewriter.close();
        }
        catch(IOException e){
            System.out.println("Error" + e.getMessage());
        }
    }

}
