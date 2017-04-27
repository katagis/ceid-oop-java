/**
 * Created by Harry on 4/23/2017.
 */
public class Room {
    private static int RoomCount = 1;

    private int Id;
    private int MaxPeople;
    private double PricePerPerson;

    Reservation[] Reservations; // Represents the Reservations for each day of the month


    Room(int maxPeople, double pricePerPerson) {
        MaxPeople = maxPeople;
        PricePerPerson = pricePerPerson;
        Id = RoomCount++;
        Reservations = new Reservation[30];
    }

    public static int GetTotalRooms() {
        return RoomCount;
    }

    public int GetId() {
        return Id;
    }

    public boolean AddReservation(Reservation reservation) {
        if (reservation.GetNumberOfPeople() > MaxPeople) {
            return false;
        }
        int firstDay = reservation.GetArrival();
        int lastDay = firstDay + reservation.GetDays() - 1;

        for (int i = firstDay; i <= lastDay; ++i) {
            if (Reservations[i] != null) {
                return false;
            }
        }

        for (int i = firstDay; i <= lastDay; ++i) {
            Reservations[i] = reservation;
        }


        reservation.SetRoom(this);
        return true;
    }

    // Cost for the total of Reservations on this room
    public double TotalCost() {
        double cost = 0;

        for (Reservation reservation : Reservations) {
            if (reservation != null) {
                cost += reservation.GetNumberOfPeople() * PricePerPerson;
            }
        }

        return cost;
    }

    public boolean RemoveReservation(int reservationId) {
        for (int i = 0; i < 30; ++i) {
            if (Reservations[i] != null && Reservations[i].GetId() == reservationId) {
                Reservations[i] = null;
            }
        }
        return true;
    }

    // Returns an int percent (0-100 inclusive) of the days available for the room
    public int GetFillPercent() {
        int ReservedCount = 0;
        for (Reservation reservation : Reservations) {
            if (reservation != null) {
                ReservedCount++;
            }
        }
        return Math.round(100 * ReservedCount / 30);
    }

    public String GetReservationRow() {
        String row = String.format(" %02d  |\t", Id);
        for (Reservation reservation : Reservations) {
            if (reservation != null) {
                row += "** ";
            } else {
                row += "__ ";
            }
        }
        row += "\t$ " + TotalCost();
        return row;
    }

    public String toString() {
        return String.format("%2d | %3d%% | %f", Id, GetFillPercent(), TotalCost());
    }

}

