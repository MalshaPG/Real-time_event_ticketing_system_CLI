package org.configuration;

import java.util.Scanner;

/**
 * ConfigurationCLI class prompts for valid user inputs to
 * configure the system before it start.
 */
public class ConfigurationCLI {
    /**
     *Main method - Run the CLI for system configuration.
     */
    public static void main(String[] args){
        try {
            //Prompt for configuration parameters.
            System.out.println("Enter the total number of tickets(range 0-20): ");
            int totalTickets = validInputCheck(0, 20);

            System.out.println("Enter the ticket release rate(range 5-10): ");
            int ticketReleaseRate = validInputCheck(5, 10);

            System.out.println("Enter the customer retrieval rate(range 5-10): ");
            int customerRetrievalRate = validInputCheck(5, 10);

            System.out.println("Enter the maximum ticket capacity((range 0-20)): ");
            int maxTicketCapacity = validInputCheck(0, 20);

            //Create configuration object with provided valid inputs.
            Configuration configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

            //Call the saveConfiguration function on the current object
            configuration.saveConfiguration("configuration.json");
        }
        catch(Exception e){
            System.out.println("Sorry an error occurred." + e.getMessage());
        }
    }

    /**
     * Validate the inputs
     * if input is invalid, prompt again for valid input
     *
     * @param minValue - Lower boundary of the input(inclusive)
     * @param maxValue - Upper boundary of the input(inclusive)
     * @return the valid input
     */
    public static int validInputCheck(int minValue, int maxValue) {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        boolean validInput = false;

        //Prompt for inputs until valid input is provided
        while(!validInput){
            try {
                //Access the input as String to catch any white spaces
                //Then covert it to an integer
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
        return input;
    }

}
