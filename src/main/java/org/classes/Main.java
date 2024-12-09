package org.classes;

import com.google.gson.Gson;
import org.configuration.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Get user input
        String userInput = getUserInput("Do you want to start the ticketing system(Y/N)").toUpperCase();

        // Check if user wants to continue
        boolean isConfirmed = isConfirmationYes(userInput);
        if (!isConfirmed){
            // User does not want to continue
            System.out.println("Exiting the program..");
            return;
        }

        // Load configuration
        Configuration config = readJson();

        int maxTicketCapacity = config.getMaxTicketCapacity();
        int ticketReleaseRate = config.getTicketReleaseRate();
        int customerRetrievalRate = config.getCustomerRetrievalRate();
        int totalTickets = config.getTotalTickets();

        // Initialize the ticket pool
        TicketPool ticketPool = new TicketPool(maxTicketCapacity);

        try {
            // create 10  vendors for simulation
            Vendor[] vendors = new Vendor[10];

            for (int i = 0; i < vendors.length; i++) {
                vendors[i] = new Vendor(ticketPool, totalTickets, ticketReleaseRate);
                Thread vendorThread = new Thread(vendors[i], "vendor - " + i);
                vendorThread.start();
            }

            Customer[] customers = new Customer[10];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = new Customer(ticketPool, customerRetrievalRate, 2);
                Thread customerThread = new Thread(customers[i], "customer - " + i);
                customerThread.start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while(isConfirmed) {
            // Check if the user wants to continue the program or not
            userInput = getUserInput("Do you want to continue the system(Y/N)").toUpperCase();

            if (isConfirmationYes(userInput)) {
                System.out.println("System has stopped.");
                System.exit(0);
            }
        }
    }


    public static Configuration readJson(){
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("configuration.json");
            // TODO: validate the structure of the config
            return (gson.fromJson(fileReader, Configuration.class));

        }//IOException?
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return null;

    }

    public static boolean isConfirmationYes(String status){
        if(status.equals("Y") || status.equals("YES") ){
            System.out.println("System is starting...");
            return true;
        }
        return false;
    }

    public static boolean systemStop(String stop){
        if(stop.equals("Y") || stop.equals("YES")){
            System.out.print("System is stopping...");
            return false;
        }
        return true;
    }

    public static String getUserInput(String userPrompt) {
        Scanner sc = new Scanner(System.in);
        if (!userPrompt.isEmpty()) {
            System.out.printf("%s : ", userPrompt);
        } else  {
            System.out.print("Input : ");
        }

        String userInput = "";
        try {
            userInput = sc.nextLine();
        } catch (Exception e) {
            //
        }
        return userInput;
    }

}