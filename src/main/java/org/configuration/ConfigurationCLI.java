package org.configuration;

import java.util.Scanner;

public class ConfigurationCLI {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the total number of tickets: ");
            int totalTickets = positiveInt(sc.nextInt());

            System.out.println("Enter the ticket release rate(Released ticket per hour): ");
            int ticketReleaseRate = positiveInt(sc.nextInt());

            System.out.println("Enter the customer retrieval rate(Retrieved tickets per hour): ");
            int customerRetrievalRate = positiveInt(sc.nextInt());

            System.out.println("Enter the maximum ticket capacity: ");
            int maxTicketCapacity = positiveInt(sc.nextInt());

            Configuration configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

            configuration.saveConfiguration("configuration.json");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static int positiveInt(int number) {
        Scanner sc = new Scanner(System.in);

        // Keep prompting until a positive integer is entered
        while (number <= 0) {
            System.out.println("Please enter a positive integer: ");
            number = sc.nextInt();
        }

        return number; // Return the valid number
    }

}
