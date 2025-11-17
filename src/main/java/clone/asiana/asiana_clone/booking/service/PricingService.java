package clone.asiana.asiana_clone.booking.service;

import clone.asiana.asiana_clone.booking.dto.BookingSearchResultDTO;
import clone.asiana.asiana_clone.booking.vo.FlightListVO;
import clone.asiana.asiana_clone.booking.vo.PassengerInfoVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PricingService {

    /**
     *
     * @param list - 출발지 / 도착지 / 출발일 / 기준가
     * @param PassengerInfo - 항공편명 / 출발지 / 도착지 / 출발시간 / 도착시간 / 최종운임
     * @return
     */
    public List<BookingSearchResultDTO> calculate(List<FlightListVO> list, PassengerInfoVO PassengerInfo){

        //금액 계산
        return null;
    }

}
