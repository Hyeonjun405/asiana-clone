package clone.asiana.asiana_clone.flight.mapper;

import clone.asiana.asiana_clone.flight.vo.FindFlightVO;
import clone.asiana.asiana_clone.flight.vo.FlightStatusVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlightMapper {

    List<String> departureAirport();
    List<String> arrivalAirport();
    List<FlightStatusVO> flightsSearch(FindFlightVO findFlightVO);
}
