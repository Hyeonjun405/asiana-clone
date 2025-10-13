package clone.asiana.asiana_clone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/booking")
    public String booking() {
        return "booking";
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "reservation";
    }

    @GetMapping("/flight-search")
    public String flightStatus() {
        return "flight-search";
    }

    @GetMapping("/event")
    public String event() {
        return "event";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
