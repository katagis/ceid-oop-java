import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Harry on 4/23/2017.
 */
public class RoomTypeA extends Room {
    double PricePerDay;

    RoomTypeA(int maxPeople, double pricePerPerson, double pricePerDay) {
        super(maxPeople, pricePerPerson);
        PricePerDay = pricePerDay;
    }

    @Override
    double TotalCost() {
        double cost = super.TotalCost();

        for (Reservation reservation : Availability) {
            if ( reservation != null) {
                cost += PricePerDay;
            }
        }
        return cost;
    }
}
