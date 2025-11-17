package clone.asiana.asiana_clone.booking.vo;

import java.time.LocalDate;

public class SearchFlightVO {

    private String departureAirport; // 출발공항
    private String arrivalAirport; // 도착공항
    private LocalDate departureDate; // 출발날짜
    private int totalPassengers; // 탑승객합계

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

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(int totalPassengers) {
        this.totalPassengers = totalPassengers;
    }
}
