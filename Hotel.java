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

    boolean AddReservationToRoom(int reservationId, int roomId) {
        return GetRoomFromId(roomId).AddReservation(GetReservationFromId(reservationId));
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
}
