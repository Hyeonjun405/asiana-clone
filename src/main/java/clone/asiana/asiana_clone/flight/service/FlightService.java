package clone.asiana.asiana_clone.flight.service;


import clone.asiana.asiana_clone.flight.mapper.FlightMapper;
import clone.asiana.asiana_clone.flight.vo.FindFlightVO;
import clone.asiana.asiana_clone.flight.vo.FlightStatusVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class FlightService {

    @Autowired
    private FlightMapper flightMapper;

    public List<String> findAllDepartureAirports(){

        return flightMapper.departureAirport();
    };

    public List<String> findAllArrivalAirports(){

        return flightMapper.arrivalAirport();
    };

    public List<FlightStatusVO> findFlights(FindFlightVO findFlightVO){

         return flightMapper.flightsSearch(findFlightVO);
    }


}
