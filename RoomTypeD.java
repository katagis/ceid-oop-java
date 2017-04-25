/**
 * Created by Harry on 4/25/2017.
 */

// Half price for children
public class RoomTypeD extends Room
{
    int NumOfChildren;

    RoomTypeD(int maxPeople, double pricePerPerson, int numOfChildren) {
        super(maxPeople, pricePerPerson);
        NumOfChildren = numOfChildren;
    }

    @Override
    double TotalCost() {
        return super.TotalCost() - (NumOfChildren * PricePerPerson/2);
    }
}
