package org.classes;

import com.google.gson.Gson;
import org.configuration.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean startSystem;

        System.out.println("Do you want to start the ticketing system(Y/N) : ");
        String status = sc.next().toUpperCase();

        startSystem = systemStatus(status);

        while(startSystem) {
            try {
                Configuration config = readJson();
                int maximumCapacity = config.getMaxTicketCapacity();
                int ticketReleaseRate = config.getTicketReleaseRate();
                int customerRetrievalRate = config.getCustomerRetrievalRate();
                int totalTickets = config.getTotalTickets();

                TicketPool ticketPool = new TicketPool(maximumCapacity);

                Vendor[] vendors = new Vendor[10];//convenience purpose

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
        }
        System.out.println("System has stopped.");

    }


    public static Configuration readJson(){
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("configuration.json");
            return (gson.fromJson(fileReader, Configuration.class));

        }//IOException?
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return null;

    }


    public static boolean systemStatus(String status){
        if(status.equals("Y") || status.equals("YES") ){
            System.out.println("System is starting...");
            return true;
        }
        return false;
    }

    public static boolean systemStop(){
        if(status.equals("Y")){
            System.out.print("System is stopping...");
        }
    }

}