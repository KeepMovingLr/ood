package voyageBooking;
import java.util.Date;

public class Order {
    private String orderId;
    private String startLocation;
    private String destination;
    private Date pickupDate;
    private Date expectedDeliveryDate;
    private String status;
    private Voyage voyage; // Link to the Voyage

    public Order(String orderId, String startLocation, String destination, Date pickupDate, Date expectedDeliveryDate) {
        this.orderId = orderId;
        this.startLocation = startLocation;
        this.destination = destination;
        this.pickupDate = pickupDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.status = "Pending";
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    // Getters
    public String getOrderId() { return orderId; }
    public String getStartLocation() { return startLocation; }
    public String getDestination() { return destination; }
    public Date getPickupDate() { return pickupDate; }
    public Date getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public String getStatus() { return status; }
    public Voyage getVoyage() { return voyage; }
}

