import java.util.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static String[] rows = {"A", "B", "C", "D"};
    static int [] priceRange250 = {1,2,3,4,5};
    static int [] priceRange150 = {6,7,8,9};
    static int [] priceRange180 = {10,11,12,13,14};
    static Ticket[] ticketInformation = new Ticket[51];
    static int[] row_A = new int[14];//arrays for seat count
    static int[] row_B = new int[12];
    static int[] row_C = new int[12];
    static int[] row_D = new int[14];
    static boolean ball = true;
    static Scanner input = new Scanner(System.in);
    static Ticket ticket;

    public static void main(String[] args) {

        Arrays.fill(row_A, 0);
        Arrays.fill(row_B, 0);
        Arrays.fill(row_C, 0);
        Arrays.fill(row_D, 0);
        menu();

    }
    // Menu Method

    public static void menu() {
        System.out.println("***Welcome to the Plane Management application***");
        while (ball) {
            System.out.print("****************************************************");
            System.out.print("\n*                  MENU OPTION                     *");
            System.out.print("\n****************************************************");
            System.out.print("\n\t\t1) Buy a seat\n\t\t2) Cancel Seat\n\t\t3) Find first available seat\n\t\t4) Show seating plan" +
                    "\n\t\t5) Print ticket information and total sales\n\t\t6) Search Ticket\n\t\t0) Quit");
            System.out.print("\n*****************************************************");
            System.out.println("\nPlease select an option:");
            String option = input.nextLine();

            switch (option) {
                case "1":
                    buy_seat();
                    break;
                case "2":
                    cancel_seat();
                    break;
                case "3":
                    find_first_available();
                    break;
                case "4":
                    show_seating_plan();
                    break;
                case "5":
                    print_tickets_info();
                    break;
                case "6":
                    search_ticket();
                    break;
                case "0":
                    ball = false;
                    System.out.print("Thank you for using this system");
                    break;
                default:
                    System.out.println("Please enter a valid number");
            }
        }
    }

    //Task 3 Book Seat
    public static void buy_seat() {
        show_seating_plan();

        System.out.print("\nEnter your name: ");
        String name = input.next();
        System.out.print("Enter your surname: ");
        String surname = input.next();
        System.out.print("Enter your email: ");
        String email = input.next();
        Person customer = new Person(name, surname, email);

        boolean check = true;
        boolean found = false;
        while (check) {
            try {
                String row_number = check_row(found);
                if (row_number != null) {
                    switch (row_number) {
                        case "A" -> buy_ticket_method(row_A, "A", customer, 14);
                        case "B" -> buy_ticket_method(row_B, "B", customer, 12);
                        case "C" -> buy_ticket_method(row_C, "C", customer, 12);
                        case "D" -> buy_ticket_method(row_D, "D", customer, 14);
                    }
                    check = false;
                } else {
                    System.out.println("Row not found. Please enter a valid row.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Row Number, Row Number must be integer");
                input.nextLine();
            }
        }
    }


    //Task 4 Cancel Seat
    public static void cancel_seat() {
        show_seating_plan();
        boolean check = true;
        boolean found = false;
        while (check) {
            try {
                String row_number = check_row(found);
                if (row_number != null) {
                    switch (row_number) {
                        case "A" -> cancel_seat_method(row_A,"A");
                        case "B" -> cancel_seat_method(row_B,"B");
                        case "C" -> cancel_seat_method(row_C, "C");
                        case "D" -> cancel_seat_method(row_D, "D");
                    }
                    check = false;
                } else {
                    System.out.println("Row not found. Please enter a valid row.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Row Number, Row Number must be integer");
                input.nextLine();
            }
        }

    }

    //Task 5 Find First Available
    public static void find_first_available(){
        first_seat("Row A:",row_A);
        first_seat("Row B:",row_B);
        first_seat("Row C:",row_C);
        first_seat("Row D:",row_D);
    }



    // Task 6 Seating Plan
    public static void show_seating_plan() {
        System.out.println("***************Seating Plan***************\n");
        print_seat(row_A);
        System.out.print("\n");
        print_seat(row_B);
        System.out.print("\n");
        print_seat(row_C);
        System.out.print("\n");
        print_seat(row_D);
        System.out.println("\n");
    }

    // Task 10
    public static void print_tickets_info() {
        double totalPrice = 0;

        System.out.println("***************Tickets Information***************\n");

        // Iterate through the ticketInformation array
        for (Ticket ticket : ticketInformation) {
            if (ticket != null) {
                // Print the information of each sold ticket
                System.out.println("Row: " + ticket.getRow());
                System.out.println("Seat: " + ticket.getSeat());
                System.out.println("Price: £" + ticket.getPrice());
                System.out.println("---------------------------------------------");

                // Add the price of the ticket to the total price
                totalPrice += ticket.getPrice();
            }
        }

        // Print the total price of all sold tickets
        System.out.println("Total Price of Tickets Sold: £" + totalPrice);
    }
    //Task 11
    public static void search_ticket() {
        System.out.println("***************Search Ticket***************\n");

        // Prompt the user to input a row letter
        System.out.print("Enter the row letter (A, B, C, or D): ");
        String rowLetter = input.next().toUpperCase();

        // Prompt the user to input a seat number
        System.out.print("Enter the seat number: ");
        int seatNumber = input.nextInt();

        // Check if a ticket has been sold for the specified seat
        boolean ticketFound = false;
        for (Ticket ticket : ticketInformation) {
            if (ticket != null && ticket.getRow().equals(rowLetter) && (ticket.getSeat()) == seatNumber) {
                ticketFound = true;
                // Print Ticket and Person information
                System.out.println("Ticket Information:");
                System.out.println("Row: " + ticket.getRow());
                System.out.println("Seat: " + ticket.getSeat());
                System.out.println("Price: £" + ticket.getPrice());
                System.out.println("Person Information:");
                Person person = ticket.getPerson();
                System.out.println("Name: " + person.getName());
                System.out.println("Surname: " + person.getSurname());
                System.out.println("Email: " + person.getEmail());
                break;
            }
        }

        // If no ticket is found for the specified seat, display a message indicating that the seat is available
        if (!ticketFound) {
            System.out.println("This seat is available.");
        }
    }

    public static void save(String row,int seat,double price,Person person){
        String fileName = row + seat + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat + "\n");
            writer.write("Price: " + price + "\n");
            writer.write("Person Information:\n");
            writer.write("Name: " + person.getName() + "\n");
            writer.write("Surname: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");

            System.out.println("Ticket information saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error occurred while saving ticket information to file: " + e.getMessage());
        }
    }


    //Other Methods

    //Seating Plan Method use in show_seating_plan method
    private static void print_seat(int[] Row_number) {
        for (int i : Row_number) {
            if (i == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
    }

    //Method to use in buy ticket method
    private static void buy_ticket_method(int[] Row, String row_number, Person customer, int length) {
        int seat_number;
        boolean check1 = true;
        while (check1) {
            try {
                System.out.print("Seat number : ");
                seat_number = input.nextInt();

                if (seat_number >= 1 && seat_number <= length) {
                    while (true) {
                        if (Row[seat_number - 1] == 0) {
                            Row[seat_number - 1] = 1;
                            double price = check_price(seat_number);
                            System.out.println(" Your seat is Booked");
                            Ticket ticket = new Ticket(row_number,seat_number,price,customer);
                            int index = findFirstAvailableIndex(ticketInformation);
                            save(row_number,seat_number,price,customer);
                            if (index != -1) {
                                ticketInformation[index] = ticket;
                                System.out.println("Ticket added successfully.");
                            } else {
                                System.out.println("Unable to add ticket. Ticket Information array is full.");
                            }
                            book_another_seat();
                            check1 = false;
                            break;
                        } else {
                            System.out.println("Seat was already booked, Select another Seat ");
                            buy_seat();
                        }
                        break;

                    }
                    input.nextLine();
                } else {
                    System.out.println(" Invalid seat, Enter a seat 1-" + Row.length);
                    buy_seat();
                }
            } catch (Exception e) {
                System.out.println("Invalid Seat Number,Seat number must be an integer");
                buy_seat();
            }

            input.nextLine();
        }
    }

    // Book Another Seat Method Use in Buy Ticket Method
    public static void book_another_seat() {
        while (true) {
            System.out.println("Do you need another ticket (YES,NO):");
            String input_continue = input.next().toUpperCase();
            if (input_continue.equals("YES")) {
                buy_seat();
            } else if (input_continue.equals("NO")) {
                menu();
            } else {
                System.out.println("Please enter yes or no");
            }
        }
    }

    // Method for  use the entered row is valid

    public static String check_row(boolean found) {
        System.out.println("Row Number:");
        String row_number = input.next().toUpperCase();
        for (String row : rows) {
            if (row.equals(row_number)) {
                found = true;
                return row_number;
            }
        }
        return null;
    }

    // Cancel Seat Method use in to cancel seat
    private static void cancel_seat_method(int[] Row, String row_number) {
        while (true) {
            try {
                System.out.print("Seat number : ");
                int seat_number = input.nextInt();

                if (seat_number < Row.length) {
                    if (Row[seat_number - 1] == 1) {
                        Row[seat_number - 1] = 0;
                        System.out.println(" Your seat is Cancelled");
                        removeTicket(row_number, seat_number);
                        break;
                    } else {
                        System.out.println("Seat is not yet book");
                    }
                    break;
                } else {
                    System.out.println("Invalid seat, Enter a seat 1-" + Row.length);

                }
            } catch (Exception e) {
                System.out.println("Invalid Seat Number,Please enter valid seat");
                input.nextLine();
            }
        }
    }
    // Method that use in find_first seat method
    private static void first_seat(String rowName,int [] row){
        System.out.print(rowName);
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 0) {
                System.out.print( (i + 1) + "\n");
                break;
            }
        }
    }
    //Method that use to check the seat number and the price
    private static double check_price(int seatNumber) {
        for (int i = 0; i < priceRange250.length; i++) {
            if (seatNumber == priceRange250[i]) {
                return 250;
            }
        }
        for (int i = 0; i < priceRange150.length; i++) {
            if (seatNumber == priceRange150[i]) {
                return 150;
            }
        }
        for (int i = 0; i < priceRange180.length; i++) {
            if (seatNumber == priceRange180[i]) {
                return 180;
            }
        }
        return -1;
    }
    // Method to find first array index in array
    private static int findFirstAvailableIndex(Ticket[] tickets) {
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] == null) {
                return i; // Found an available index
            }
        }
        return -1; // No available index found (array is full)
    }

    //Method to remove ticket object from the array
    private static void removeTicket(String rowNumber, int seatNumber) {
        boolean ticketFound = false;
        for (int i = 0; i < ticketInformation.length; i++) {
            if (ticketInformation[i] != null &&
                    ticketInformation[i].getRow().equals(rowNumber) &&
                    ticketInformation[i].getSeat() == seatNumber) {
                ticketInformation[i] = null; // Remove the Ticket object
                ticketFound = true;
                break;
            }
        }
        if (ticketFound) {
            System.out.println("Ticket with row number " + rowNumber + " and seat number " + seatNumber + " removed successfully.");
        } else {
            System.out.println("Ticket with row number " + rowNumber + " and seat number " + seatNumber + " not found.");
        }
    }

}
