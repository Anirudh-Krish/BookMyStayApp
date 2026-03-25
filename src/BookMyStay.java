import java.util.*;
import java.util.concurrent.*;

/**
 * Book My Stay App - Use Case 11
 * Concurrent Booking Simulation (Thread Safety)
 */

class SharedInventory {

    private final Map<String, Integer> inventory = new HashMap<>();

    public SharedInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public synchronized boolean bookRoom(String type, String guestName) {

        if (!inventory.containsKey(type)) {
            System.out.println(guestName + " -> INVALID ROOM TYPE");
            return false;
        }

        int available = inventory.get(type);

        if (available <= 0) {
            System.out.println(guestName + " -> NO ROOMS AVAILABLE: " + type);
            return false;
        }

        inventory.put(type, available - 1);

        System.out.println(guestName + " SUCCESSFULLY BOOKED: " + type +
                " | Remaining: " + (available - 1));

        return true;
    }

    public void showInventory() {
        System.out.println("\n--- FINAL INVENTORY ---");
        for (String k : inventory.keySet()) {
            System.out.println(k + " -> " + inventory.get(k));
        }
    }
}

class BookingTask implements Runnable {

    private SharedInventory inventory;
    private String roomType;
    private String guestName;

    BookingTask(SharedInventory inventory, String roomType, String guestName) {
        this.inventory = inventory;
        this.roomType = roomType;
        this.guestName = guestName;
    }

    public void run() {
        inventory.bookRoom(roomType, guestName);
    }
}

public class BookMyStay {

    public static void main(String[] args) throws Exception {

        SharedInventory inventory = new SharedInventory();

        Thread t1 = new Thread(new BookingTask(inventory, "Single Room", "Guest A"));
        Thread t2 = new Thread(new BookingTask(inventory, "Single Room", "Guest B"));
        Thread t3 = new Thread(new BookingTask(inventory, "Double Room", "Guest C"));
        Thread t4 = new Thread(new BookingTask(inventory, "Suite Room", "Guest D"));
        Thread t5 = new Thread(new BookingTask(inventory, "Single Room", "Guest E"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        inventory.showInventory();
    }
}