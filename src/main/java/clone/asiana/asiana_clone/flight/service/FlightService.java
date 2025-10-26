package clone.asiana.asiana_clone.flight.service;


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
    private SqlSessionTemplate sqlSession;


    public List<String> findAllDepartureAirports(){

        return sqlSession.selectList("FlightStatusMapper.flights_departureAirport");
    };

    public List<String> findAllArrivalAirports(){
        return sqlSession.selectList("FlightStatusMapper.flights_arrivalAirport");
    };

    public List<FlightStatus> findFlights(FindFlight findFlight){
         return sqlSession.selectList("FlightStatusMapper.flights_search", findFlight);
    }


}
