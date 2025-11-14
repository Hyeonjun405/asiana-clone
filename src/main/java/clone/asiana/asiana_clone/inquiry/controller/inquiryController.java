package clone.asiana.asiana_clone.inquiry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry")
public class inquiryController {

    @GetMapping
    public String reservationSearch() {
        return "inquiry/inquiry-search";
    }

    @GetMapping("/detail")
    public String reservationDetailPage() {
        return "inquiry/inquiry-detail";
    }

    @PostMapping("/detail")
    public String reservationDetail() {
        return "redirect:/inquiry/detail";
    }


}
