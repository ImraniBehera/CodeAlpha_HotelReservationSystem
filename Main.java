import java.util.*;

class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean available;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + "), Price: $" + price + ", Available: " + available;
    }
}

class Booking {
    private String guestName;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private double payment;

    public Booking(String guestName, Room room, String checkInDate, String checkOutDate, double payment) {
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Booking for " + guestName + ": Room " + room.getRoomNumber() + " (" + room.getType() + ") from " + checkInDate + " to " + checkOutDate + ", Payment: $" + payment;
    }
}

class HotelReservation {
    private List<Room> rooms;
    private List<Booking> bookings;

    public HotelReservation() {
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();

        rooms.add(new Room(101, "Standard", 100));
        rooms.add(new Room(102, "Standard", 100));
        rooms.add(new Room(201, "Deluxe", 150));
        rooms.add(new Room(202, "Deluxe", 150));
        rooms.add(new Room(301, "Suite", 250));
        rooms.add(new Room(302, "Suite", 250));
    }

    public List<Room> searchAvailableRooms(String type) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getType().equalsIgnoreCase(type)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void makeReservation(String guestName, int roomNumber, String checkInDate, String checkOutDate, double payment) {
        Room roomToBook = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook != null) {
            roomToBook.setAvailable(false);
            Booking booking = new Booking(guestName, roomToBook, checkInDate, checkOutDate, payment);
            bookings.add(booking);
            System.out.println("Booking confirmed: " + booking);
        } else {
            System.out.println("Room is either unavailable or doesn't exist.");
        }
    }

    public void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelReservation hotel = new HotelReservation();

        while (true) {
            System.out.println("\nWelcome to the Hotel Reservation System");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View All Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter room type (Standard/Deluxe/Suite): ");
                    String type = scanner.nextLine();
                    List<Room> availableRooms = hotel.searchAvailableRooms(type);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No rooms available for the selected type.");
                    } else {
                        for (Room room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter check-in date (dd/mm/yyyy): ");
                    String checkIn = scanner.nextLine();
                    System.out.print("Enter check-out date (dd/mm/yyyy): ");
                    String checkOut = scanner.nextLine();
                    System.out.print("Enter payment amount: ");
                    double payment = scanner.nextDouble();

                    hotel.makeReservation(name, roomNumber, checkIn, checkOut, payment);
                    break;

                case 3:
                    hotel.showBookings();
                    break;

                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}