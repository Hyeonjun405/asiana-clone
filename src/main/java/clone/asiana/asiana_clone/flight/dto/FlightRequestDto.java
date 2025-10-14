package clone.asiana.asiana_clone.flight.dto;

import java.time.LocalDate;

public class FlightRequestDto {


    private String from;
    private String to;
    private LocalDate departureDate;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "FlightRequestDto{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", departureDate=" + departureDate +
                '}';
    }
}
