package clone.asiana.asiana_clone.common.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/inquiry")
    public String reservationSearch() {
        return "inquiry/inquiry-search";
    }

    @GetMapping("/inquiry/detail")
    public String reservationDetail() {
        return "inquiry/inquiry-detail";
    }

    @GetMapping("/event")
    public String event() {
        return "event";
    }

}
