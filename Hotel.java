import java.util.ArrayList;

/**
 * Created by Harry on 4/23/2017.
 */
public class Hotel {
    String Name;
    ArrayList<Room> Rooms;
    ArrayList<Reservation> Reservations;

    Hotel(String name) {
        Name = name;
        Rooms = new ArrayList<>();
        Reservations = new ArrayList<>();
    }

    void AddRoom(Room room) {
        Rooms.add(room);
    }

    Room GetRoomFromId(int roomId) {
        for (Room room : Rooms) {
            if (room.RoomId == roomId) {
                return room;
            }
        }
        return null;
    }

    Reservation GetReservationFromId(int reservationId) {
        for (Reservation reservation : Reservations) {
            if (reservation.ReservationId == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    // Useless function (reservation will never be found) but that is what is asked in the exercise
    boolean AddReservationToRoom(int reservationId, int roomId) {
        Room room = GetRoomFromId(roomId);
        Reservation reservation = GetReservationFromId(reservationId);
        if (room == null) {
            System.out.println("Room was not found.");
            return false;
        }
        if (reservation == null) {
            System.out.println("Reservation was not found.");
            return false;
        }
        return room.AddReservation(reservation);
    }

    boolean AddReservationToRoom(Reservation reservation, int roomId) {
        Room room = GetRoomFromId(roomId);
        if (room == null) {
            System.out.println("Room was not found.");
        }
        Reservations.add(reservation);
        return room.AddReservation(reservation);
    }

    // Adds the reservation in a possible room and returns said room (or returns 0 if no such room is available)
    // Also adds the reservation in the reservation list of the hotel
    int InsertReservation(Reservation reservation) {
        for (Room room : Rooms) {
            if (room.AddReservation(reservation)) {
                Reservations.add(reservation);
                System.out.println("Reservation was added to room: " + room.RoomId);
                return room.RoomId;
            }
        }
        System.out.println("There are no available rooms for this reservation.");
        return 0;
    }


    boolean CancelReservation(int reservationId) {
        Reservation reservation = GetReservationFromId(reservationId);
        if (reservation == null) {
            System.out.println("Reservation doesn't exist.");
            return false;
        }
        boolean WasRemoved = reservation.ReservedRoom.RemoveReservation(reservationId);

        if (WasRemoved) {
            System.out.println("Reservation has been canceled.");
            Reservations.remove(reservation);
            return true;
        }

        System.out.println("This reservation cannot be canceled.");
        return false;
    }

    double CalculateIncome() {
        double totalIncome = 0;
        for (Room room : Rooms) {
            totalIncome += room.TotalCost();
        }
        return totalIncome;
    }

    double CalculateIncome(int roomId) {
        Room room = GetRoomFromId(roomId);
        if (room == null) {
            return 0;
        }
        return room.TotalCost();
    }

    void PrintReservationTable() {
        System.out.print("ROOM |\t");
        for (int i = 1; i <= 30; ++i) {
            System.out.print(String.format("%2d ", i));
        }
        System.out.println("\t$ " + CalculateIncome());
        for (Room room : Rooms) {
            System.out.println(room.GetReservationRow());
        }
    }

    void PrintReservations() {
        for (Reservation reservation : Reservations) {
            System.out.println(reservation.toString());
        }
    }

    void PrintRooms() {
        for (Room room : Rooms) {
            System.out.println(room.toString());
        }
    }
}
