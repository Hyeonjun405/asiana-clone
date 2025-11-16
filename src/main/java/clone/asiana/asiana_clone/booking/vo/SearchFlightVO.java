package clone.asiana.asiana_clone.booking.vo;

import clone.asiana.asiana_clone.booking.dto.BookingSearchDTO;

import java.time.LocalDate;

public class SearchFlightVO {


    String departureAirport; // 출발
    String arrivalAirport;
    LocalDate searchDate;

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

    public LocalDate getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDate searchDate) {
        this.searchDate = searchDate;
    }


    public static SearchFlightVO fromOutbound(BookingSearchDTO dto) {
        SearchFlightVO vo = new SearchFlightVO();
        vo.departureAirport = dto.getDepartureAirport();
        vo.arrivalAirport = dto.getArrivalAirport();
        vo.searchDate = dto.getDepartureDate();
        return vo;
    }

    public static SearchFlightVO fromReturn(BookingSearchDTO dto) {
        SearchFlightVO vo = new SearchFlightVO();
        vo.departureAirport = dto.getDepartureAirport();
        vo.arrivalAirport = dto.getArrivalAirport();
        vo.searchDate = dto.getDepartureDate();
        return vo;
    }

}
