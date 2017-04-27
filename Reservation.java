/**
 * Created by Harry on 4/23/2017.
 */
public class Reservation {
    private static int ReservationCount = 1;

    private String CustomerName;
    private int Id;
    private int Arrival;
    private int Days;
    private int NumberOfPeople;
    private Room ReservedRoom;

    Reservation(String customerName, int arrival, int days, int numberOfPeople) {
        Id = ReservationCount++;
        CustomerName = customerName;
        Arrival = arrival;
        Days = days;
        NumberOfPeople = numberOfPeople;
        ReservedRoom = null;
    }

    public static int GetTotalReservations() {
        return ReservationCount;
    }

    public int GetId() {
        return Id;
    }

    public int GetArrival() {
        return Arrival;
    }

    public int GetDays() {
        return Days;
    }

    public int GetNumberOfPeople() {
        return NumberOfPeople;
    }

    public void SetRoom(Room reservedRoom) {
        ReservedRoom = reservedRoom;
    }

    public Room GetRoom() {
        return ReservedRoom;
    }

    @Override
    public String toString() {
        return String.format("%2d | %16s | %2d", Id, CustomerName, ReservedRoom.GetId());
    }
}
