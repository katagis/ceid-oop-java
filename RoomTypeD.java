/**
 * Created by Harry on 4/25/2017.
 */

public class RoomTypeD extends Room {
    private double PercentDiscount;

    RoomTypeD(int maxPeople, double pricePerPerson, double percentDiscount) {
        super(maxPeople, pricePerPerson);
        PercentDiscount = percentDiscount;
    }

    @Override
    public double TotalCost() {
        return super.TotalCost() * (1 - PercentDiscount);
    }
}
