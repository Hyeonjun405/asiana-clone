package clone.asiana.asiana_clone.booking.service;

import clone.asiana.asiana_clone.booking.mapper.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {


    @Autowired
    BookingMapper mapper;
}
