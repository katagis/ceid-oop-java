import sun.security.x509.AVA;

import java.rmi.server.RemoteServer;

/**
 * Created by Harry on 4/23/2017.
 */
public class Room {
    static int RoomCount = 1;

    int RoomId;
    int MaxPeople;
    Reservation[] Availability; // Represents the reservations for each day of the month
    double PricePerPerson;

    Room(int maxPeople, double pricePerPerson) {
        MaxPeople = maxPeople;
        PricePerPerson = pricePerPerson;
        RoomId = RoomCount++;
        Availability = new Reservation[30];
    }

    boolean AddReservation(Reservation reservation) {
        if (reservation.NumberOfPeople > MaxPeople) {
            return false;
        }
        int firstDay = reservation.Arrival;
        int lastDay = firstDay + reservation.Days - 1;

        for (int i = firstDay; i <= lastDay; ++i) {
            if (Availability[i] != null) {
                return false;
            }
        }

        for (int i = firstDay; i <= lastDay; ++i) {
            Availability[i] = reservation;
        }


        reservation.SetRoom(this);
        return true;
    }

    // Cost for the total of reservations on this room
    double TotalCost() {
        double cost = 0;

        for (Reservation reservation : Availability) {
            if (reservation != null) {
                cost += reservation.NumberOfPeople * PricePerPerson;
            }
        }

        return cost;
    }

    boolean RemoveReservation(int reservationId) {
        for (Reservation reservation : Availability) {
            if (reservation.ReservationId == reservationId) {
                reservation = null;
            }
        }
        return true;
    }

    // Returns an int percent (0-100 inclusive) of the days available for the room
    int GetFillPercent() {
        int ReservedCount = 0;
        for (Reservation reservation : Availability) {
            if (reservation != null) {
                ReservedCount++;
            }
        }
        return Math.round(100 * ReservedCount / 30);
    }

}

