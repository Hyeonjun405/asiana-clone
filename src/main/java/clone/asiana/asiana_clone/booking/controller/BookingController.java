package clone.asiana.asiana_clone.booking.controller;


import clone.asiana.asiana_clone.booking.dto.BookingSearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
@Slf4j
public class BookingController {

    @GetMapping
    public String booking() {
        return "booking";
    }

    @PostMapping
    public String bookingSearch(@ModelAttribute BookingSearchDTO Search){


        return "/booking";
    }



}
