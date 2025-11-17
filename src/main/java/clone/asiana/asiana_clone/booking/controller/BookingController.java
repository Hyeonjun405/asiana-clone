package clone.asiana.asiana_clone.booking.controller;


import clone.asiana.asiana_clone.booking.dto.BookingRequestDTO;
import clone.asiana.asiana_clone.booking.dto.BookingSearchDTO;
import clone.asiana.asiana_clone.booking.service.BookingFacadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    BookingFacadeService service;

    @GetMapping
    public String booking(Model model) {
        return "booking/booking";
    }

    @PostMapping
    public String bookingSearch(@ModelAttribute BookingSearchDTO search, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("search", search);

        //search의 TripType를 가지고 ROUND랑 기본형 분류.
        redirectAttributes.addFlashAttribute("outboundFlights", service.searchFlights(search.toFirstFlightVO(), search.toPassengerInfoVO()));

        if(search.getTripType().equals("ROUND")) {
            redirectAttributes.addFlashAttribute("returnFlights", service.searchFlights(search.toSecondFlightVO(), search.toPassengerInfoVO()));
        }

        return "redirect:/booking";
    }

    @GetMapping("/payment")
    public String payment() {
        return "booking/payment";
    }

    @PostMapping("/payment")
    public String payment(@ModelAttribute BookingRequestDTO param){

        return "redirect:/booking/payment";
    }


}
