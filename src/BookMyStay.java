import java.util.*;

/**
 * Book My Stay App - Use Case 9
 * Error Handling & Validation
 */

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    InventoryService() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public boolean isValidRoom(String type) {
        return inventory.containsKey(type);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decrement(String type) throws InvalidBookingException {

        if (!isValidRoom(type)) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }

        if (inventory.get(type) <= 0) {
            throw new InvalidBookingException("No availability for: " + type);
        }

        inventory.put(type, inventory.get(type) - 1);
    }

    public void showInventory() {
        System.out.println("\n--- INVENTORY ---");
        for (String k : inventory.keySet()) {
            System.out.println(k + " -> " + inventory.get(k));
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        InventoryService inventory = new InventoryService();

        String[] requests = {
                "Single Room",
                "Suite Room",
                "Luxury Room" // invalid
        };

        for (String req : requests) {
            try {
                System.out.println("\nProcessing booking: " + req);
                inventory.decrement(req);
                System.out.println("Booking successful: " + req);

            } catch (InvalidBookingException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        inventory.showInventory();
    }
}