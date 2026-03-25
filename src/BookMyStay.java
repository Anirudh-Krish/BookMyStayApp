import java.util.*;

// =======================
// ROOM MODEL (UC2)
// =======================
abstract class Room {
    protected String roomType;
    protected int price;

    public Room(String roomType, int price) {
        this.roomType = roomType;
        this.price = price;
    }

    public abstract void displayDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 2000);
    }

    public void displayDetails() {
        System.out.println(roomType + " | Price: " + price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 3500);
    }

    public void displayDetails() {
        System.out.println(roomType + " | Price: " + price);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 6000);
    }

    public void displayDetails() {
        System.out.println(roomType + " | Price: " + price);
    }
}

// =======================
// INVENTORY (UC3)
// =======================
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        System.out.println("\n===== INVENTORY =====");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

// =======================
// UC5: RESERVATION MODEL
// =======================
class Reservation {
    String customerName;
    String roomType;

    public Reservation(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String toString() {
        return customerName + " requested " + roomType;
    }
}

// =======================
// MAIN CLASS
// =======================
public class BookMyStay {

    // UC1
    public static void useCase1() {
        System.out.println("=================================");
        System.out.println("      BOOK MY STAY APP           ");
        System.out.println("=================================");
        System.out.println("System Started Successfully\n");
    }

    // UC2
    public static void useCase2() {
        System.out.println("===== UC2: ROOM TYPES =====");

        Room s = new SingleRoom();
        Room d = new DoubleRoom();
        Room su = new SuiteRoom();

        s.displayDetails();
        d.displayDetails();
        su.displayDetails();
    }

    // UC3
    public static void useCase3(RoomInventory inventory) {
        System.out.println("\n===== UC3: INVENTORY =====");

        inventory.displayInventory();

        System.out.println("\nUpdating Inventory...");
        inventory.updateAvailability("Single Room", 4);
        inventory.updateAvailability("Suite Room", 1);

        inventory.displayInventory();
    }

    // =======================
    // UC5: BOOKING REQUEST QUEUE (FIFO)
    // =======================
    public static void useCase5() {

        System.out.println("\n===== UC5: BOOKING REQUEST QUEUE =====");

        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Incoming requests
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Suite Room"));
        bookingQueue.add(new Reservation("Charlie", "Double Room"));

        // Process in FIFO order
        System.out.println("Processing Requests in Order:");

        while (!bookingQueue.isEmpty()) {
            System.out.println("Processing -> " + bookingQueue.poll());
        }
    }

    // MAIN
    public static void main(String[] args) {

        useCase1();
        useCase2();

        RoomInventory inventory = new RoomInventory();

        useCase3(inventory);

        useCase5(); // 👈 NEW UC5 ADDED
    }
}