import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String type;
    double price;
    boolean isAvailable;

    Room(int roomNumber, String type, double price, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Type: " + type + ", Price: $" + price + ", Available: " + isAvailable;
    }
}

class Booking {
    int roomNumber;
    String customerName;
    double payment;

    Booking(int roomNumber, String customerName, double payment) {
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Customer: " + customerName + ", Payment: $" + payment;
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        boolean exit = false;

        System.out.println("Welcome to the Hotel Reservation System!");

        while (!exit) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 50.0, true));
        rooms.add(new Room(102, "Double", 80.0, true));
        rooms.add(new Room(103, "Suite", 150.0, true));
        rooms.add(new Room(104, "Single", 50.0, false));
        rooms.add(new Room(105, "Double", 80.0, true));
    }

    private static void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Search for Available Rooms");
        System.out.println("2. Make a Reservation");
        System.out.println("3. View Booking Details");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number between 1 and 4.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    private static void searchRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation() {
        System.out.println("\nEnter the room number you want to book:");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        Room roomToBook = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook == null || !roomToBook.isAvailable) {
            System.out.println("Room not available or does not exist.");
            return;
        }

        System.out.println("Enter your name:");
        String customerName = scanner.nextLine();

        System.out.println("Enter payment amount (Room Price: $" + roomToBook.price + "):");
        double payment = scanner.nextDouble();

        if (payment < roomToBook.price) {
            System.out.println("Insufficient payment. Reservation failed.");
            return;
        }

        roomToBook.isAvailable = false;
        bookings.add(new Booking(roomNumber, customerName, payment));
        System.out.println("Reservation successful! Thank you, " + customerName + ".");
    }

    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings have been made yet.");
        } else {
            System.out.println("\nBooking Details:");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}
