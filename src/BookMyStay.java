import java.util.*;

/**
 * Book My Stay App - Use Case 7: Add-On Service Selection
 */

class AddOnService {
    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String toString() {
        return name + " (₹" + cost + ")";
    }
}

class AddOnServiceManager {

    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public List<AddOnService> getServices(String reservationId) {
        return serviceMap.getOrDefault(reservationId, new ArrayList<>());
    }

    public double calculateTotalCost(String reservationId) {
        double total = 0;

        for (AddOnService s : getServices(reservationId)) {
            total += s.cost;
        }

        return total;
    }

    public void showServices(String reservationId) {
        System.out.println("\n=== Add-On Services ===");
        System.out.println("Reservation ID: " + reservationId);

        for (AddOnService s : getServices(reservationId)) {
            System.out.println("- " + s);
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        // Sample reservation
        String reservationId = "BOOK-101";

        // Add-on services
        manager.addService(reservationId, new AddOnService("Breakfast", 300));
        manager.addService(reservationId, new AddOnService("Spa", 1200));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 500));

        // Display services
        manager.showServices(reservationId);
    }
}