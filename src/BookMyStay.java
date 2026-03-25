import java.io.*;
import java.util.*;

/**
 * Book My Stay App - Use Case 12
 * Data Persistence & System Recovery
 */

class PersistenceService {

    private Map<String, Integer> inventory = new HashMap<>();
    private List<String> bookings = new ArrayList<>();

    private final String FILE_NAME = "bookmystay_data.ser";

    public PersistenceService() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public void bookRoom(String guest) {
        for (String type : inventory.keySet()) {
            if (inventory.get(type) > 0) {
                inventory.put(type, inventory.get(type) - 1);
                bookings.add(guest + " -> " + type);
                System.out.println("BOOKED: " + guest + " -> " + type);
                return;
            }
        }
        System.out.println("NO ROOMS AVAILABLE for " + guest);
    }

    public void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {

            out.writeObject(inventory);
            out.writeObject(bookings);

            System.out.println("\nSTATE SAVED SUCCESSFULLY");
        } catch (Exception e) {
            System.out.println("ERROR SAVING STATE: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {

            inventory = (HashMap<String, Integer>) in.readObject();
            bookings = (ArrayList<String>) in.readObject();

            System.out.println("\nSTATE RESTORED SUCCESSFULLY");

        } catch (Exception e) {
            System.out.println("\nNO PREVIOUS STATE FOUND - STARTING FRESH");
        }
    }

    public void showState() {
        System.out.println("\n--- INVENTORY ---");
        for (String k : inventory.keySet()) {
            System.out.println(k + " -> " + inventory.get(k));
        }

        System.out.println("\n--- BOOKINGS ---");
        for (String b : bookings) {
            System.out.println(b);
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        PersistenceService system = new PersistenceService();

        // LOAD PREVIOUS STATE
        system.loadState();

        // NEW BOOKINGS
        system.bookRoom("Guest A");
        system.bookRoom("Guest B");

        system.showState();

        // SAVE BEFORE EXIT
        system.saveState();
    }
}import java.io.*;
        import java.util.*;

/**
 * Book My Stay App - Use Case 12
 * Data Persistence & System Recovery
 */

class PersistenceService {

    private Map<String, Integer> inventory = new HashMap<>();
    private List<String> bookings = new ArrayList<>();

    private final String FILE_NAME = "bookmystay_data.ser";

    public PersistenceService() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public void bookRoom(String guest) {
        for (String type : inventory.keySet()) {
            if (inventory.get(type) > 0) {
                inventory.put(type, inventory.get(type) - 1);
                bookings.add(guest + " -> " + type);
                System.out.println("BOOKED: " + guest + " -> " + type);
                return;
            }
        }
        System.out.println("NO ROOMS AVAILABLE for " + guest);
    }

    public void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {

            out.writeObject(inventory);
            out.writeObject(bookings);

            System.out.println("\nSTATE SAVED SUCCESSFULLY");
        } catch (Exception e) {
            System.out.println("ERROR SAVING STATE: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadState() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {

            inventory = (HashMap<String, Integer>) in.readObject();
            bookings = (ArrayList<String>) in.readObject();

            System.out.println("\nSTATE RESTORED SUCCESSFULLY");

        } catch (Exception e) {
            System.out.println("\nNO PREVIOUS STATE FOUND - STARTING FRESH");
        }
    }

    public void showState() {
        System.out.println("\n--- INVENTORY ---");
        for (String k : inventory.keySet()) {
            System.out.println(k + " -> " + inventory.get(k));
        }

        System.out.println("\n--- BOOKINGS ---");
        for (String b : bookings) {
            System.out.println(b);
        }
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        PersistenceService system = new PersistenceService();

        // LOAD PREVIOUS STATE
        system.loadState();

        // NEW BOOKINGS
        system.bookRoom("Guest A");
        system.bookRoom("Guest B");

        system.showState();

        // SAVE BEFORE EXIT
        system.saveState();
    }
}