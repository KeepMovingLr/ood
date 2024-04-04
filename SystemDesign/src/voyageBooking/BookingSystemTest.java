package voyageBooking;
import java.text.SimpleDateFormat;

public class BookingSystemTest {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BookingSystem bookingSystem = new BookingSystem();

        Order orderA = new Order("A", "New York", "London", sdf.parse("2023-04-10"), sdf.parse("2023-04-20"));
        Order orderB = new Order("B", "New York", "London", sdf.parse("2023-04-15"), sdf.parse("2023-04-25"));

        Voyage voyageA = new Voyage("V1", "New York", "London", sdf.parse("2023-04-12"), sdf.parse("2023-04-18"));
        Voyage voyageB = new Voyage("V2", "New York", "London", sdf.parse("2023-04-16"), sdf.parse("2023-04-22"));

        bookingSystem.addOrder(orderA);
        bookingSystem.addOrder(orderB);
        bookingSystem.addVoyage(voyageA);
        bookingSystem.addVoyage(voyageB);

        bookingSystem.reschedule();

        // Output the results
        for (Order order : bookingSystem.getOrders()) {
            System.out.println("Order " + order.getOrderId() + " status: " + order.getStatus() +
                    ", shipped by Voyage: " + (order.getVoyage() != null ? order.getVoyage().getVoyageId() : "None"));
        }
    }
}
