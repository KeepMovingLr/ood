package voyageBooking;
import java.util.Date;

public class Voyage {
    private String voyageId;
    private String startLocation;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private boolean isBooked;

    public Voyage(String voyageId, String startLocation, String destination, Date departureDate, Date arrivalDate) {
        this.voyageId = voyageId;
        this.startLocation = startLocation;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.isBooked = false;
    }

    public void book() {
        this.isBooked = true;
    }

    public void cancelBooking() {
        this.isBooked = false;
    }

    // Getters
    public String getVoyageId() { return voyageId; }
    public String getStartLocation() { return startLocation; }
    public String getDestination() { return destination; }
    public Date getDepartureDate() { return departureDate; }
    public Date getArrivalDate() { return arrivalDate; }
    public boolean isBooked() { return isBooked; }
}
