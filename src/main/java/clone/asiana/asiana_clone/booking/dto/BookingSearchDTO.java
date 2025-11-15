package clone.asiana.asiana_clone.booking.dto;

import java.time.LocalDate;
import java.util.List;

public class BookingSearchDTO {

    private String tripType;

    String departureAirport;
    String arrivalAirport;
    LocalDate departureDate;
    LocalDate returnDate;

    int adult;
    int child;
    int infant;

    private List<String> seatClass;

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getInfant() {
        return infant;
    }

    public void setInfant(int infant) {
        this.infant = infant;
    }

    public List<String> getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(List<String> seatClass) {
        this.seatClass = seatClass;
    }

    @Override
    public String toString() {
        return "BookingSearchDTO{" +
                "departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", adult=" + adult +
                ", child=" + child +
                ", infant=" + infant +
                ", seatClass='" + seatClass + '\'' +
                '}';
    }
}
