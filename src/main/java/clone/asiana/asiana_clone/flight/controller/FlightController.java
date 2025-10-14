package clone.asiana.asiana_clone.flight.controller;

import clone.asiana.asiana_clone.flight.dto.FlightRequestDto;
import clone.asiana.asiana_clone.flight.service.FlightService;
import clone.asiana.asiana_clone.flight.vo.FlightStatus;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/flight-search")
public class FlightController {

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
    public String searchFlights(FlightRequestDto requestDto, Model model) {

        List<FlightStatus> flights = flightService.findFlights(requestDto.getFrom(), requestDto.getTo(), requestDto.getDepartureDate());

        if(flights == null) flights = new ArrayList<>();

        model.addAttribute("flights", flights);

        model.addAttribute("from", requestDto.getFrom());
        model.addAttribute("to", requestDto.getTo());
        model.addAttribute("departureDate", requestDto.getDepartureDate());

        model.addAttribute("departure", flightService.findAllArrivalAirports());
        model.addAttribute("arrival", flightService.findAllDepartureAirports());

        return "flight-search";
    }

}
