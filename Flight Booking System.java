import java.io.*;
import java.util.*;

class Flight implements Serializable {
    String flightNo, destination;
    int seats;

    Flight(String flightNo, String destination, int seats) {
        this.flightNo = flightNo;
        this.destination = destination;
        this.seats = seats;
    }

    public String toString() {
        return flightNo + " to " + destination + " - Seats: " + seats;
    }
}

public class FlightBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Flight> flights = new HashMap<>();
        File file = new File("flights.dat");

        while (true) {
            System.out.println("1. Add Flight\n2. View Flights\n3. Save & Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Flight No: ");
                String no = sc.nextLine();
                System.out.print("Destination: ");
                String dest = sc.nextLine();
                System.out.print("Seats: ");
                int seats = sc.nextInt();
                sc.nextLine();
                flights.put(no, new Flight(no, dest, seats));
            } else if (choice == 2) {
                flights.values().forEach(System.out::println);
            } else {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(flights);
                } catch (IOException e) {
                    System.out.println("Save failed.");
                }
                break;
            }
        }
    }
}
