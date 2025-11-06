package clone.asiana.asiana_clone.booking.controller;


import clone.asiana.asiana_clone.booking.dto.BookingSearchDTO;
import clone.asiana.asiana_clone.booking.service.BookingService;
import clone.asiana.asiana_clone.booking.vo.SearchFlightVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
@Slf4j
public class BookingController {

    @Autowired
    BookingService service;

    @GetMapping
    public String booking() {
        return "booking";
    }

    @PostMapping
    public String bookingSearch(@ModelAttribute BookingSearchDTO search, Model model){

        SearchFlightVO outboundFlight = SearchFlightVO.fromOutbound(search);
        model.addAttribute("outboundFlights", service.SearchFlights(outboundFlight));

        if(search.getReturnDate() != null) {
            SearchFlightVO returnFlight = SearchFlightVO.fromReturn(search);
            model.addAttribute("returnFlights", service.SearchFlights(returnFlight));
        }

        return "/booking";
    }



}
