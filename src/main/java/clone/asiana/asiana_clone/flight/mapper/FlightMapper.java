package clone.asiana.asiana_clone.flight.mapper;

import clone.asiana.asiana_clone.flight.vo.FindFlight;
import clone.asiana.asiana_clone.flight.vo.FlightStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlightMapper {

    List<String> departureAirport();
    List<String> arrivalAirport();
    List<FlightStatus> flightsSearch(FindFlight findFlight);
}
