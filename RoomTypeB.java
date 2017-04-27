/**
 * Created by Harry on 4/23/2017.
 */
public class RoomTypeB extends RoomTypeA {
    private double DiscountPerDay;

    RoomTypeB(int maxPeople, double pricePerPerson, double pricePerDay, double discountPerDay) {
        super(maxPeople, pricePerPerson, pricePerDay);
        DiscountPerDay = discountPerDay;
    }

    @Override
    public double TotalCost() {
        double discountedCost = super.TotalCost();
        int discountDay = 1;
        Reservation lastReservation = null;

        for (Reservation reservation : Reservations) {
            if (reservation != null && reservation == lastReservation) {

                double discount = discountDay * DiscountPerDay;
                if (discount > GetPricePerDay() / 2) {
                    discount = GetPricePerDay() / 2;
                }

                discountedCost -= discount;

                discountDay++;
            } else {
                lastReservation = reservation;
                discountDay = 1;
            }
        }
        return discountedCost;
    }

    @Override
    public boolean RemoveReservation(int reservationId) {
        return false;
    }
}
