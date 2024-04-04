package voyageBooking;

/**
 * There are voyages which contains origin, destination, departureDate, arrivalDate
 * and there are some orders need to be ship, could you please help to design the booking system to find the suitable voyages for orders?
 * Orders have origin and destination,pickupDate,expectedDeliveryDate
 */

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private List<Order> orders = new ArrayList<>();
    private List<Voyage> voyages = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addVoyage(Voyage voyage) {
        voyages.add(voyage);
    }

    public boolean bookOrder(Order order, Voyage voyage) {
        // Check if the order can be booked on the given voyage
        if (canBookOrderOnVoyage(order, voyage)) {
            // Book the voyage and update the order's status and linked voyage
            voyage.book();
            order.updateStatus("Booked");
            order.setVoyage(voyage);
            return true; // Booking successful
        }
        return false; // Booking failed
    }

    public void reschedule() {
        // Reset all voyages to not booked before rescheduling
        voyages.forEach(voyage -> voyage.cancelBooking());

        // List to keep track of orders that have been successfully booked
        List<Order> bookedOrders = new ArrayList<>();

        // Try to book each order
        for (Order order : orders) {
            for (Voyage voyage : voyages) {
                if (canBookOrderOnVoyage(order, voyage)) {
                    voyage.book();
                    order.updateStatus("Booked");
                    order.setVoyage(voyage);
                    bookedOrders.add(order);
                    break; // Move to the next order once booked
                }
            }
        }

        // Check for possible reassignments to accommodate more orders
        for (Order order : orders) {
            if (!bookedOrders.contains(order)) { // Order not yet booked
                for (Voyage voyage : voyages) {
                    // Check if swapping can make space for this order
                    if (canBookOrderOnVoyage(order, voyage) && canSwapToAccommodate(order, voyage, bookedOrders)) {
                        voyage.book(); // Re-book the voyage with the new order
                        order.updateStatus("Booked");
                        order.setVoyage(voyage);
                        bookedOrders.add(order);
                        break; // Move to the next unbooked order
                    }
                }
            }
        }
    }

    private boolean canBookOrderOnVoyage(Order order, Voyage voyage) {
        return !voyage.isBooked() && voyage.getStartLocation().equals(order.getStartLocation()) &&
                voyage.getDestination().equals(order.getDestination()) &&
                !voyage.getDepartureDate().before(order.getPickupDate()) &&
                !voyage.getArrivalDate().after(order.getExpectedDeliveryDate());
    }

    private boolean canSwapToAccommodate(Order newOrder, Voyage voyage, List<Order> bookedOrders) {
        for (Order bookedOrder : bookedOrders) {
            if (bookedOrder.getVoyage().equals(voyage)) {
                for (Voyage altVoyage : voyages) {
                    if (altVoyage != voyage && canBookOrderOnVoyage(bookedOrder, altVoyage)) {
                        bookedOrder.getVoyage().cancelBooking();
                        bookedOrder.updateStatus("Pending");
                        altVoyage.book();
                        bookedOrder.setVoyage(altVoyage);
                        bookedOrder.updateStatus("Booked");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
