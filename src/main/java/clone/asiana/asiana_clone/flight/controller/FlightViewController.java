package clone.asiana.asiana_clone.flight.controller;

import clone.asiana.asiana_clone.flight.dto.FlightRequestDTO;
import clone.asiana.asiana_clone.flight.service.FlightService;
import clone.asiana.asiana_clone.flight.vo.FindFlightVO;
import clone.asiana.asiana_clone.flight.vo.FlightStatusVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/flight-search")
public class FlightViewController {

    @Autowired
    FlightService flightService;

    @GetMapping
    public String flightSearchForm(Model model) {

        model.addAttribute("departure", flightService.findAllArrivalAirports());
        model.addAttribute("arrival", flightService.findAllDepartureAirports());
        model.addAttribute("departureDate", null);
        model.addAttribute("flights", null);  // 조회 전에는 null
        return "flight-search";
    }

    @PostMapping("/search")
    public String searchFlights(@ModelAttribute FlightRequestDTO requestDto, Model model) {

        FindFlightVO findFlightVO = new FindFlightVO(requestDto.getDepartureAirport(), requestDto.getArrivalAirport(), requestDto.getDepartureDate());

        List<FlightStatusVO> flights = flightService.findFlights(findFlightVO);

        if(flights == null) flights = new ArrayList<>();

        model.addAttribute("flights", flights);

        model.addAttribute("departureAirport", requestDto.getDepartureAirport());
        model.addAttribute("arrivalAirport", requestDto.getArrivalAirport());
        model.addAttribute("departureDate", requestDto.getDepartureDate());

        model.addAttribute("departure", flightService.findAllArrivalAirports());
        model.addAttribute("arrival", flightService.findAllDepartureAirports());

        return "flight-search";
    }

}
