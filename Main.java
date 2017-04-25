import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static private Scanner scanner = new Scanner(System.in);

    static private Reservation MakeRandomReservation() {
        int nameCode = ThreadLocalRandom.current().nextInt(1, 1000);
        int arrival = ThreadLocalRandom.current().nextInt(1, 29);
        int days = ThreadLocalRandom.current().nextInt(1, 30 - arrival);
        int numberOfPeople = ThreadLocalRandom.current().nextInt(2, 10);
        return new Reservation("Name " + nameCode, arrival, days, numberOfPeople);
    }

    static private int GetRandomReservationId() {
        // We can get already canceled reservations. But this is needed in case we cancel the first reservation and the second is impossible to add to any room
        return ThreadLocalRandom.current().nextInt(1, Reservation.ReservationCount);
    }

    static private void PrintMenu() {
        System.out.println();
        System.out.println("1. Simulate Random");
        System.out.println("2. Insert Reservation");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. Show Reservations");
        System.out.println("5. Show Rooms");
        System.out.println("6. Show Plan");
        System.out.println("7. Show Income");
        System.out.println("8. Exit");
        System.out.println("Please make a choice:");
    }

    static private void ChoiceRandomSimulate(Hotel hotel) {
        hotel.InsertReservation(MakeRandomReservation());
        if (ThreadLocalRandom.current().nextInt(1, 100) <= 25) {
            hotel.CancelReservation(GetRandomReservationId());
        }
    }

    static private void ChoiceInsertReservation(Hotel hotel) {
        System.out.println("Insert customer name:");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        String name = scanner.nextLine();
        System.out.println("Insert arrival:");
        int arrival = scanner.nextInt();
        System.out.println("Insert days:");
        int days = scanner.nextInt();
        System.out.println("Insert number of people:");
        int numOfPeople = scanner.nextInt();

        System.out.println("Insert room (or 0 for auto):");
        int roomId = scanner.nextInt();

        Reservation reservation = new Reservation(name, arrival, days, numOfPeople);
        if (roomId == 0) {
            hotel.InsertReservation(reservation);
        } else {
            if (hotel.AddReservationToRoom(reservation, roomId)) {
                System.out.println("Reservation added.");
            }
            else {
                System.out.println("Reservation could not be added.");
            }
        }
    }

    static private void ChoiceCancelReservation(Hotel hotel) {
        System.out.println("Insert reservation to cancel:");
        int id = scanner.nextInt();
        hotel.CancelReservation(id);
    }

    static private void ChoiceShowReservations(Hotel hotel) {
        hotel.PrintReservations();
    }

    static private void ChoiceShowRooms(Hotel hotel) {
        hotel.PrintRooms();
    }

    static private void ChoiceShowPlan(Hotel hotel) {
        hotel.PrintReservationTable();
    }

    static private void ChoiceShowIncome(Hotel hotel) {
        System.out.println("Give a room id or 0 for total: ");
        int choice = scanner.nextInt();
        if (choice != 0) {
            System.out.println("$ " + hotel.CalculateIncome(choice));
        } else {
            System.out.println("Total: $ " + hotel.CalculateIncome());
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel("My own foo hotel");

        Room room1 = new Room(4, 15);
        RoomTypeA room2 = new RoomTypeA(4, 10, 5);
        RoomTypeB room3 = new RoomTypeB(3, 1, 25, 5);
        RoomTypeC room4 = new RoomTypeC(5, 8, 3, 5);
        RoomTypeD room5 = new RoomTypeD(5, 11, 0.2);
        RoomTypeE room6 = new RoomTypeE(2, 9);
        RoomTypeA room7 = new RoomTypeA(2, 5, 20);
        RoomTypeB room8 = new RoomTypeB(6, 5, 30, 10);
        RoomTypeC room9 = new RoomTypeC(10, 4, 8, 8);
        Room room10 = new Room(5, 14);

        hotel.AddRoom(room1);
        hotel.AddRoom(room2);
        hotel.AddRoom(room3);
        hotel.AddRoom(room4);
        hotel.AddRoom(room5);
        hotel.AddRoom(room6);
        hotel.AddRoom(room7);
        hotel.AddRoom(room8);
        hotel.AddRoom(room9);
        hotel.AddRoom(room10);

        PrintMenu();
        int choice = scanner.nextInt();

        while (choice != 8) {
            switch (choice) {
                case 1: // Continue loop
                    ChoiceRandomSimulate(hotel);
                    break;
                case 2: // Add Reservation
                    ChoiceInsertReservation(hotel);
                    break;
                case 3:
                    // Cancel Reservation
                    ChoiceCancelReservation(hotel);
                    break;
                case 4:
                    // Show Reservations
                    ChoiceShowReservations(hotel);
                    break;
                case 5:
                    // Show Rooms
                    ChoiceShowRooms(hotel);
                    break;
                case 6:
                    // Show Plan
                    ChoiceShowPlan(hotel);
                    break;
                case 7:
                    // Show Income
                    ChoiceShowIncome(hotel);
                case 8:
                    break;
                default:
                    System.out.println("Incorrect choice.");
            }
            PrintMenu();
            choice = scanner.nextInt();
        }

        hotel.PrintReservationTable();
    }
}
