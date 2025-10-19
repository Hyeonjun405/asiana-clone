package clone.asiana.asiana_clone.flight.service;


import clone.asiana.asiana_clone.flight.vo.FindFlight;
import clone.asiana.asiana_clone.flight.vo.FlightStatus;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class FlightService {

    @Autowired
    private SqlSessionTemplate sqlSession;


    public List<String> findAllDepartureAirports(){

        return Arrays.asList(
                "ICN", "GMP", "PUS", "CJU", "HND"
        );
    };

    public List<String> findAllArrivalAirports(){

        return Arrays.asList(
                "ICN", "GMP", "PUS", "CJU", "HND"
        );
    };

    public List<FlightStatus> findFlights(FindFlight findFlight){



//        flights.add(new FlightStatus("KE123", "ICN", "HND", "2025-11-01 08:00", "2025-11-01 10:00", "Scheduled"));
//        flights.add(new FlightStatus("OZ456", "ICN", "NRT", "2025-11-01 09:30", "2025-11-01 11:45", "Delayed"));
//        flights.add(new FlightStatus("JL789", "ICN", "FUK", "2025-11-01 07:15", "2025-11-01 09:05", "Cancelled"));
//        flights.add(new FlightStatus("KE321", "ICN", "CTS", "2025-11-01 12:00", "2025-11-01 14:30", "Scheduled"));
//        flights.add(new FlightStatus("OZ654", "ICN", "KIX", "2025-11-01 15:20", "2025-11-01 17:10", "Scheduled"));

        log.info(findFlight.getDepartureAirport());
        log.info(findFlight.getArrivalAirport());

        List<FlightStatus> flights = sqlSession.selectList("FlightStatusMapper.flights_search", findFlight);



        return flights;
    }


}
