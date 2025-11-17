package clone.asiana.asiana_clone.booking.service;

import clone.asiana.asiana_clone.booking.dto.BookingSearchResultDTO;
import clone.asiana.asiana_clone.booking.vo.FlightListVO;
import clone.asiana.asiana_clone.booking.vo.PassengerInfoVO;
import clone.asiana.asiana_clone.booking.vo.SearchFlightVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingFacadeService {

    @Autowired
    FlightSearchService flightSearchService;

    @Autowired
    PricingService pricingService;

    /**
     *
     * @param SearchFlight - 탑승 희망하는 항공편
     * @param PassengerInfo - 탑승객정보
     * @return 항공편명 / 출발지 / 도착지 / 출발시간 / 도착시간 / 최종운임
     */
    public List<BookingSearchResultDTO> searchFlights(SearchFlightVO SearchFlight, PassengerInfoVO PassengerInfo) {

        //파라미터(SearchDTO전체) -> 예약 가능한 항공편 조회(가능한 항공편 리스트)
        List<FlightListVO> flightList = flightSearchService.findAvailableFlights(SearchFlight);


        //항공편 리스트 -> 가격 책정 및 조합
        List<BookingSearchResultDTO> flightListData = pricingService.calculate(flightList, PassengerInfo);


        return flightListData;

    }
}
