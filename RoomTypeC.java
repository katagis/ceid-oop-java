/**
 * Created by Harry on 4/23/2017.
 */
public class RoomTypeC extends Room {
    private int MinPeople;
    private int MinDays;

    RoomTypeC(int maxPeople, double pricePerPerson, int minPeople, int minDays) {
        super(maxPeople, pricePerPerson);
        MinPeople = minPeople;
        MinDays = minDays;
    }

    @Override
    boolean AddReservation(Reservation reservation) {
        if (reservation.Days < MinDays) {
            return false;
        }
        if (reservation.NumberOfPeople < MinPeople) {
            return false;
        }
        return super.AddReservation(reservation);
    }
}
