import com.sun.org.apache.regexp.internal.RE;

import javax.naming.spi.ResolveResult;

/**
 * Created by Harry on 4/23/2017.
 */
public class Reservation {
    static int ReservationCount = 1;

    String CustomerName;
    int ReservationId;
    int Arrival;
    int Days;
    int NumberOfPeople;
    Room ReservedRoom;

    Reservation(String customerName, int arrival, int days, int numberOfPeople) {
        ReservationId = ReservationCount++;
        CustomerName = customerName;
        Arrival = arrival;
        Days = days;
        NumberOfPeople = numberOfPeople;
        ReservedRoom = null;
    }

    void SetRoom(Room reservedRoom) {
        ReservedRoom = reservedRoom;
    }

    @Override
    public String toString(){
        return String.format("%2d | %16s | %2d", ReservationId, CustomerName, ReservedRoom.RoomId);
    }
}
