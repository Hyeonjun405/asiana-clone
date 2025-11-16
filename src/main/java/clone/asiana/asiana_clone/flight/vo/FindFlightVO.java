package clone.asiana.asiana_clone.flight.vo;

public class FindFlightVO {

    private String departureAirport; // 출발 공항
    private String arrivalAirport; // 도착 공항
    private String departureTime; // 출발시간

    public FindFlightVO(String departureAirport, String arrivalAirport, String departureTime) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
