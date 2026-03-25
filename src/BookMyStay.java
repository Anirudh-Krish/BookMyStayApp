import java.util.*;

/**
 * Book My Stay App - Use Case 10
 * Booking Cancellation & Inventory Rollback
 */

class CancellationService {

    private Map<String, String> reservationMap = new HashMap<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);

        // sample bookings
        reservationMap.put("R101", "Single Room");
        reservationMap.put("R102", "Double Room");
    }

    public void cancelBooking(String reservationId) {

        if (!reservationMap.containsKey(reservationId)) {
            System.out.println("ERROR: Reservation does not exist -> " + reservationId);
            return;
        }

        String roomType = reservationMap.get(reservationId);

        // rollback tracking (LIFO)
        rollbackStack.push(roomType);

        // restore inventory
        inventory.put(roomType, inventory.get(roomType) + 1);

        // remove reservation
        reservationMap.remove(reservationId);

        System.out.println("Cancellation successful: " + reservationId +
                " | Room released: " + roomType);
    }

    public void showState() {

        System.out.println("\n--- CURRENT SYSTEM STATE ---");

        System.out.println("\nReservations:");
        for (String id : reservationMap.keySet()) {
            System.out.println(id + " -> " + reservationMap.get(id));
        }

        System.out.println("\nInventory:");
        for (String k : inventory.keySet()) {
            System.out.println(k + " -> " + inventory.get(k));
        }

        System.out.println("\nRollback Stack (released rooms): " + rollbackStack);
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        CancellationService service = new CancellationService();

        // cancellations
        service.cancelBooking("R101");
        service.cancelBooking("R999"); // invalid case

        service.showState();
    }
}