package org.configuration;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConfigurationCLI {
    public static void main(String[] args){
        try {
            System.out.println("Enter the total number of tickets(range 0-20): ");
            int totalTickets = validInputCheck(0, 20);

            System.out.println("Enter the ticket release rate(Released ticket per hour)(range 0-20): ");
            int ticketReleaseRate = validInputCheck(0, 20);

            System.out.println("Enter the customer retrieval rate(Retrieved tickets per hour)(range 0-20): ");
            int customerRetrievalRate = validInputCheck(0, 20);

            System.out.println("Enter the maximum ticket capacity((range 0-20)): ");
            int maxTicketCapacity = validInputCheck(0, 20);

            Configuration configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

            configuration.saveConfiguration("configuration.json");
        }
        catch(Exception e){
            System.out.println("Sorry an error occurred." + e.getMessage());
        }
    }

    public static int validInputCheck(int minValue, int maxValue) {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        boolean validInput = false;

        while(!validInput){
            try {
                input = Integer.parseInt(sc.nextLine());
                if (input >= minValue && input <= maxValue){
                    validInput = true;
                } else {
                    System.out.println("The input must be between " + minValue + "-" + maxValue + ". Try again: ");
                }
            } catch(IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid integer: ");
            }
        }
        sc.close();
        return input;
    }

}
