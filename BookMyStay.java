/**
 * Book My Stay App
 * Combined Use Cases Implementation
 * File: BookMyStay.java
 */

// =======================
// ABSTRACT ROOM (UC2)
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

// =======================
// ROOM TYPES (UC2)
// =======================
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
// MAIN APPLICATION
// =======================
public class BookMyStay {

    // =======================
    // USE CASE 1
    // =======================
    public static void useCase1() {
        System.out.println("=================================");
        System.out.println("      Book My Stay System        ");
        System.out.println("=================================");
        System.out.println("Application Started Successfully\n");
    }

    // =======================
    // USE CASE 2
    // =======================
    public static void useCase2() {

        System.out.println("===== USE CASE 2: ROOM TYPES =====");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        single.displayDetails();
        System.out.println("Available: " + singleAvailable);

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);

        System.out.println();
    }

    // =======================
    // MAIN METHOD
    // =======================
    public static void main(String[] args) {

        useCase1();  // UC1
        useCase2();  // UC2
    }
}