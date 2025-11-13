package clone.asiana.asiana_clone.booking.controller;


import clone.asiana.asiana_clone.booking.dto.BookingSearchDTO;
import clone.asiana.asiana_clone.booking.service.BookingService;
import clone.asiana.asiana_clone.booking.vo.SearchFlightVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/booking")
@Slf4j
public class BookingController {

    @Autowired
    BookingService service;

    @GetMapping
    public String booking() {
        return "booking/booking";
    }

    @PostMapping
    public String bookingSearch(@ModelAttribute BookingSearchDTO search, RedirectAttributes redirectAttributes){

        SearchFlightVO outboundFlight = SearchFlightVO.fromOutbound(search);
        redirectAttributes.addFlashAttribute("outboundFlights", service.SearchFlights(outboundFlight));

        if(search.getReturnDate() != null) {
            SearchFlightVO returnFlight = SearchFlightVO.fromReturn(search);
            redirectAttributes.addFlashAttribute("returnFlights", service.SearchFlights(returnFlight));
        }

        return "redirect:/booking";
    }



}
