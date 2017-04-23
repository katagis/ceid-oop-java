import javax.lang.model.element.AnnotationValueVisitor;

/**
 * Created by Harry on 4/23/2017.
 */
public class RoomTypeB extends RoomTypeA {
    double DiscountPerDay;

    RoomTypeB(int maxPeople, double pricePerPerson, double pricePerDay, double discountPerDay) {
        super(maxPeople, pricePerPerson, pricePerDay);
        DiscountPerDay = discountPerDay;
    }

    @Override
    double TotalCost() {
        double discountedCost = super.TotalCost();
        int discountDay = 1;
        Reservation lastReservation = null;

        for (Reservation reservation : Availability) {
            if (reservation != null && reservation == lastReservation) {

                double discount = discountDay*DiscountPerDay;
                if (discount > PricePerDay / 2) {
                    discount = PricePerDay / 2;
                }

                discountedCost -= discount;

                discountDay++;
            }
            else {
                lastReservation = reservation;
                discountDay = 1;
            }
        }
        return discountedCost;
    }

    @Override
    boolean RemoveReservation(int reservationId) {
        return false;
    }
}
