/**
 * Created by Harry on 4/25/2017.
 */
// Half the price is prepaid at the time of reservation
public class RoomTypeE extends Room {
    private double PrepaidAmount;

    RoomTypeE(int maxPeople, double pricePerPerson) {
        super(maxPeople, pricePerPerson);
        PrepaidAmount = 0; // Holds total of money that have been prepaid at reservation time
    }


    @Override
    public boolean AddReservation(Reservation reservation) {
        if (super.AddReservation(reservation)) {
            PrepaidAmount += super.TotalCost() / 2;
            return true;
        }
        return false;
    }

    @Override
    public double TotalCost() {
        return (super.TotalCost() / 2) + PrepaidAmount;
    }
}
