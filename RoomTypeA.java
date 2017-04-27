/**
 * Created by Harry on 4/23/2017.
 */
public class RoomTypeA extends Room {
    private double PricePerDay;

    RoomTypeA(int maxPeople, double pricePerPerson, double pricePerDay) {
        super(maxPeople, pricePerPerson);
        PricePerDay = pricePerDay;
    }

    public double GetPricePerDay() {
        return PricePerDay;
    }

    @Override
    public double TotalCost() {
        double cost = super.TotalCost();

        for (Reservation reservation : Reservations) {
            if (reservation != null) {
                cost += PricePerDay;
            }
        }
        return cost;
    }
}
