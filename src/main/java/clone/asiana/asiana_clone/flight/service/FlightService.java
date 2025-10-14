package clone.asiana.asiana_clone.flight.service;


import clone.asiana.asiana_clone.flight.vo.FlightStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class FlightService {


    public List<String> findAllDepartureAirports(){

        return Arrays.asList("ICN", "GMP", "PUS", "CJU");
    };

    public List<String> findAllArrivalAirports(){

        return Arrays.asList("HND", "NRT", "FUK", "CTS", "KIX");
    };

    public List<FlightStatus> findFlights(String from, String to, LocalDate departureDate){

        List<FlightStatus> flights = new ArrayList<>();
        flights.add(new FlightStatus("KE123", "ICN", "HND", "2025-11-01 08:00", "2025-11-01 10:00", "Scheduled"));
        flights.add(new FlightStatus("OZ456", "ICN", "NRT", "2025-11-01 09:30", "2025-11-01 11:45", "Delayed"));
        flights.add(new FlightStatus("JL789", "ICN", "FUK", "2025-11-01 07:15", "2025-11-01 09:05", "Cancelled"));
        flights.add(new FlightStatus("KE321", "ICN", "CTS", "2025-11-01 12:00", "2025-11-01 14:30", "Scheduled"));
        flights.add(new FlightStatus("OZ654", "ICN", "KIX", "2025-11-01 15:20", "2025-11-01 17:10", "Scheduled"));

        return flights;
    }


}
