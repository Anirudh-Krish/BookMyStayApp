import java.util.*;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 * Goal: Assign unique room IDs and prevent double booking using Set + HashMap
 */

class BookingRequest {
    String roomType;

    BookingRequest(String roomType) {
        this.roomType = roomType;
    }
}

class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    InventoryService() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    public void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    public void showInventory() {
        System.out.println("\n--- Inventory ---");
        for (String key : inventory.keySet()) {
            System.out.println(key + " -> " + inventory.get(key));
        }
    }
}

class BookingService {
    private Queue<BookingRequest> queue = new LinkedList<>();
    private Set<String> allocatedRooms = new HashSet<>();
    private Map<String, Set<String>> allocationMap = new HashMap<>();
    private InventoryService inventory;

    BookingService(InventoryService inventory) {
        this.inventory = inventory;
    }

    public void addRequest(BookingRequest request) {
        queue.add(request);
    }

    public void processBookings() {

        while (!queue.isEmpty()) {

            BookingRequest req = queue.poll();

            if (inventory.isAvailable(req.roomType)) {

                String roomId = generateRoomId(req.roomType);

                allocatedRooms.add(roomId);

                allocationMap.putIfAbsent(req.roomType, new HashSet<>());
                allocationMap.get(req.roomType).add(roomId);

                inventory.decrement(req.roomType);

                System.out.println("Booking Confirmed: " + req.roomType + " -> Room ID: " + roomId);

            } else {
                System.out.println("Booking Failed (No Availability): " + req.roomType);
            }
        }
    }

    private String generateRoomId(String type) {
        return type.substring(0, 2).toUpperCase() + "-" + (allocatedRooms.size() + 100);
    }

    public void showAllocations() {
        System.out.println("\n--- Allocations ---");
        for (String type : allocationMap.keySet()) {
            System.out.println(type + " -> " + allocationMap.get(type));
        }
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        InventoryService inventory = new InventoryService();
        BookingService service = new BookingService(inventory);

        service.addRequest(new BookingRequest("Single Room"));
        service.addRequest(new BookingRequest("Double Room"));
        service.addRequest(new BookingRequest("Suite Room"));
        service.addRequest(new BookingRequest("Single Room"));

        service.processBookings();

        service.showAllocations();

        inventory.showInventory();
    }
}