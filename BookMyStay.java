import java.util.HashMap;

/**
 * Book My Stay App
 * Combined Use Cases 1, 2, 3
 */

// =======================
// UC2: ROOM MODEL
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
// UC3: INVENTORY SYSTEM
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
        System.out.println("\n===== ROOM INVENTORY =====");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

// =======================
// MAIN CLASS (UC1 ENTRY POINT)
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
    public static void useCase3() {
        System.out.println("\n===== UC3: INVENTORY =====");

        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();

        System.out.println("\nUpdating Inventory...");
        inventory.updateAvailability("Single Room", 4);
        inventory.updateAvailability("Suite Room", 1);

        inventory.displayInventory();
    }

    public static void main(String[] args) {
        useCase1();
        useCase2();
        useCase3();
    }
}