import java.util.HashMap;

/**
 * Use Case 4: Room Search & Availability Check
 * Goal: Display available rooms without modifying system state
 */

abstract class Room {
    String roomType;
    double price;

    Room(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public abstract void showDetails();
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1000);
    }

    public void showDetails() {
        System.out.println(roomType + " | Price: " + price);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 1800);
    }

    public void showDetails() {
        System.out.println(roomType + " | Price: " + price);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3000);
    }

    public void showDetails() {
        System.out.println(roomType + " | Price: " + price);
    }
}

class RoomInventory {
    private HashMap<String, Integer> availability = new HashMap<>();

    RoomInventory() {
        availability.put("Single Room", 3);
        availability.put("Double Room", 2);
        availability.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public void displayInventory() {
        System.out.println("\n--- Room Availability ---");
        for (String type : availability.keySet()) {
            System.out.println(type + " -> " + availability.get(type));
        }
    }
}

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        System.out.println("=== AVAILABLE ROOMS ===\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {
                room.showDetails();
                System.out.println("Available Count: " + available);
                System.out.println("----------------------");
            }
        }

        inventory.displayInventory();
    }
}