package clone.asiana.asiana_clone.booking.dto;

import clone.asiana.asiana_clone.booking.vo.PassengerInfoVO;
import clone.asiana.asiana_clone.booking.vo.SearchFlightVO;

import java.time.LocalDate;
import java.util.List;

public class BookingSearchDTO {

    private String tripType; //여행타입

    String departureAirport; // 출발공항
    String arrivalAirport; // 도착공항
    LocalDate departureDate; // 출발시간
    LocalDate returnDate; // 도착시간

    int adult; //성인
    int child; //아동
    int infant; //유아

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

    public SearchFlightVO toFirstFlightVO(){

        SearchFlightVO returnVO = new SearchFlightVO();
        returnVO.setDepartureAirport(this.departureAirport); //출발
        returnVO.setArrivalAirport(this.arrivalAirport); //도착
        returnVO.setDepartureDate(this.departureDate);
        returnVO.setTotalPassengers(this.adult + this.child + this.infant);
        return returnVO;
    }

    public SearchFlightVO toSecondFlightVO(){

        SearchFlightVO returnVO = new SearchFlightVO();
        returnVO.setDepartureAirport(this.arrivalAirport); //출발
        returnVO.setArrivalAirport(this.departureAirport); //도착
        returnVO.setDepartureDate(this.returnDate);
        returnVO.setTotalPassengers(this.adult + this.child + this.infant);
        return returnVO;
    }

    public PassengerInfoVO toPassengerInfoVO(){

        PassengerInfoVO returnVO = new PassengerInfoVO();
        returnVO.setAdult(this.adult);
        returnVO.setChild(this.child);
        returnVO.setInfant(this.infant);

        return returnVO;

    }


}
