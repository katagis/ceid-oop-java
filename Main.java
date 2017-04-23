public class Main {

    public static void main(String[] args) {
        Reservation reservation1 = new Reservation("Customer", 10, 18, 3);
        Reservation reservation2 = new Reservation("Customer 2", 3, 11, 2);

        Room room1 = new Room(4, 10);

        room1.AddReservation(reservation1);
        room1.AddReservation(reservation2);

        System.out.println(room1.GetFillPercent());
    }
}
