package org.classes;

import com.google.gson.Gson;
import org.configuration.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Main class to simulate a multithreaded ticketing system
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Prompt the user to start the ticketing system
        System.out.println("Do you want to start the system(Y/N): ");
        boolean runSystem = isConfirmationYes(sc.nextLine().toUpperCase(), "Staring the system..");

        if (!runSystem){
            // User choose not to start, terminate from the system
            System.out.println("System stopping..");
            System.exit(0);
        }

        // Load and save the configuration data in the JSON format to a java object
        Configuration config = readJson();

        //Handle null pointer exception
        if (config == null) {
            throw new NullPointerException();
        }

        //Declare and initialize configuration data which was previously loaded from the JSON file
        int maxTicketCapacity = config.getMaxTicketCapacity();
        int ticketReleaseRate = config.getTicketReleaseRate();
        int customerRetrievalRate = config.getCustomerRetrievalRate();
        int totalTickets = config.getTotalTickets();

        // Initialize the ticket pool
        TicketPool ticketPool = new TicketPool(maxTicketCapacity);

        Thread[] threads = new Thread[15]; //Arrays to store vendor and customer threads
        int threadCount = 0; //Keep the tack of thread count in the threads array

        try {
            // create 5  vendors for the simulation
            Vendor[] vendors = new Vendor[5];

            for (int i = 0; i < vendors.length; i++) {
                vendors[i] = new Vendor(ticketPool, totalTickets, ticketReleaseRate);
                Thread vendorThread = new Thread(vendors[i], "vendor - " + i);
                threads[threadCount++] = vendorThread; //Store the thread in the 'threads' array
                vendorThread.start(); //start the vendor thread
            }

            //create 10 customers for the simulation
            Customer[] customers = new Customer[10];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = new Customer(ticketPool, customerRetrievalRate, 2);
                Thread customerThread = new Thread(customers[i], "customer - " + i);
                threads[threadCount++] = customerThread; //Store the thread in the 'threads' array
                customerThread.start(); //Start the customer thread
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());//Catch any unexpected threads
        }

        //Keep running until user wants to stop
        while(runSystem) {
            // Check if the user wants to continue the program or not
            System.out.println("Do you want to continue the system(Y/N): ");
            runSystem= isConfirmationYes(sc.nextLine().toUpperCase(), "Exiting..");

            if (!runSystem) {
                // Interrupt all threads
                for (Thread thread : threads) {
                    thread.interrupt();
                }

                // Wait for threads to finish
                for (Thread thread : threads) {
                    try {
                        // Wait up to 1 sec for each thread
                        thread.join(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted Exception" + e.getMessage());
                    }
                }
                System.out.println("System has stopped.");
                System.exit(0);//Terminate the program
            }
        }
    }


    /**
     * Read configuration.json file,
     * load the configuration data,
     * convert it to a java object
     *
     * @return a configuration object
     */
    public static Configuration readJson(){
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("configuration.json");
            return (gson.fromJson(fileReader, Configuration.class));

        }
        catch (FileNotFoundException e) {
            System.out.println("Configuration File not found: " + e.getMessage());
        }
        System.out.println("Please configure the system and try again.");
        System.exit(0);
        return null;
    }

    /**
     * Check the user inputs(start/stop commands)
     *
     * @param status The user's input
     * @param message The message that will display if the confirmation is yes
     * @return true if the user wants to run or continue running the system
     */
    public static boolean isConfirmationYes(String status, String message){
        if(status.equals("Y") || status.equals("YES") ){
            System.out.println(message);
            return true;
        }
        else{
            return false;
        }
    }
}