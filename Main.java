public class Main {

    public static void main(String[] args) {
        Hotel myHotel = new Hotel("My own foo hotel");

        myHotel.AddRoom(new Room(4,10));
        myHotel.AddRoom(new RoomTypeB(3,5,100,20));
        myHotel.AddRoom(new Room(5,10));

        Reservation reservation1 = new Reservation("Customer", 10, 18, 3);
        Reservation reservation2 = new Reservation("Customer 2", 3, 11, 2);

        myHotel.InsertReservation(reservation1);
        myHotel.InsertReservation(reservation2);
        myHotel.InsertReservation(new Reservation("Customer 3", 1, 3, 4));
        myHotel.InsertReservation(new Reservation("Customer 4", 9, 8, 4));

        myHotel.InsertReservation(new Reservation("Customer 5 Many", 1, 25, 5));

        myHotel.CancelReservation(4);
        myHotel.InsertReservation(new Reservation("Customer 6", 9, 5, 5));


        myHotel.PrintReservationTable();
    }
}
