import java.util.*;

/**
 * Book My Stay App - Use Case 8: Booking History & Reporting
 */

class Reservation {
    String reservationId;
    String roomType;
    double price;

    Reservation(String reservationId, String roomType, double price) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.price = price;
    }

    public String toString() {
        return reservationId + " | " + roomType + " | ₹" + price;
    }
}

class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {

    public void generateReport(List<Reservation> history) {

        System.out.println("\n=== BOOKING HISTORY REPORT ===");

        double totalRevenue = 0;

        for (Reservation r : history) {
            System.out.println(r);
            totalRevenue += r.price;
        }

        System.out.println("----------------------------");
        System.out.println("Total Bookings: " + history.size());
        System.out.println("Total Revenue: ₹" + totalRevenue);
    }
}

public class BookMyStay {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Sample confirmed bookings
        history.addReservation(new Reservation("B001", "Single Room", 1000));
        history.addReservation(new Reservation("B002", "Double Room", 1800));
        history.addReservation(new Reservation("B003", "Suite Room", 3000));

        // Generate report
        reportService.generateReport(history.getHistory());
    }
}