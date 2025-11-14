package clone.asiana.asiana_clone.booking.dto;

public class BookingRequestDTO {

    private String tripType;           // "ONEWAY" or "ROUND"
    private String firstJourney;       // 첫 번째 여정 flightNumber
    private String secondJourney;      // 두 번째 여정 flightNumber (왕복일 경우)

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getFirstJourney() {
        return firstJourney;
    }

    public void setFirstJourney(String firstJourney) {
        this.firstJourney = firstJourney;
    }

    public String getSecondJourney() {
        return secondJourney;
    }

    public void setSecondJourney(String secondJourney) {
        this.secondJourney = secondJourney;
    }

    @Override
    public String toString() {
        return "BookingRequestDTO{" +
                "tripType='" + tripType + '\'' +
                ", firstJourney='" + firstJourney + '\'' +
                ", secondJourney='" + secondJourney + '\'' +
                '}';
    }
}
