package clone.asiana.asiana_clone.booking.service;

import clone.asiana.asiana_clone.booking.vo.FlightListVO;
import clone.asiana.asiana_clone.booking.vo.SearchFlightVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightSearchService {

    /**
     * 조회 요청한 항공편 리스트 조회
     * @param param - 출발지 / 도착지 / 출발일 / 총인원
     * @return - 출발지 / 도착지 / 출발일 / 기준가
     *
     */
    public List<FlightListVO> findAvailableFlights(SearchFlightVO param){
        // 1. 항공편 조회
        // 2. 잔여 좌석 조회

        return new ArrayList<>();
    }
}
