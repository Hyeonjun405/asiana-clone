package clone.asiana.asiana_clone.flight.service;


import clone.asiana.asiana_clone.flight.mapper.FlightMapper;
import clone.asiana.asiana_clone.flight.vo.FindFlight;
import clone.asiana.asiana_clone.flight.vo.FlightStatus;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class FlightService {

    @Autowired
    private FlightMapper flightMapper;

    public List<String> findAllDepartureAirports(){

        return flightMapper.departureAirport();
    };

    public List<String> findAllArrivalAirports(){

        return flightMapper.arrivalAirport();
    };

    public List<FlightStatus> findFlights(FindFlight findFlight){
         return flightMapper.flightsSearch(findFlight);
    }


}
